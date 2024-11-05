package com.pmdm.contexto;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout layout;
    private ActivityResultLauncher<Intent> launcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        layout = findViewById(R.id.main);
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        int colorGuardado = sharedPreferences.getInt("backgroundColor", Color.WHITE);
        layout.setBackgroundColor(colorGuardado);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Button rojo = findViewById(R.id.buttonRojo);
        Button azul = findViewById(R.id.buttonAzul);


        rojo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout.setBackgroundColor(Color.RED);
                int colorSeleccionado = Color.RED;
                editor.putInt("backgroundColor",colorSeleccionado);
                editor.apply();
            }
        });

        azul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout.setBackgroundColor(Color.BLUE);
                int colorSeleccionado = Color.BLUE;
                editor.putInt("backgroundColor",colorSeleccionado);
                editor.apply();
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}