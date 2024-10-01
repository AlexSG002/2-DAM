package com.pmdm.ciclovida;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Log.i("TAG CICLOVIDA", "CICLO DE VIDA : ONCREATE");
        i++;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("TAG CICLOVIDA", "CICLO DE VIDA : ONSTART");
        i++;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.wtf("TAG CICLOVIDA", "CICLO DE VIDA : ONRESUME");
        i++;
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("TAG CICLOVIDA", "CICLO DE VIDA : ONPAUSE");
        i--;
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("TAG CICLOVIDA", "CICLO DE VIDA : ONSTOP");
    }
}