package com.pmdm.practicaimplicit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondaryActivity extends AppCompatActivity {


    private Button bGuardarURL;
    private EditText txtURL;
    private Button bGuardarCorreo;
    private EditText txtCorreo;
    private EditText txtAsunto;
    private EditText txtMensaje;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_secondary);

        bGuardarURL = findViewById(R.id.buttonGuardarURL);
        txtURL = findViewById(R.id.editTextURL);

        bGuardarCorreo = findViewById(R.id.buttonGuardarCorreo);
        txtCorreo = findViewById(R.id.editTextEmail);

        txtAsunto = findViewById(R.id.editTextAsunto);
        txtMensaje = findViewById(R.id.editTextMensaje);
        bGuardarURL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtURL == null || txtURL.getText().toString().isEmpty()){
                    Toast.makeText(SecondaryActivity.this,"La url no puede estar vacía", Toast.LENGTH_SHORT).show();
                }else{
                SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                String url = sharedPreferences.getString("URL","www.google.com");
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("url", txtURL.getText().toString());
                editor.apply();
                Intent intentURL = new Intent();
                intentURL.putExtra("WEB", txtURL.getText().toString());
                setResult(RESULT_OK, intentURL);
                finish();
                }
            }
        });

        bGuardarCorreo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean entradaValida = true;
                if(txtCorreo == null ||txtCorreo.getText().toString().isEmpty()){
                    Toast.makeText(SecondaryActivity.this,"El correo no puede estar vacío", Toast.LENGTH_SHORT).show();
                    entradaValida = false;
                }
                if(txtAsunto == null || txtAsunto.getText().toString().isEmpty()){
                    Toast.makeText(SecondaryActivity.this,"El asunto no puede estar vacío", Toast.LENGTH_SHORT).show();
                    entradaValida = false;
                }
                if(txtMensaje == null || txtMensaje.getText().toString().isEmpty()){
                    Toast.makeText(SecondaryActivity.this,"El mensaje no puede estar vacío", Toast.LENGTH_SHORT).show();
                    entradaValida = false;
                }
                if(entradaValida && correoValido(txtCorreo.getText().toString())) {
                    Intent intentCorreo = new Intent();
                    intentCorreo.putExtra("CORREO", txtCorreo.getText().toString());
                    intentCorreo.putExtra("ASUNTO", txtAsunto.getText().toString());
                    intentCorreo.putExtra("MENSAJE", txtMensaje.getText().toString());
                    setResult(RESULT_OK, intentCorreo);
                    finish();
                }else{
                    Toast.makeText(SecondaryActivity.this,"Introduce un correo válido", Toast.LENGTH_SHORT).show();
                }
            }
        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private boolean correoValido(String correo){
        int posicionArroba=-1;
        int posicionPunto=-1;
        if(correo.contains("@") && correo.contains(".")){
            char[] arrayCorreo = correo.toCharArray();
            for(int i=0; i < arrayCorreo.length;i++){
                if(arrayCorreo[i] == '@'){
                    posicionArroba = i;
                    break;
                }
            }
            for(int i=0; i < arrayCorreo.length; i++){
                if(arrayCorreo[i] == '.') {
                    posicionPunto = i;
                    break;
                }
            }
            if(posicionArroba+1<posicionPunto && posicionArroba!=0){
                return true;
            }
        }
        return false;
    }
}