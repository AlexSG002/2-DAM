package com.example.buscaminasrecuperacionalejandrosanchez;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.gridlayout.widget.GridLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
//Clase principal que inicializa el juego y maneja la lógica del menú.
public class MainActivity extends AppCompatActivity {
    //Declaramos e inicializamos la dificultad por defecto que es 8x8.
    private int dificultadSeleccionada = 8;
    //Declaramos el grid layout.
    private GridLayout gl;
    //Declaramos una variable string para el emoji seleccionado de la mina.
    private String emojiSeleccionado;
    //Declaramos una instancia de la clase tablero.
    private Tablero t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        //Cargamos el emoji seleccionado por el usuario en caso de que haya seleccionado alguno.
        emojiSeleccionado = cargarEmojiSeleccionado();
        //Cambiamos el título de la toolbar.
        setTitle("Buscaminas");
        //Inicializamos el grid layout a la ya existente.
        gl = findViewById(R.id.gridLayout);
        //Inicializamos con el método post la instancia del tablero a una nueva con las variables necesarias.
        //Utilizando el método post nos aseguramos de que se inicialice por completo el gridlayout antes de
        //Calcular sus dimensiones.
        gl.post(() -> {
            t = new Tablero(this, gl, dificultadSeleccionada,dificultadSeleccionada, emojiSeleccionado);
        });
    }
    //Método para inflar el menú de opciones.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    //Método para ejecutar las distintas funciones del menú de opciones.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.cambiarTamaño) {
            cambioDificultad();
            return true;
        } else if (id == R.id.reiniciar) {
            reiniciarPartida();
            return true;
        } else if (id == R.id.instrucciones) {
            mostrarInstrucciones();
            return true;
        }else if (id == R.id.cambiarMina) {
            mostrarCambioDeMina();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    //Método para cambiar la dificultad del tablero.
    private void cambioDificultad() {
        //Con un grupo de radioButtons y radioButtons para cada opción.
        RadioGroup radioGroup = new RadioGroup(this);
        radioGroup.setOrientation(RadioGroup.VERTICAL);

        RadioButton radio8x8 = new RadioButton(this);
        radio8x8.setText("Nivel Principiante");
        radio8x8.setId(View.generateViewId());
        //Establecemos una tag con un entero indicando la dificultad en cada una de las opciones a cada radioButton.
        radio8x8.setTag(8);

        RadioButton radio12x12 = new RadioButton(this);
        radio12x12.setText("Nivel Amateur");
        radio12x12.setId(View.generateViewId());
        radio12x12.setTag(12);

        RadioButton radio16x16 = new RadioButton(this);
        radio16x16.setText("Nivel Avanzado");
        radio16x16.setId(View.generateViewId());
        radio16x16.setTag(16);

        radioGroup.addView(radio8x8);
        radioGroup.addView(radio12x12);
        radioGroup.addView(radio16x16);
        //Con esto dejamos indicada la dificultad seleccionada.
        if (dificultadSeleccionada == 8) {
            radio8x8.setChecked(true);
        } else if (dificultadSeleccionada == 12) {
            radio12x12.setChecked(true);
        } else if (dificultadSeleccionada == 16) {
            radio16x16.setChecked(true);
        }
        //Creamos una nueva alerta de dialogo para que el usuario seleccione la dificultad que quiera.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Selecciona el tamaño del tablero");
        builder.setView(radioGroup);
        builder.setPositiveButton("Aceptar", (dialog, which) -> {
            //Obtenemos la id del radioButton que está ahora mismo seleccionado.
            int comprobarID = radioGroup.getCheckedRadioButtonId();
            if (comprobarID != -1) {
                //Siempre que no sea -1 obtenemos entonces de el radioButton la dificultad seleccionada del tag que hemos establecido antes.
                RadioButton botonSeleccionado = radioGroup.findViewById(comprobarID);
                this.dificultadSeleccionada = (int) botonSeleccionado.getTag();
                //Creamos entonces un nuevo tablero con la dificultad introducida.
                Tablero t = new Tablero(this, gl, dificultadSeleccionada, dificultadSeleccionada, emojiSeleccionado);
            }
        });
        builder.setNegativeButton("Cancelar", null);
        builder.show();
    }
    //Método para mostrar las instrucciones del juego.
    private void mostrarInstrucciones() {
        //Simplemente en una alerta de diálogo nos muestra el mensaje con las instrucciones.
        new AlertDialog.Builder(this)
                .setTitle("Instrucciones")
                .setMessage("Cuando pulsas en una casilla, sale un número que identifica cuántas minas hay alrededor. "
                        + "Ten cuidado porque si pulsas en una casilla que tenga una mina escondida, perderás. "
                        + "Si crees o tienes la certeza de que hay una mina, haz un click largo sobre la casilla para señalarla. "
                        + "No hagas un click largo en una casilla donde no hay una mina porque perderás. "
                        + "Ganas una vez hayas encontrado todas las minas.")
                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                .setCancelable(true)
                .show();
    }
    //Método para reiniciar la partida que simplemente ejecuta un "recreate()".
    public void reiniciarPartida() {
        recreate();
    }
    //Método para mostrar las opciones del cambio de mina.
    private void mostrarCambioDeMina() {
        //Creamos un array de strings para las opciones.
        final String[] opciones = {"Emoji de bomba 💣", "Estrella ⭐", "Calavera 💀"};
        //Obitene el indice del emoji actual.
        int emojiActual = obtenerIndiceSeleccion(emojiSeleccionado);
        //Iniciamos un diálogo que indica al usuario que si cambia el icono la partida se reiniciará.
        new AlertDialog.Builder(this)
                .setTitle("Atención")
                .setMessage("Cambiar el icono reiniciará la partida. ¿Deseas continuar?")
                .setPositiveButton("Sí", (dialog, which) -> {
                    //En caso de que confirme con un sí abrimos otra alerta de diálogo para seleccionar el emoji.
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Selecciona el icono para las minas")
                            .setSingleChoiceItems(opciones, emojiActual, null)
                            .setPositiveButton("OK", (dialog2, which2) -> {
                                AlertDialog alert = (AlertDialog) dialog2;
                                int selectedPosition = alert.getListView().getCheckedItemPosition();

                                switch (selectedPosition) {
                                    case 0:
                                        emojiSeleccionado = "💣";
                                        break;
                                    case 1:
                                        emojiSeleccionado = "⭐";
                                        break;
                                    case 2:
                                        emojiSeleccionado = "💀";
                                        break;
                                }
                                //Guardamos el emoji seleccionado.
                                guardarEmojiSeleccionado(emojiSeleccionado);
                                //Reiniciamos la partida.
                                reiniciarPartida();
                            })
                            .setNegativeButton("Cancelar", null)
                            .show();
                })
                .setNegativeButton("No", null)
                .show();
    }


    //Método para obtener el indice de selección para ver que emoji tenemos seleccionado.
    private int obtenerIndiceSeleccion(String emojiSeleccionado) {
        switch (emojiSeleccionado) {
            case "💣":
                return 0;
            case "⭐":
                return 1;
            case "💀":
                return 2;
            default:
                return 0;
        }
    }
    //Método para guardar el emoji seleccionado en las preferencias del usuario.
    private void guardarEmojiSeleccionado(String emoji) {
        getSharedPreferences("PreferenciasBuscaminas", MODE_PRIVATE)
                .edit()
                .putString("emojiSeleccionado", emoji)
                .apply();
    }
    //Método para cargar el emoji seleccionado desde las preferncias del usuario.
    private String cargarEmojiSeleccionado() {
        return getSharedPreferences("PreferenciasBuscaminas", MODE_PRIVATE)
                .getString("emojiSeleccionado", "💣");
    }

}