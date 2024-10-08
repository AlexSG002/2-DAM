package com.pmdm.ut1cronometrotabata;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton playButton;
    TextView txtSeries;
    TextView txtContador;
    TextView txtEstado;
    EditText edtSeries;
    EditText edtTrabajo;
    EditText edtDescanso;
    CountDownTimer contador;
    ConstraintLayout layout;

    @Override
    public void onClick(View view) {
        String seriesRestantes = edtSeries.getText().toString();
        int seriesNum = Integer.parseInt(seriesRestantes);
        if (validarEntradas()) {
            String trabajoTexto = edtTrabajo.getText().toString();
            txtSeries.setText("SERIES LEFT: " + edtSeries.getText());

            layout.setBackgroundColor(Color.GREEN);

            long tiempoTrabajo = Long.parseLong(trabajoTexto);
            txtContador.setText(trabajoTexto);
            contador = new CountDownTimer(tiempoTrabajo, 1000) {
                @Override
                public void onTick(long l) {
                    while(tiempoTrabajo!=0){
                        
                        }
                }

                @Override
                public void onFinish() {
                    if (seriesNum == 0) {
                        txtEstado.setText("FINISHED");
                        layout.setBackgroundColor(Color.WHITE);
                    } else {
                        txtEstado.setText("REST");
                        layout.setBackgroundColor(Color.RED);
                    }
                }
            };
            contador.start();
        }
    }


    private boolean validarEntradas() {
        if (edtSeries.getText().toString().isEmpty()) {
            return false;
        }

        if (edtTrabajo.getText().toString().isEmpty()) {
            return false;
        }

        if (edtDescanso.getText().toString().isEmpty()) {
            return false;
        }

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        txtContador = findViewById(R.id.txtContador);
        txtEstado = findViewById(R.id.txtEstado);
        txtSeries = findViewById(R.id.txtSeries);
        playButton = findViewById(R.id.imbPlay);
        edtSeries = findViewById(R.id.edtSeries);
        edtTrabajo = findViewById(R.id.edtTrabajo);
        edtDescanso = findViewById(R.id.edtDescanso);
        layout = findViewById(R.id.constraintLayout);
        playButton.setOnClickListener(this);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.constraintLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
