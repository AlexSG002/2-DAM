package com.pmdm.ut2buscaminas;

import android.graphics.Color;
import android.hardware.camera2.params.BlackLevelPattern;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private boolean[][] mines;
    private GridLayout gridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridLayout = findViewById(R.id.gridLayout);

        // Configuración inicial 8x8
        setupGridLayout(8, 8);

    }

    private void setupGridLayout(int rows, int columns) {
        // Limpia las vistas actuales
        gridLayout.removeAllViews();
        gridLayout.setRowCount(rows);
        gridLayout.setColumnCount(columns);
        gridLayout.setBackgroundColor(Color.BLACK);
        boolean[][] revealed = new boolean[rows][columns];
        int mineCount = 0;
        if (rows == 8) {
            mineCount = 10; // 8x8
        } else if (rows == 12) {
            mineCount = 30; // 12x12
        } else if (rows == 16) {
            mineCount = 60; // 16x16
        }
        mines = generateMines(rows, columns, mineCount);
        printMinesMatrix(rows, columns);
        // Calcula las dimensiones después de que el GridLayout se haya renderizado
        gridLayout.post(() -> {
            int gridWidth = gridLayout.getWidth();
            int gridHeight = gridLayout.getHeight();

            int margin = 2;

            // Espacio disponible para las celdas (restando márgenes)
            int availableWidth = gridWidth - (columns * margin * 2);
            int availableHeight = gridHeight - (rows * margin * 2);

            // Tamaño de cada celda
            int cellWidth = availableWidth / columns;
            int cellHeight = availableHeight / rows;

            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < columns; col++) {
                    TextView cell = new TextView(this);

                    // Configura el diseño de la celda
                    GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                    params.setMargins(2,2,2,2);
                    params.width = cellWidth;
                    params.height = cellHeight;
                    params.rowSpec = GridLayout.spec(row);
                    params.columnSpec = GridLayout.spec(col);
                    cell.setLayoutParams(params);

                    // Estiliza la celda inicial
                    cell.setBackgroundColor(Color.LTGRAY);
                    cell.setGravity(android.view.Gravity.CENTER);
                    cell.setTextColor(Color.BLACK);

                    // Agrega un OnClickListener para descubrir la celda
                    int finalRow = row;
                    int finalCol = col;
                    cell.setOnClickListener(v -> {
                        if (cell.getText().toString().equals("🚩")) {
                            // Si la celda tiene una bandera, quítala y no descubras la celda
                            cell.setText("");
                        } else if (revealed[finalRow][finalCol]) {
                            // Si la celda ya está descubierta, no hagas nada
                            return;
                        } else {
                            if (mines[finalRow][finalCol]) {
                                // Si es una mina
                                cell.setText("💣");
                                cell.setBackgroundColor(Color.RED); // Cambia el fondo a rojo
                                showGameOverDialog();
                            } else {
                                // Descubre la celda actual y las vacías alrededor
                                revealEmptyCells(finalRow, finalCol, rows, columns, revealed);
                                checkWinCondition(revealed, rows, columns);
                            }
                            cell.setEnabled(false); // Deshabilita el clic en esta celda
                        }
                    });

                    // Agrega un OnLongClickListener para poner una bandera
                    cell.setOnLongClickListener(v -> {
                        if (revealed[finalRow][finalCol]) {
                            // Si la celda ya está descubierta, no se puede poner una bandera
                            return true;
                        }
                        if (cell.getText().toString().equals("🚩")) {
                            // Si ya tiene una bandera, la quita
                            cell.setText("");
                        } else {
                            // Coloca la bandera
                            cell.setText("🚩");
                        }
                        checkWinCondition(revealed, rows, columns);
                        return true; // Indica que el evento fue consumido
                    });

                    gridLayout.addView(cell);
                }
            }
        });
    }

    private void printMinesMatrix(int rows, int columns) {
        StringBuilder builder = new StringBuilder();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                builder.append(mines[row][col] ? "1 " : "0 ");
            }
            builder.append("\n"); // Nueva línea después de cada fila
        }
        Log.d("MinesMatrix", builder.toString());
    }

    private void checkWinCondition(boolean[][] revealed, int rows, int columns) {
        boolean allMinesFlagged = true;
        boolean allCellsRevealed = true;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                TextView cell = (TextView) gridLayout.getChildAt(row * columns + col);

                if (mines[row][col]) {
                    // Si hay una mina y no tiene bandera, la condición falla
                    if (!cell.getText().toString().equals("🚩")) {
                        allMinesFlagged = false;
                    }
                } else {
                    // Si no es mina y no está descubierta, la condición falla
                    if (!revealed[row][col]) {
                        allCellsRevealed = false;
                    }
                }
            }
        }

        if (allMinesFlagged && allCellsRevealed) {
            showWinDialog();
        }
    }

    private void showWinDialog() {
        new AlertDialog.Builder(this)
                .setTitle("¡Felicidades!")
                .setMessage("¡Has ganado el juego! ¿Quieres jugar otra vez?")
                .setPositiveButton("Reiniciar", (dialog, which) -> {
                    restartGame(); // Reinicia el juego
                })
                .setCancelable(false) // No permite cerrar el diálogo sin interactuar
                .show();
    }


    private int countBombsAround(int row, int col, int rows, int columns) {
        int bombs = 0;

        // Recorrer las 8 direcciones alrededor de la celda
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int neighborRow = row + i;
                int neighborCol = col + j;

                // Verificar si la celda vecina está dentro de los límites del tablero
                if (neighborRow >= 0 && neighborRow < rows && neighborCol >= 0 && neighborCol < columns) {
                    if (mines[neighborRow][neighborCol]) {
                        bombs++;
                    }
                }
            }
        }
        return bombs;
    }

    private void revealEmptyCells(int row, int col, int rows, int columns, boolean[][] revealed) {
        // Verificar límites del tablero
        if (row < 0 || row >= rows || col < 0 || col >= columns) {
            return;
        }

        // Si la celda ya fue revelada, detener
        if (revealed[row][col]) {
            return;
        }

        // Marcar la celda como revelada
        revealed[row][col] = true;

        // Obtener la celda visual
        int index = row * columns + col;
        TextView cell = (TextView) gridLayout.getChildAt(index);

        // Calcular minas alrededor
        int bombsAround = countBombsAround(row, col, rows, columns);

        if (bombsAround > 0) {
            // Si hay minas alrededor, mostrar el número
            cell.setText(String.valueOf(bombsAround));
            cell.setBackgroundColor(Color.WHITE);
        } else {
            // Si no hay minas alrededor, mostrar vacío y continuar recursión
            cell.setText("");
            cell.setBackgroundColor(Color.WHITE);

            // Llamadas recursivas para las celdas vecinas
            revealEmptyCells(row - 1, col - 1, rows, columns, revealed);
            revealEmptyCells(row - 1, col, rows, columns, revealed);
            revealEmptyCells(row - 1, col + 1, rows, columns, revealed);
            revealEmptyCells(row, col - 1, rows, columns, revealed);
            revealEmptyCells(row, col + 1, rows, columns, revealed);
            revealEmptyCells(row + 1, col - 1, rows, columns, revealed);
            revealEmptyCells(row + 1, col, rows, columns, revealed);
            revealEmptyCells(row + 1, col + 1, rows, columns, revealed);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Infla el menú en el ActionBar
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_change_size) {
            showChangeSizeDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showChangeSizeDialog() {
        // Crear un RadioGroup con 3 opciones
        RadioGroup radioGroup = new RadioGroup(this);
        radioGroup.setOrientation(RadioGroup.VERTICAL);

        RadioButton radio8x8 = new RadioButton(this);
        radio8x8.setText("8x8");
        radio8x8.setId(View.generateViewId()); // Genera un ID único
        radio8x8.setTag(8);

        RadioButton radio12x12 = new RadioButton(this);
        radio12x12.setText("12x12");
        radio12x12.setId(View.generateViewId()); // Genera un ID único
        radio12x12.setTag(12);

        RadioButton radio16x16 = new RadioButton(this);
        radio16x16.setText("16x16");
        radio16x16.setId(View.generateViewId()); // Genera un ID único
        radio16x16.setTag(16);

        radioGroup.addView(radio8x8);
        radioGroup.addView(radio12x12);
        radioGroup.addView(radio16x16);

        // Crear el diálogo
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Selecciona el tamaño del tablero");
        builder.setView(radioGroup);
        builder.setPositiveButton("Aceptar", (dialog, which) -> {
            int checkedId = radioGroup.getCheckedRadioButtonId();
            if (checkedId != -1) {
                RadioButton selectedRadio = radioGroup.findViewById(checkedId);
                int selectedSize = (int) selectedRadio.getTag(); // Recupera el tamaño desde el tag
                setupGridLayout(selectedSize, selectedSize);
            }
        });
        builder.setNegativeButton("Cancelar", null);
        builder.show();
    }

    private boolean[][] generateMines(int rows, int columns, int mineCount) {
        boolean[][] mines = new boolean[rows][columns]; // Matriz para guardar las minas
        Random random = new Random();

        int placedMines = 0; // Contador de minas colocadas

        while (placedMines < mineCount) {
            // Generar posición aleatoria
            int randomRow = random.nextInt(rows);
            int randomCol = random.nextInt(columns);

            // Colocar la mina si la celda está vacía
            if (!mines[randomRow][randomCol]) {
                mines[randomRow][randomCol] = true;
                placedMines++;
            }
        }

        return mines;
    }

    private void showGameOverDialog() {
        new AlertDialog.Builder(this)
                .setTitle("¡Has perdido!")
                .setMessage("Has pulsado una mina. ¿Quieres intentarlo de nuevo?")
                .setPositiveButton("Reiniciar", (dialog, which) -> {
                    restartGame(); // Reinicia el juego
                })
                .setCancelable(false) // No permite cerrar el diálogo sin interactuar
                .show();
    }

    private void restartGame() {
        finish(); // Finaliza la actividad actual
        startActivity(getIntent()); // Reinicia la actividad
    }

}


