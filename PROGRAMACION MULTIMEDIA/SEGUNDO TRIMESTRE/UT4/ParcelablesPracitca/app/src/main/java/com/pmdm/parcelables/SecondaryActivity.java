package com.pmdm.parcelables;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_secondary);
        TextView t = findViewById(R.id.textView);
        TextView tNom = findViewById(R.id.textViewNombre);
        TextView tEdad = findViewById(R.id.textViewEdad);
        TextView tNotaM = findViewById(R.id.textViewNota);
        Intent i = getIntent();
        Estudiante es = i.getParcelableExtra("obj1");
        String nombre = i.getStringExtra("NOMBRE");
        String edad = i.getStringExtra("EDAD");
        String nota = i.getStringExtra("NOTAM");
        Materia m = i.getParcelableExtra("MATERIA");
        t.setText(es.toString());
        tNom.setText("Nombre: "+nombre);
        tEdad.setText("Edad: "+edad);
        tNotaM.setText("Nota media: "+nota);
    }
}