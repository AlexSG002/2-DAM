package es.riberadeltajo.myapplication;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    
    private ActivityResultLauncher<Intent> launcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        String retorno = result.getData().getStringExtra("SELECCION");
                        Toast.makeText(this, "El usuario seleccionó " + retorno, Toast.LENGTH_LONG).show();
                        TextView t = findViewById(R.id.textView);
                        t.setText(retorno);
                    }
                }
        );
        Button b = findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //abrir una segunda actividad!!! Actividad es un proceso distinto
                Intent i = new Intent(getApplicationContext(), SecondActivity.class);
                launcher.launch(i);
            }
        });
    }

}