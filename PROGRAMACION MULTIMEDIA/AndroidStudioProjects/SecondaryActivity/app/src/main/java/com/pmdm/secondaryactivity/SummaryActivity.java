package com.pmdm.secondaryactivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SummaryActivity extends AppCompatActivity {

    private ActivityResultLauncher<Intent> launcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_summary);

        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode() == RESULT_OK && result.getData()!=null){
                        String nombre = result.getData().getStringExtra("NOMBRE");
                        String ciudad = result.getData().getStringExtra("CIUDAD");
                        int edad = result.getData().getIntExtra("EDAD",0);
                        TextView txtNombre = findViewById(R.id.textViewNombre);
                        TextView txtCiudad = findViewById(R.id.textViewCiudad);
                        TextView txtEdad = findViewById(R.id.textViewEdad);
                        txtNombre.setText("Nombre: "+ nombre);
                        txtCiudad.setText("Ciudad: "+ciudad);
                        txtEdad.setText("Edad: "+ edad);
                    }
                }
        );

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}