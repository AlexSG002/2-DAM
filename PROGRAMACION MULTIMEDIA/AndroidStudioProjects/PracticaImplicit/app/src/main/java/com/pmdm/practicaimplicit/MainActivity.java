package com.pmdm.practicaimplicit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private ActivityResultLauncher<Intent> launcher;
    private String retornoWeb="";
    private String retornoCorreo="";
    private String retornoAsunto="";
    private String retornoMensaje="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        retornoWeb = sharedPreferences.getString("url", "");
        Button bWeb = findViewById(R.id.buttonWeb);
        Button bCorreo = findViewById(R.id.buttonCorreo);
        Button bFoto = findViewById(R.id.buttonFoto);
        Button bConfig = findViewById(R.id.buttonConfigurar);

        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        if(result.getData().hasExtra("WEB")) {
                            retornoWeb = result.getData().getStringExtra("WEB");
                        }else{
                            retornoWeb = "";
                        }
                        if(result.getData().hasExtra("CORREO")) {
                            retornoCorreo = result.getData().getStringExtra("CORREO");
                            retornoAsunto = result.getData().getStringExtra("ASUNTO");
                            retornoMensaje = result.getData().getStringExtra("MENSAJE");
                        }else{
                            retornoCorreo="";
                            retornoMensaje="";
                            retornoAsunto="";
                        }
                    }

                }
        );

        bWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(retornoWeb.isEmpty()){
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"));
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://" + retornoWeb));
                    startActivity(intent);
                }

            }
        });

        bCorreo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(retornoAsunto == null|| retornoAsunto.isEmpty() || retornoCorreo == null || retornoCorreo.isEmpty() || retornoMensaje == null || retornoMensaje.isEmpty()){
                    Toast.makeText(MainActivity.this,"ERROR, NO SE HA CONFIGURADO EL CORREO", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("message/rfc822");
                    intent.putExtra(Intent.EXTRA_EMAIL,new String[]{retornoCorreo});
                    intent.putExtra(Intent.EXTRA_SUBJECT,retornoAsunto);
                    intent.putExtra(Intent.EXTRA_TEXT, retornoMensaje);
                    startActivity(intent);
                }
            }
        });

        bFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent foto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(foto);
            }
        });

        bConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), SecondaryActivity.class);
                launcher.launch(i);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}