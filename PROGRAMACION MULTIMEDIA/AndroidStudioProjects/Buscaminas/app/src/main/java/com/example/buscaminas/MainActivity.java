package com.example.buscaminas;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;


public class MainActivity extends AppCompatActivity {
    //Declaramos las variables minas para poner las minas en la matriz con -1 por eso es un entero.
    private int [][] minas;
    //Declaramos las variables minasReveladas para diferenciar las minas reveladas de las no reveladas y comprobar las condiciones de victoria.
    private boolean[][] minasReveladas;
    //Declaramos e inicializamos las filas y columnas iniciales a valores finales (8) porque no se modifican.
    private final int filasIniciales = 8;
    private final int columnasIniciales = 8;
    //Declaramos el gridLayout para el tablero.
    private GridLayout gridLayout;
    //Declaramos el emoji por defecto que estoy usando para las minas:
    private String emojiSeleccionado = "💣";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Método para cambiar el nombre en la action toolbar
        setTitle("Buscaminas");
        //Inicializamos el layout e iniciamos el método para crear las divisiones del layout.
        gridLayout = findViewById(R.id.gridLayout);
        iniciarLayout(filasIniciales, columnasIniciales);
        emojiSeleccionado = cargarEmojiSeleccionado();
    }

    private void iniciarLayout(int filas, int columnas) {
        //Lo primero que haremos será borrar las vistas actuales para cuando cambiemos de dificultad del juego.
        gridLayout.removeAllViews();
        //Ahora establecemos el número de filas y columnas a las que lleguen al método.
        gridLayout.setRowCount(filas);
        gridLayout.setColumnCount(columnas);
        //Ponemos el fondo a negro para diferenciarlo de las casillas.
        gridLayout.setBackgroundColor(Color.BLACK);
        //Inicializamos las variables minas y minasReveladas a matrices con el número de filas y columnas.
        minasReveladas = new boolean[filas][columnas];

        minas = new int[filas][columnas];
        //Declaramos e inicializamos la cantidad de minas a 0.
        int cantidadMinas = 0;
        //Ahora comprobamos si la cantidad de filas es igual a 8, 12 o 16 establece más o menos minas.
        if (filas == 8) {
            cantidadMinas = 10; // 8x8
        } else if (filas == 12) {
            cantidadMinas = 30; // 12x12
        } else if (filas == 16) {
            cantidadMinas = 60; // 16x16
        }
        //Generamos las minas.
        generarMinas(filas, columnas, cantidadMinas);
        //Imprime las minas en el logcat para hacer pruebas.
        imprimirMinas(filas, columnas);

        //Método que sirve para calcular las dimensiones una vez el grid layout ha sido renderizado, esto es para evitar cuelgues por null pointer exceptions.
        gridLayout.post(() ->{
            //Obtenemos la anchura y altura del layout
            int anchuraGrid = gridLayout.getWidth();
            int alturaGrid = gridLayout.getHeight();
            //Inicializamos y declaramos la variable margen para que se distingan bien unas casillas de otras
            int margen = 2;

            //Calculamos el espacio disponible para cada celda, esto lo hacemos restando a la altura y anchura
            // lo multiplicado de las filas y columnas, por el margen y por 2 ya que el margen se pone en los 2 lados de la casilla.
            int anchuraDisponible = anchuraGrid - (columnas * margen * 2);
            int alturaDisponible = alturaGrid -(filas * margen * 2);

            //Por lo que entonces para calcular el tamaño de cada celda dividimos la anchura y altura disponibles por columnas y filas respectivamente.
            //El objetivo de esto es que el margen no haga que algunas casillas se salgan un poco de la pantalla,
            int anchuraCelda = anchuraDisponible / columnas;
            int alturaCelda = alturaDisponible / filas;

            //Creamos dos for anidados para crear cada celda
            //Dentro de cada for creamos las variables fila y columna en singular para crear las filas y columnas una por una teniendo en cuenta
            //El número de filas total como fin del bucle.
            for(int fila = 0; fila < filas; fila++){
                for(int columna = 0; columna < columnas; columna++){
                    //Creamos una celda en si.

                    Button celda = new Button(this);

                    //Diseñamos cada celda, para ello iremos configurando los parametros del layout, por lo que creamos la variable parametros de tipo
                    //GridLayout.LayoutParams.

                    GridLayout.LayoutParams parametros = new GridLayout.LayoutParams();
                    //Establecemos los margenes a cada lado:
                    parametros.setMargins(margen,margen,margen,margen);
                    //Establecemos la altura y anchura de cada celda.
                    parametros.width = anchuraCelda;
                    parametros.height = alturaCelda;
                    //Ahora con los métodos rowSpec y columnSpec le diremos al layout con el bucle en que fila y columna colocar cada celda.
                    parametros.rowSpec = GridLayout.spec(fila);
                    parametros.columnSpec = GridLayout.spec(columna);
                    //Establecemos los parametros editados como parametos de la celda.
                    celda.setLayoutParams(parametros);

                    //Estilizamos cada celda, yo he optado por ponerlas en gris y los números en negro
                    celda.setBackgroundColor(Color.LTGRAY);
                    //Y este método sirve para que el texto se centre
                    celda.setGravity(android.view.Gravity.CENTER);
                    celda.setTextColor(Color.BLACK);

                    //Ahora vamos a configurar la lógica de descubrir celdas, para ello primero comprobamos si la celda pulsada es una mina
                    //Para ello crearemos dos variables llamadas ultimaFila y ultimaColumna a las que igualaremos las varibles fila y columna respectivamente
                    //de manera que se guarde la última fila y columna para que comprobemos si en esa celda se encuentra una mina o no.
                    int ultimaFila = fila;
                    int ultimaColumna = columna;
                    //Establecemos un on click listener al botón para que ejecute las acciones al pulsarlo:
                    celda.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(minas[ultimaFila][ultimaColumna] ==-1){
                                //Si es una mina hacemos lo siguiente;
                                //Establecemos el texto de la celda al icono de mina.
                                celda.setText(emojiSeleccionado);
                                //Cambiamos el fondo a rojo.
                                celda.setBackgroundColor(Color.RED);
                                //Una vez pulsada una mina se ejecuta el método "pierdes" que lanza un mensaje y permite reiniciar la partida.
                                pierdes("Has perdido porque pulsaste una mina");
                            }else{
                                //En caso de que no sea una mina
                                //Comprobamos si se pueden revelar minas alrededor con el método recursivo, por lo que le pasamos todos los datos del tablero.
                                revelarCeldasVacias(ultimaFila, ultimaColumna, filas, columnas, minasReveladas);
                                //Comprobamos si es una victoria en caso de que sea la última celda descubierta, por lo que le pasamos las minas reveladas
                                //Las filas y las columnas para que compruebe todas las casillas
                                comprobarVictoria(minasReveladas, filas, columnas);
                            }
                            //Desabilitamos la celda para que no se pueda pulsar una vez descubierta
                            celda.setEnabled(false);
                        }
                    });
                    //Agregamos un OnLogClick Listener para poner banderas e identificar minas.
                    celda.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            //Primero comprobamos si la celda ya está descubierta para que no se puedan poner banderas en celdas ya descubiertas.
                            if(minasReveladas[ultimaFila][ultimaColumna]){
                                return true;
                            }
                            //Ponemos la bandera en la casilla
                            celda.setText("🚩");
                            //Ahora comprobamos si hemos puesto la bandera sobre una mina
                            if (minas[ultimaFila][ultimaColumna] == -1){
                                //Ponemos el icono de mina para darle información al jugador de que ha descubierto una mina.
                                celda.setText(emojiSeleccionado);
                                //Fondo a blanco para indicar celda descubierta.
                                celda.setBackgroundColor(Color.WHITE);
                                //Establecemos esa celda como revelada.
                                minasReveladas[ultimaFila][ultimaColumna] = true;
                                //Mensaje para confirmar al usuario que ha encontrado una mina.
                                Toast.makeText(MainActivity.this,"¡Has encontrado una mina!",Toast.LENGTH_SHORT).show();
                                celda.setEnabled(false);
                            }else{
                                //Si el usuario sin embargo pone la bandera sobre una casilla vacia pierde.
                                //Ponemos el color del fondo de la celda a rojo.
                                celda.setBackgroundColor(Color.RED);
                                //De nuevo le lanzamos al usuario un mensaje de derrota y un botón para reiniciar la partida
                                pierdes("Has perdido porque colocaste una bandera en una celda sin mina.");
                            }
                            //Comprobamos si hemos ganado ya que puede ser que el usuario haya ganado descubriendo la última mina con la bandera.
                            comprobarVictoria(minasReveladas, filas, columnas);
                            return true;
                        }
                    });
                    //Añadimos la vista de las celdas es decir los objetos de las celdas a la grid layout
                    gridLayout.addView(celda);
                }
            }
        });
    }
    //Método para imprimir las minas.
    private void imprimirMinas(int filas, int columnas) {
        //Utilizamos un string builder para añadir al string de la matriz si es una mina o casilla vacia.
        StringBuilder builder = new StringBuilder();
        //for anidados para construir la matriz.
        for (int fila = 0; fila < filas; fila++) {
            for (int columna = 0; columna < columnas; columna++) {
                builder.append(minas[fila][columna] == -1 ? "-1 " : "0 ");
            }
            //Añadimos un salto de línea después de cada fila
            builder.append("\n");
        }
        //Imprimimos las minas en el LogCat.
        Log.d("imprimirMinas", builder.toString());
    }

    //Método para generar minas
    private void generarMinas(int filas, int columnas, int cantidadMinas) {
        //Importamos y usamos el método Random para generar números aleatorios.
        Random random = new Random();
        //Creamos y declaramos la variable minasColocadas a 0
        int minasColocadas = 0;
        //Comprobamos que el número de minas colocadas no sea menor que la cantidad de minas, en caso contrario entramos en el bucle.
        while(minasColocadas < cantidadMinas){
            //Declaramos e inicializamos las variables filaRandom y columnaRandom que serán seleccionadas aleatoriamente dentro del número de filas con el
            //método random.
            int filaRandom = random.nextInt(filas);
            int columnaRandom = random.nextInt(columnas);

            //Comprobamos si en la celda seleccionada de la matriz existe una mina.
            if(minas[filaRandom][columnaRandom] !=1){
                //Si no existe se añade una mina.
                minas[filaRandom][columnaRandom] =-1;
                //Y aumentamos el número de minasColocadas en uno, de manera que el bucle se parará cuando se hayan puesto el número de cantidadMinas
                //En las primeras casillas distintas.
                minasColocadas++;
            }
        }
    }
    //Método que lanza un mensaje diciendole al usuario que perdió
    private void pierdes(String mensaje){
        //Creamos un Alert Dialog para lanzar el mensaje al usuario, le ponemos titulo, mensaje y configuramos la función del botón.
        new AlertDialog.Builder(this).setTitle("¡Has Perdido!")
                .setMessage(mensaje).setPositiveButton("Reiniciar", (dialog, which) -> {
                    reiniciarPartida();
                    //Con esto hacemos que el dialogo sea modal y no se pueda cerrar hasta que el usuario haga clic en Reiniciar.
                }).setCancelable(false).show();
    }
    //Método con recursividad para descubrir las celdas vacias.
    private void revelarCeldasVacias(int fila, int columna, int filas, int columnas, boolean[][] minasReveladas){

        //Comprobamos si la celda actual está dentro de los límites del tablero para no provocar excepciones.
        if(fila < 0 || fila >= filas || columna < 0 || columna >= columnas){
            return;
        }
        //Comprobamos si la celda está revelada para evitar revelarla de nuevo evitando cuelgues o bucles infinitos.
        if(minasReveladas[fila][columna]){
            return;
        }
        //Revelamos la celda en la que se encuentre.
        if(!minasReveladas[fila][columna]){
            minasReveladas[fila][columna] = true;
        }
        //El indice sirve para calcular en que celda se encuentra del grid layout.
        int indice = fila * columnas + columna;
        //Con el método getChildAt seleccionamos el elemento "hijo" del layout, es decir la celda del layout con el indice calculado antes.
        Button celda = (Button) gridLayout.getChildAt(indice);
        //Calculamos las minas alrededor de la celda seleccionada con el método para contar minas.
        int minasAlrededor = contarMinasAlrededor(fila, columna, filas, columnas);
        //Si las minas alrededor son mayores a 0.
        if(minasAlrededor > 0){
            //Establece el valor de la celda limite al número de minas que haya alrededor de la celda, como es un entero lo volvemos
            //un string con String.valueOf(entero);
            celda.setText(String.valueOf(minasAlrededor));
            //Ponemos el fondo en blanco
            celda.setBackgroundColor(Color.WHITE);
        }else{
            //En caso de que no hayan minas alrededor no ponemos nada de texto en la celda y la ponemos blanca
            celda.setText("");
            celda.setBackgroundColor(Color.WHITE);

            //Aquí la recursividad del método que sirve para comprobar las 8 celdas alrededor de la celda que hemos pulsado.
            revelarCeldasVacias(fila -1, columna -1, filas, columnas, minasReveladas);
            revelarCeldasVacias(fila -1, columna, filas, columnas, minasReveladas);
            revelarCeldasVacias(fila -1, columna +1, filas, columnas, minasReveladas);
            revelarCeldasVacias(fila, columna -1, filas, columnas, minasReveladas);
            revelarCeldasVacias(fila, columna +1, filas, columnas, minasReveladas);
            revelarCeldasVacias(fila +1, columna -1, filas, columnas, minasReveladas);
            revelarCeldasVacias(fila +1, columna, filas, columnas, minasReveladas);
            revelarCeldasVacias(fila +1, columna +1, filas, columnas, minasReveladas);
        }
    }
    //Método que cuenta las mianas alrededor de la celda que hemos pulsado
    private int contarMinasAlrededor(int fila, int columna, int filas, int columnas){
        //Declaramos e inicializamos una variable minasExistentes, para saber cuantas minas existen alrededor de nuestra celda
        int minasExistentes = 0;
        //Hacemos un bucle anidado para comprobar las minas.
        for(int i = -1; i <= 1; i++){
            for(int j = -1; j <= 1; j++){
                //Declaramos e inicializamos las variables filaColindante y columnaColindante a el valor de la fila actual + el valor
                //del iterador que puede estar entre -1 o 1 para comprobar las filas arriba y abajo y las columnas a derecha e izquierda.
                int filaColindante = fila + i;
                int columnaColindante = columna + j;

                //Comprobamos si la filaColindante es mayor o igual a cero y es menor al número de filas y la columna colindante es mayor
                //a 0 y menor al número de columnas para identificar en que fila o columna colindante si se encuentra alguna mina
                if(filaColindante >= 0 && filaColindante < filas && columnaColindante >= 0 && columnaColindante < columnas){
                    //Comprobamos si existe una mina en las filas comparando las minas de las filas y columnas a -1, es decir la celda
                    //con las posiciones de las filas y columnas colindantes.
                    if(minas[filaColindante][columnaColindante] == -1){
                        //Si es así añadimos una minaExistente, para poder ponerla de texto en la celda.
                        minasExistentes++;
                    }
                }

            }
        }
        //Devolvemos el número de minasExistentes alrededor de la celda.
        return minasExistentes;
    }

    //Menú que sirve para comprobar si hemos ganado.
    private void comprobarVictoria(boolean[][] minasReveladas, int filas, int columnas){
        //Creamos la variable que comprobara si tenemos todas las minas marcadas.
        boolean todasLasMinasMarcadas = true;


        //Recorremos de nuevo la matriz
        for(int fila = 0; fila < filas; fila++){
            for (int columna = 0; columna < columnas; columna++){
                //De nuevo seleccionamos la celda actual con el método de seleccionar el elemento "hijo" del layout con el indice de
                //la fila y la columna actuales.
                Button celda = (Button) gridLayout.getChildAt(fila * columnas + columna);
                //Comprobamos si existe una mina en la celda.
                if(minas[fila][columna] == -1 && !minasReveladas[fila][columna]){
                    //Si existe comprubea que este marcada, comparandolo con el emoji de bomba.
                    if(!celda.getText().toString().equals(emojiSeleccionado)){
                        //Si no equivale al emoji de seleccionado significa que no están todas las bombas marcadas.
                        todasLasMinasMarcadas = false;
                    }
                }
            }
        }

        //Si todas las celdas están descubiertas y todas las minas marcadas.
        if(todasLasMinasMarcadas){
            //Entramos en el método de victoria.
            ganaste();
        }

    }
    //Método de victoria que simplemente lanza un mensaje con un botón que reinicia la partida de las misma manera que el de perder.
    private void ganaste(){
        new AlertDialog.Builder(this)
                .setTitle("¡Felicidades!")
                .setMessage("¡Has ganado el juego! ¿Quieres jugar otra vez?")
                .setPositiveButton("Reiniciar", (dialog, which) -> {
                    reiniciarPartida(); // Reinicia el juego
                })
                .setCancelable(false) // No permite cerrar el diálogo sin interactuar
                .show();
    }
    //Método para reiniciar la partida
    private void reiniciarPartida() {
        //Reinicia la aplicación con recreate();
        recreate();
    }

    //Método que sirve para crear las opciones del menú en la ActionBar.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflamos el menú, es decir hacemos que aparezca con el método MenuInflater.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //Método para añadir los elementos del menú
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Obtenemos la id del item del menú, que están en el xml y ejecutamos el método que este de acuerdo.
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

    //Método para cambiar de dificultad
    private void cambioDificultad() {
        //Inicializa un radioGroup para introducir los radioButtons y que aparezca la ventana de cambio de dificultad.
        RadioGroup radioGroup = new RadioGroup(this);
        //Ponemos la orientación del mensaje de los radioButtons en vertical.
        radioGroup.setOrientation(RadioGroup.VERTICAL);

        //Creamos 3 radioButtons con las distintas dificultades, que son 8x8 o principiante, 12x12 o amateur y 16x16 o profesional
        RadioButton radio8x8 = new RadioButton(this);
        radio8x8.setText("Nivel Principiante");
        //Método para que se genere una id única para cada botón de manera que no de problemas.
        radio8x8.setId(View.generateViewId());
        //Método que sirve para ponerle una etiqueta al botón para que otros métodos lo identifiquen.
        radio8x8.setTag(8);

        RadioButton radio12x12 = new RadioButton(this);
        radio12x12.setText("Nivel Amateur");
        radio12x12.setId(View.generateViewId());
        radio12x12.setTag(12);

        RadioButton radio16x16 = new RadioButton(this);
        radio16x16.setText("Nivel Avanzado");
        radio16x16.setId(View.generateViewId());
        radio16x16.setTag(16);

        //Añadimos las vistas, es decir los objetos al radioGroup que es la agrupación de radioButtons.
        radioGroup.addView(radio8x8);
        radioGroup.addView(radio12x12);
        radioGroup.addView(radio16x16);

        //Creamos entonces un mensaje de Dialog Builder para mostrar el radiogGroup
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Selecciona el tamaño del tablero");
        builder.setView(radioGroup);
        builder.setPositiveButton("Aceptar", (dialog, which) -> {
            //Declaramos e incializamos una variable para comprobar el id de los radioButtons y lo comprobasmos conel método getCheckedRadioButtonId.
            int comprobarID = radioGroup.getCheckedRadioButtonId();
            //Mientras el id sea distinto de -1
            if (comprobarID != -1) {
                //Seleccionamos el id del botón seleccionado creando un nuevo RadioButton auxiliar para comparar
                RadioButton botonSeleccionado = radioGroup.findViewById(comprobarID);
                //Obtenemos el tag que tiene ese botón auxiliar y se lo pasamos como valor entero a la variable que acabamos de crear
                //que he llamado dificultad seleccionada.
                int dificultadSeleccionada = (int) botonSeleccionado.getTag();
                //Los tags son 8, 12 o 16 que son los que hemos puesto antes, de manera que ahora simplemente iniciamos una nueva layout
                //con la dificultad dicha.
                iniciarLayout(dificultadSeleccionada, dificultadSeleccionada);
            }
        });
        //Establecemos un botón de cancelar y mostramos la ventana.
        builder.setNegativeButton("Cancelar", null);
        builder.show();
    }

    //Método para lanzar un mensaje con las instrucciones.
    private void mostrarInstrucciones() {
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
    //Método para mostrar la lista de cambio de minas.
    private void mostrarCambioDeMina() {
        //Creamos un array de strings con las posibles opciones.
        String[] opciones = {"Emoji de bomba 💣", "Estrella ⭐", "Calavera 💀"};

        //Creamos el cuadro de diálogo para lanzar el mensaje
        new AlertDialog.Builder(this)
                .setTitle("Selecciona el icono para las minas")
                .setItems(opciones, (dialog, which) -> {
                    //Creamos una variable string para seleccionar uno de los iconos de la lista.
                    String iconoSeleccionado = opciones[which];
                    //Solicitamos el cambio de icono con un switch y diferentes casos para los emojis.
                    switch (iconoSeleccionado) {
                        //Establecemos un emoji por cada opción.
                        case "Emoji de bomba 💣":
                            //establecemos la variable global emojiSeleccionado como el emoji que queremos.
                            emojiSeleccionado = "💣";
                            break;
                        case "Estrella ⭐":
                            emojiSeleccionado = "⭐";
                            break;
                        case "Calavera 💀":
                            emojiSeleccionado = "💀";
                            break;
                    }
                    //Guardamos el emoji en las preferencias.
                    guardarEmojiSeleccionado(emojiSeleccionado);
                    //Actualizamos las minas ya reveladas al nuevo emoji.
                    actualizarMinasReveladas();
                })
                .setCancelable(true)
                .show();
    }
    //Método para guardar las preferencias del emoji.
    private void guardarEmojiSeleccionado(String emoji) {
        //Utilizamos sharedPreferences para guardar el emoji.
        getSharedPreferences("PreferenciasBuscaminas", MODE_PRIVATE)
                .edit()
                .putString("emojiSeleccionado", emoji)
                .apply();
    }
    //Con este método cargamos el emoji que tengamos seleccionado en las sharedPreferences.
    private String cargarEmojiSeleccionado() {
        return getSharedPreferences("PreferenciasBuscaminas", MODE_PRIVATE)
                //Con esto establecemos el valor por defecto.
                .getString("emojiSeleccionado", "💣");
    }
    //Método para actulizar las minas ya reveladas y cambiar el emoji.
    private void actualizarMinasReveladas() {
        //Recorremos la matriz en busca de minas ya reveladas.
        for (int fila = 0; fila < minas.length; fila++) {
            for (int columna = 0; columna < minas[0].length; columna++) {
                //Comprobamos si existe una mina y ya está revelada en la celda seleccionada.
                if (minas[fila][columna] == -1 && minasReveladas[fila][columna]) {
                    // En caso de que si, obtenemos la mina en la que se encuentre, de nuevo con el método
                    //getChildAt para conocer la celda "hija" del grid en la que nos encontramos, para ello como siempre
                    //El índice calculado con la fila * el número de columnas y la columna en concreto.
                    int indice = fila * minas[0].length + columna;
                    //Igualamos la celda a la hija.
                    Button celda = (Button) gridLayout.getChildAt(indice);

                    // Actualizamos el texto al emoji seleccionado.
                    celda.setText(emojiSeleccionado);
                }
            }
        }
    }

}