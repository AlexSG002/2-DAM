package com.pmdm.archivos;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private int COD_SELECCION_ARCHIVOS = 1;
    private List<String> lineas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Button buttonSeleccionar = findViewById(R.id.buttonSeleccionarArchivo);
        Button buttonGuardar = findViewById(R.id.buttonGuardarArchivo);
        Button buttonLeerArchivo = findViewById(R.id.buttonLeer);
        Button buttonSumarLinea = findViewById(R.id.buttonSumarLinea);

        EditText editTextRuta = findViewById(R.id.editTextRuta);
        EditText editTextSumarLinea = findViewById(R.id.editTextSumarLinea);

        buttonSumarLinea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextSumarLinea != null || !editTextSumarLinea.getText().toString().isEmpty()){
                    String linea = editTextSumarLinea.getText().toString();
                    lineas.add(linea);
                    LineAdapter adapter = new LineAdapter(lineas);
                    recyclerView.setAdapter(adapter);
                }else{
                    Toast.makeText(MainActivity.this, "No puedes añadir una línea vacía", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonSeleccionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ruta = getRaiz().getAbsolutePath().toString();
                editTextRuta.setText(ruta);
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.setType("text/plain");
                startActivityForResult(i, COD_SELECCION_ARCHIVOS);
            }
        });

        buttonLeerArchivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextRuta != null || !editTextRuta.getText().toString().isEmpty()){
                    String ruta = editTextRuta.getText().toString();
                    try{
                        Uri uriArchivo = Uri.parse(ruta);
                        InputStream is = getContentResolver().openInputStream(uriArchivo);
                        BufferedReader br = new BufferedReader(new InputStreamReader(is));
                        String linea;
                        while((linea = br.readLine()) != null){
                            lineas.add(linea);
                        }
                        br.close();
                        LineAdapter adapter = new LineAdapter(lineas);
                        recyclerView.setAdapter(adapter);
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }else{
                    Toast.makeText(MainActivity.this, "La ruta no puede estar vacía.", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    File getRaiz(){
        File raiz;
        raiz = getApplicationContext().getFilesDir();

        return raiz;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == COD_SELECCION_ARCHIVOS && resultCode == RESULT_OK);
        Uri uriArchivo = data.getData();
        if (uriArchivo != null) {
            EditText editTextRuta = findViewById(R.id.editTextRuta);
            editTextRuta.setText(uriArchivo.toString());
        }
    }
}