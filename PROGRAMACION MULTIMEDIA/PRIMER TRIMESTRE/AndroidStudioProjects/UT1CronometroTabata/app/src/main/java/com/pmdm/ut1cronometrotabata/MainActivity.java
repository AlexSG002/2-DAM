package com.pmdm.ut1cronometrotabata;

import android.graphics.Color;
import android.media.MediaPlayer;
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

public class MainActivity extends AppCompatActivity {

    ImageButton playButton;
    TextView txtSeries;
    TextView txtContador;
    TextView txtEstado;
    EditText edtSeries;
    EditText edtTrabajo;
    EditText edtDescanso;
    CountDownTimer contador;
    CountDownTimer contadorDescanso;
    ConstraintLayout layout;
    MediaPlayer mpBeep;
    MediaPlayer mpGong;

    public void playBeep(){
        mpBeep = MediaPlayer.create(
                getApplicationContext(), R.raw.beep);
        mpBeep.start();
    }

    public void playGong(){
        mpGong = MediaPlayer.create(getApplicationContext(), R.raw.gong);
        mpGong.start();
    }



    private void iniciarContadorTrabajo(int seriesNum, long tiempoTrabajo, long tiempoDescanso) {

        if (contador != null) {
            contador.cancel();
        }

        txtEstado.setText("WORKING");
        layout.setBackgroundResource(R.drawable.green);
        txtSeries.setText("SERIES LEFT: "+seriesNum);
        playBeep();
        contador = new CountDownTimer(tiempoTrabajo, 1000) {
            @Override
            public void onTick(long l) {
                txtContador.setText("" + l / 1000);
            }

            @Override
            public void onFinish() {
                txtEstado.setText("REST");
                layout.setBackgroundResource(R.drawable.red);
                iniciarContadorDescanso(seriesNum - 1, tiempoTrabajo, tiempoDescanso);
            }
        };
        contador.start();
    }

    private void iniciarContadorDescanso(int seriesNum, long tiempoTrabajo, long tiempoDescanso) {

        if (contadorDescanso != null) {
            contadorDescanso.cancel();
        }

        contadorDescanso = new CountDownTimer(tiempoDescanso, 1000) {
            @Override
            public void onTick(long l) {
                txtContador.setText("" + l / 1000);
            }

            @Override
            public void onFinish() {
                if (seriesNum > 0) {
                    iniciarContadorTrabajo(seriesNum, tiempoTrabajo, tiempoDescanso);
                } else {
                    txtEstado.setText("FINISHED");
                    layout.setBackgroundResource(R.drawable.gray);
                    txtSeries.setText("SERIES LEFT: 0");
                    playGong();
                }
            }
        };
        txtSeries.setText("SERIES LEFT: "+seriesNum);
        contadorDescanso.start();
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
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (validarEntradas()) {

                    String seriesRestantes = edtSeries.getText().toString();
                    int seriesNum = Integer.parseInt(seriesRestantes);
                    String trabajoTexto = edtTrabajo.getText().toString();
                    String descansoTexto = edtDescanso.getText().toString();


                    long tiempoTrabajo = (Long.parseLong(trabajoTexto)+1) * 1000;
                    long tiempoDescanso = (Long.parseLong(descansoTexto)+1) * 1000;


                    iniciarContadorTrabajo(seriesNum, tiempoTrabajo, tiempoDescanso);
                }
            }
        });
        layout.setBackgroundResource(R.drawable.gray);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.constraintLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
