package com.pmdm.ut1cronometrotabata;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
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

    @Override
    public void onClick(View view) {
        txtSeries.setText("SERIES LEFT: " + edtSeries.getText());
        contador = new CountDownTimer(Long.parseLong(edtTrabajo.getText().toString()), 1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {

            }
        };

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

        playButton.setOnClickListener(this);


        for (int i = 0; i < Integer.parseInt(edtSeries.getText().toString()); i++) {

        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.constraintLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
