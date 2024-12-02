package com.pmdm.parcelables;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    //Declaramos las variables de los elementos gráficos a usar.
    private Button agregar;
    private Button guardar;
    private EditText nombreEditText;
    private EditText edadEditText;
    private EditText notaMEditText;
    //Declaramos un linear layout para añadir las nuevas materias.
    private LinearLayout ll;
    //Declaramos un contador de materias que inicializaremos a 0 y un maximo de materias que será un valor final a 4.
    private int contadorMaterias = 0;
    private static final int MAX_MATERIAS = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //Inicializamos los elementos gráficos y layout que vamos a usar.
        agregar = findViewById(R.id.buttonAgregarMateria);
        guardar = findViewById(R.id.buttonGuardar);
        nombreEditText = findViewById(R.id.editTextNombre);
        edadEditText = findViewById(R.id.editTextEdad);
        notaMEditText = findViewById(R.id.editTextNotaMedia);
        ll = findViewById(R.id.LinearLayout);
        ll.setOrientation(LinearLayout.VERTICAL);

        //Añadimos un onClick listener a el botón guardar que ejecute un método que guarda datos.
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarDatos();
            }
        });

        //Añadimos un onClick listener a el botón editar que ejecute un método que agrega materias.
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agregarMateria();
            }
        });
    }

    //Con este método generamos nuevas asignaturas de manera dinámica dentro de la app. que se guardan en el linear layout.
    private void agregarMateria() {
        if (contadorMaterias >= MAX_MATERIAS) {
            Toast.makeText(this, "El número máximo de materias es 4", Toast.LENGTH_SHORT).show();
            return;
        }

        // Creamos un nuevo linear layout para cada vista "objeto" de cada materia, es decir
        //Es el contenedor de los elementos que forman una nueva materia.
        LinearLayout materiaNueva = new LinearLayout(this);
        //Establecemos los parametros de la nuevan vista de materia.
        materiaNueva.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams materiaLayoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        materiaLayoutParams.setMargins(0, 8, 0, 8); // Márgenes superior e inferior
        materiaNueva.setLayoutParams(materiaLayoutParams);

        //Declaramos dos nuevos editText para el nombre y nota de la materia que será ajustado
        //al nuevo layout que hemos creado para las vistas de la materia.
        EditText editTextNombreAsignatura = new EditText(this);
        editTextNombreAsignatura.setHint("Materia");
        LinearLayout.LayoutParams nombreParams = new LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1.0f
        );
        nombreParams.setMargins(0, 0, 8, 0);
        editTextNombreAsignatura.setLayoutParams(nombreParams);

        //Lo mismo para la ota media
        EditText editTextNotaMediaAsignatura = new EditText(this);
        editTextNotaMediaAsignatura.setHint("Nota Media");
        editTextNotaMediaAsignatura.setInputType(android.text.InputType.TYPE_CLASS_NUMBER | android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL);
        LinearLayout.LayoutParams notaParams = new LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1.0f
        );
        editTextNotaMediaAsignatura.setLayoutParams(notaParams);

        //Y agregamos los nuevos editText al layout es decir el contenedor que hemos creado antes, esto por
        //Cada materria.
        materiaNueva.addView(editTextNombreAsignatura);
        materiaNueva.addView(editTextNotaMediaAsignatura);

        //Ahora agregamos el contenedor, es decir el nuevo linearLayout al linear layout ya existente.
        ll.addView(materiaNueva);
        //Añadimos una materia al contador.
        contadorMaterias++;

        // Verificamos si el limite de materias ha sido superado.
        if (contadorMaterias >= MAX_MATERIAS) {
            //Establece el botón a false para evitar añadir más materias.
            agregar.setEnabled(false);
            Toast.makeText(this, "Máximo de materias agregadas", Toast.LENGTH_SHORT).show();
        }
    }

    //Con este método guardamos los datosn introducidos por el usuario
    private void guardarDatos() {
        //Declaramos e inicializamos al texto los datos introducidos por el usuario.
        String nombre = nombreEditText.getText().toString();
        String edadStr = edadEditText.getText().toString();
        String notaMStr = notaMEditText.getText().toString();

        //Validamos las entradas comprobnando que los campos no estén vacíos.
        if (nombre.isEmpty() || edadStr.isEmpty() || notaMStr.isEmpty()) {
            Toast.makeText(this, "Por favor, rellena todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }
        //Creamos un nuevo entero y float para la edad y la nota media.
        int edad;
        float notaM;

        //Realizamos la conversión de un formato a otro, con un try catch para evitar cuelgues y errores.
        try {
            edad = Integer.parseInt(edadStr);
            notaM = Float.parseFloat(notaMStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Por favor, ingresa números válidos para edad y nota media", Toast.LENGTH_SHORT).show();
            return;
        }

        //Creamos un nuevo objeto estudiante con los datos introducidos
        Estudiante es = new Estudiante(nombre, edad, notaM);

        //Cogemos los datos de las materias añadidads dinámicamente.
        //Primero comprobamos que el contador de materias sea mayor que 0.
        if (contadorMaterias > 0) {
            //Declaramos una nueva variable llamada materiasAñadadidas y contamos el número de elementos que tiene
            //el linear layout principal.
            int materiasAñadidas = ll.getChildCount();
            for (int i = 0; i < materiasAñadidas; i++) {
                //Para cada vista de materia que tenga el linear layout sacamos la materia del linear layout hijo
                //del contenedor materias hijo.
                //Es decir desde el linear layout principal, entramos al linear layout que contiene los edit text de materia
                //Y nota media para recoger esos datos.
                View materia = ll.getChildAt(i);
                //Si la vista materia existe y es una instancia del linear layout
                if (materia instanceof LinearLayout) {
                    //Recupera ese linear layout
                    LinearLayout materiaLayout = (LinearLayout) materia;
                    //Comprobamos que los elementos que haya en el contenedor de materia sea 2 ya que tiene que haber el de
                    //el nombre de la materia y el de la nota
                    if (materiaLayout.getChildCount() >= 2) {
                        //Obtenemos cada dato con el indice como si de un array se tratase.
                        EditText materiaEditText = (EditText) materiaLayout.getChildAt(0);
                        EditText notaEditText = (EditText) materiaLayout.getChildAt(1);
                        //Obtenemos el nombre y la nota de la materia.
                        String materiaNombre = materiaEditText.getText().toString();
                        String materiaNotaStr = notaEditText.getText().toString();

                        //Comprobamos que las entradas no estén vacías.
                        if (!materiaNombre.isEmpty() && !materiaNotaStr.isEmpty()) {
                            try {
                                //Hacemos la conversión de formato
                                float materiaNota = Float.parseFloat(materiaNotaStr);
                                //Añadimos al array de lista de materias del estudiante cada materia.
                                Materia m = new Materia(materiaNombre, materiaNota);
                                es.listaMaterias.add(m);
                            } catch (NumberFormatException e) {
                                Toast.makeText(this, "Nota no válida en la materia " + materiaNombre, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            }
        }

        //Iniciamos la Secondary Activity y le pasamos los datos del objeto.
        Intent i = new Intent(MainActivity.this, SecondaryActivity.class);
        i.putExtra("obj1", es);
        i.putExtra("NOMBRE",nombre);
        i.putExtra("EDAD",edadStr);
        i.putExtra("NOTAM",notaMStr);
        startActivity(i);
    }
}
