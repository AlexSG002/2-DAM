package com.pmdm.parcelables;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.ParseException;

public class MainActivity extends AppCompatActivity {
    private Button agregar;
    private Button guardar;
    private EditText nombreEditText;
    private EditText edadEditText;
    private EditText notaMEditText;
    private boolean materiasAñadidas = false;
    private String nombre;
    private int edad;
    private int notaM;
    private LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        agregar = findViewById(R.id.buttonAgregarMateria);
        guardar = findViewById(R.id.buttonGuardar);
        nombreEditText = findViewById(R.id.editTextNombre);
        edadEditText = findViewById(R.id.editTextEdad);
        notaMEditText = findViewById(R.id.editTextNotaMedia);
        ll = findViewById(R.id.LinearLayout);

        View view = getLayoutInflater().inflate(R.layout.activity_main, ll,false);


        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nombre = nombreEditText.getText().toString();
                edad = Integer.parseInt(String.valueOf(edadEditText.getText()));
                notaM = Integer.parseInt(String.valueOf(notaMEditText.getText()));
                Estudiante es = null;
                try {
                    es = new Estudiante(nombre, edad, notaM);
                    if (materiasAñadidas) {
                        es.listaMaterias.add(new Materia(null, 0));
                    }

                    Intent i = new Intent(MainActivity.this, SecondaryActivity.class);
                    i.putExtra("obj1", es);
                    startActivity(i);
                }catch (NumberFormatException e){
                    Toast.makeText(MainActivity.this,"No has rellenado correctamente los campos", Toast.LENGTH_SHORT);
                    e.printStackTrace();
                }

            }
        });

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                materiasAñadidas = true;
                EditText nuevaMateria = new EditText(MainActivity.this);
                ll.addView(nuevaMateria);
                EditText nuevaNota = new EditText(MainActivity.this);
                ll.addView(nuevaNota);
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}