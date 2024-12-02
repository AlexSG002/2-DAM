package com.pmdm.secondaryactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private String nombre;
    private String ciudad;
    private int edad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Button b =findViewById(R.id.buttonEntrar);
        TextView txtNombre = findViewById(R.id.editTextNombre);
        nombre = (String) txtNombre.getText();
        TextView txtCiudad = findViewById(R.id.editTextCiudad);
        ciudad = (String) txtCiudad.getText();
        TextView txtEdad = findViewById(R.id.editTextEdad);
        edad = Integer.parseInt(String.valueOf(txtEdad));

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), SummaryActivity.class);
                i.putExtra("NOMBRE",nombre);
                i.putExtra("CIUDAD",ciudad);
                i.putExtra("EDAD",edad);
                startActivity(i);
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}