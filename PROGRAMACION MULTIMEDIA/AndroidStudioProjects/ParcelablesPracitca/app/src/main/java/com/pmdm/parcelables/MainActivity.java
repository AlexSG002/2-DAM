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
    private Button agregar;
    private Button guardar;
    private EditText nombreEditText;
    private EditText edadEditText;
    private EditText notaMEditText;
    private LinearLayout ll;
    private int contadorMaterias = 0;
    private static final int MAX_MATERIAS = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Inicialización de vistas
        agregar = findViewById(R.id.buttonAgregarMateria);
        guardar = findViewById(R.id.buttonGuardar);
        nombreEditText = findViewById(R.id.editTextNombre);
        edadEditText = findViewById(R.id.editTextEdad);
        notaMEditText = findViewById(R.id.editTextNotaMedia);
        ll = findViewById(R.id.LinearLayout);
        ll.setOrientation(LinearLayout.VERTICAL);

        // Configuración del botón "Guardar y mostrar"
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarDatos();
            }
        });

        // Configuración del botón "Agregar materia"
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agregarMateria();
            }
        });

        // Manejo de Insets (opcional)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    /**
     * Método para agregar una nueva asignatura dinámicamente.
     */
    private void agregarMateria() {
        if (contadorMaterias >= MAX_MATERIAS) {
            Toast.makeText(this, "Has alcanzado el número máximo de materias", Toast.LENGTH_SHORT).show();
            return;
        }

        // Crear un nuevo LinearLayout para la nueva asignatura
        LinearLayout materiaNueva = new LinearLayout(this);
        materiaNueva.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams materiaLayoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        materiaLayoutParams.setMargins(0, 8, 0, 8); // Márgenes superior e inferior
        materiaNueva.setLayoutParams(materiaLayoutParams);

        // Crear EditText para el nombre de la asignatura
        EditText editTextNombreAsignatura = new EditText(this);
        editTextNombreAsignatura.setHint("Materia");
        LinearLayout.LayoutParams nombreParams = new LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1.0f
        );
        nombreParams.setMargins(0, 0, 8, 0); // Margen a la derecha
        editTextNombreAsignatura.setLayoutParams(nombreParams);

        // Crear EditText para la nota media de la asignatura
        EditText editTextNotaMediaAsignatura = new EditText(this);
        editTextNotaMediaAsignatura.setHint("Nota Media");
        editTextNotaMediaAsignatura.setInputType(android.text.InputType.TYPE_CLASS_NUMBER | android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL);
        LinearLayout.LayoutParams notaParams = new LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1.0f
        );
        editTextNotaMediaAsignatura.setLayoutParams(notaParams);

        // Agregar los EditText al nuevo LinearLayout
        materiaNueva.addView(editTextNombreAsignatura);
        materiaNueva.addView(editTextNotaMediaAsignatura);

        // Agregar el nuevo LinearLayout al contenedor principal
        ll.addView(materiaNueva);

        contadorMaterias++;

        // Verificar si se alcanzó el límite
        if (contadorMaterias >= MAX_MATERIAS) {
            agregar.setEnabled(false);
            Toast.makeText(this, "Máximo de materias agregadas", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Método para guardar los datos ingresados por el usuario.
     */
    private void guardarDatos() {
        String nombre = nombreEditText.getText().toString().trim();
        String edadStr = edadEditText.getText().toString().trim();
        String notaMStr = notaMEditText.getText().toString().trim();

        if (nombre.isEmpty() || edadStr.isEmpty() || notaMStr.isEmpty()) {
            Toast.makeText(this, "Por favor, rellena todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        int edad;
        float notaM;

        try {
            edad = Integer.parseInt(edadStr);
            notaM = Float.parseFloat(notaMStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Por favor, ingresa números válidos para edad y nota media", Toast.LENGTH_SHORT).show();
            return;
        }

        Estudiante es = new Estudiante(nombre, edad, notaM);

        // Recoger las materias agregadas dinámicamente
        if (contadorMaterias > 0) {
            int childCount = ll.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = ll.getChildAt(i);
                if (child instanceof LinearLayout) {
                    LinearLayout materiaLayout = (LinearLayout) child;
                    if (materiaLayout.getChildCount() >= 2) {
                        EditText materiaEditText = (EditText) materiaLayout.getChildAt(0);
                        EditText notaEditText = (EditText) materiaLayout.getChildAt(1);
                        String materiaNombre = materiaEditText.getText().toString().trim();
                        String materiaNotaStr = notaEditText.getText().toString().trim();

                        if (!materiaNombre.isEmpty() && !materiaNotaStr.isEmpty()) {
                            try {
                                float materiaNota = Float.parseFloat(materiaNotaStr);
                                es.listaMaterias.add(new Materia(materiaNombre, materiaNota));
                            } catch (NumberFormatException e) {
                                Toast.makeText(this, "Nota de materia inválida para " + materiaNombre, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            }
        }

        // Iniciar SecondaryActivity con el objeto Estudiante
        Intent i = new Intent(MainActivity.this, SecondaryActivity.class);
        i.putExtra("obj1", es);
        startActivity(i);
    }
}
