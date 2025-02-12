package com.pmdm.nuevasPreferencias;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {

    private EditText edNombre;
    private EditText edEmail;
    private EditText edEmpresa;
    private EditText edEdad;
    private EditText edSueldo;
    private SharedPreferences misPreferencias;
    //Añadido un botón para acceder a la pestaña de detalles.
    private Button buttonDetalles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edNombre = findViewById(R.id.edNombre);
        edEmail = findViewById(R.id.edEmail);
        edEmpresa = findViewById(R.id.edEmpresa);
        edEdad = findViewById(R.id.edEdad);
        edSueldo = findViewById(R.id.edSueldo);

        //Obtenemos los datos basicos de las preferencias.
        misPreferencias = getSharedPreferences("Basic Data", MODE_PRIVATE);

        String nombre = misPreferencias.getString("nombre", "");
        String empresa = misPreferencias.getString("empresa", "Ribera del Tajo");
        String email = misPreferencias.getString("email", "cambiame@riberadeltajo.es");
        int edad = misPreferencias.getInt("edad", 18);
        float sueldo = misPreferencias.getFloat("sueldo", 15000);

        //Ponemos el nombre del último contacto guardado al volver a entrar.
        TextView tvBienvenida = findViewById(R.id.tvBienvenida);
        tvBienvenida.setText("Bienvenido " + nombre);

        edNombre.setText(nombre);
        edEmpresa.setText(empresa);
        edEmail.setText(email);
        edSueldo.setText(String.valueOf(sueldo));
        edEdad.setText(String.valueOf(edad));

        //Obtenemos las preferencias para detectar el modo nocturno.
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        boolean modoNocturno = prefs.getBoolean("modoNocturno", false);
        if (modoNocturno) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        Button btnGuardar = findViewById(R.id.btnGuardar);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = misPreferencias.edit();
                editor.putString("nombre", edNombre.getText().toString());
                editor.putString("empresa", edEmpresa.getText().toString());
                editor.putString("email", edEmail.getText().toString());
                try {
                    editor.putInt("edad", Integer.parseInt(edEdad.getText().toString()));
                    editor.putFloat("sueldo", Float.parseFloat(edSueldo.getText().toString()));
                } catch (Exception e) {
                    //Error de conversión
                }
                if (!editor.commit())
                    Toast.makeText(getApplicationContext(), "Error al grabar las preferencias", Toast.LENGTH_SHORT).show();

            }
        });

        Button btnSiguiente = findViewById(R.id.btnSiguiente);
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity2.class);
                startActivity(i);
            }
        });
        //El botón detalles nos lleva a la Actividad Detalles.
        buttonDetalles = findViewById(R.id.buttonDetalles);
        buttonDetalles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), DetallesActivity.class);
                startActivity(i);
            }
        });

    }


}