package com.pmdm.archivos;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LineAdapter.ListenerLineaBorrada {
    //Declaramos e inicializamos variables locales.
    private int COD_SELECCION_ARCHIVOS = 1;
    private int COD_CREAR_ARCHIVO = 2;
    private List<String> lineas = new ArrayList<>();
    private TextView contadorPalabras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Declaramos e inicializamos el recycler view a una nueva instancia de LinearLayout.
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //Declaramos botones.
        Button buttonSeleccionar = findViewById(R.id.buttonSeleccionarArchivo);
        Button buttonGuardar = findViewById(R.id.buttonGuardarArchivo);
        Button buttonLeerArchivo = findViewById(R.id.buttonLeer);
        Button buttonSumarLinea = findViewById(R.id.buttonSumarLinea);
        //Declaramos campos de texto.
        EditText editTextRuta = findViewById(R.id.editTextRuta);
        EditText editTextSumarLinea = findViewById(R.id.editTextSumarLinea);
        //Inicializamos el contador de palabras al elemento de texto.
        contadorPalabras = findViewById(R.id.textViewContador);
        //Botón para guardar que lanza un intent de tipo crear documento.
        buttonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentCrear = new Intent(Intent.ACTION_CREATE_DOCUMENT);
                //Añadimos la categoría para que sean solo documentos que se pueden abrir.
                intentCrear.addCategory(Intent.CATEGORY_OPENABLE);
                //Añadimos la categoría para que sean solo documentos de texto.
                intentCrear.setType("text/plain");
                //Añadimos un título por defecto.
                intentCrear.putExtra(Intent.EXTRA_TITLE, "modificado.txt");
                //Lanzamos el intent a la actividad de guardado de archivos.
                startActivityForResult(intentCrear, COD_CREAR_ARCHIVO);
            }
        });
        //Botón para añadir una línea.
        buttonSumarLinea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Comprobamos que exista texto en el campo.
                if(editTextSumarLinea != null && !editTextSumarLinea.getText().toString().isEmpty()){
                    //Obtenemos el texto del campo.
                    String linea = editTextSumarLinea.getText().toString();
                    //Lo añadimos al array de lineas.
                    lineas.add(linea);
                    //Declaramos una nueva instancia del LineAdapter y la inicializamos en esta actividad y con las líneas que ya tenemos.
                    LineAdapter adapter = new LineAdapter(lineas, MainActivity.this);
                    //Le ponemos el adaptador al recyclerView.
                    recyclerView.setAdapter(adapter);
                    //Actualizamos el contador de palabras.
                    actualizarContador(contadorPalabras);
                }else{
                    Toast.makeText(MainActivity.this, "No puedes añadir una línea vacía", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //Botón para seleccionar un archivo.
        buttonSeleccionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Declaramos el intent para ir a la selección de documentos
                Intent i = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                //Establecemos que sean documentos abribles y de texto plano.
                i.addCategory(Intent.CATEGORY_OPENABLE);
                i.setType("text/plain");
                //Lanzamos el selector de archivos.
                startActivityForResult(i, COD_SELECCION_ARCHIVOS);
                //Obtenemos la ruta del archivo seleccionado.
                String ruta = getRaiz().getAbsolutePath().toString();
                //La ponemos en el editText.
                editTextRuta.setText(ruta);
            }
        });
        //Botón para leer el archivo seleccionado.
        buttonLeerArchivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Comprobamos que la ruta no este vacía.
                if(editTextRuta != null && !editTextRuta.getText().toString().isEmpty()){
                    //Obtenemos la dirección de la ruta.
                    String ruta = editTextRuta.getText().toString();
                    //Limpiamos la lista de lineas.
                    lineas.clear();
                    try{
                        //Declaramos una uri y la inicializamos a la ruta del documento parseada.
                        Uri uriArchivo = Uri.parse(ruta);
                        //Declaramos e inicializamos un InputStream con getContent de la uri
                        InputStream is = getContentResolver().openInputStream(uriArchivo);
                        //Declaramos un BufferedReader y lo inicializamos a un nuevo InputStreamReader
                        //Para leer lo que vayamos cogiendo del InputStream.
                        BufferedReader br = new BufferedReader(new InputStreamReader(is));
                        //Declaramos una variable línea para ir separando y añadiendo el texto.
                        String linea;
                        //Mientras haya una línea
                        while((linea = br.readLine()) != null){
                            //añadimos la línea a la lista.
                            lineas.add(linea);
                        }
                        //Cuando no hayan más líneas cerramos el BufferedReader.
                        br.close();
                        //Declaramos una nueva instancia de LineAdapter en MainActivity con las líneas obtenidas del documento.
                        LineAdapter adapter = new LineAdapter(lineas, MainActivity.this);
                        //Establecemos el adaptador al recyclerView.
                        recyclerView.setAdapter(adapter);
                        //Actualizamos el contador de palabras.
                        actualizarContador(contadorPalabras);
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
    //Método para obtener la raíz del documento.
    File getRaiz(){
        File raiz;
        raiz = getApplicationContext().getFilesDir();

        return raiz;
    }
    //Método para actualizar el contador de palabras
    private void actualizarContador(TextView contadorPalabras){
        //Declaramos el total de palabras
        int totalPalabras = 0;
        //Por cada línea en la lista de lineas
        for(String linea : lineas){
            //Comprobamos si la línea no está vacía
            if(!linea.trim().isEmpty()){
                //Separamos las palabras con la expresión regular \\s
                String[] palabras = linea.trim().split("\\s+");
                //Por cada separación existente añadimos una palabra.
                totalPalabras += palabras.length;
            }
        }
        //Actualizamos el TextView.
        contadorPalabras.setText("Contador de palabras: "+ totalPalabras);
    }

    //Al resultado de la selección o creación de nuevo documento.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Si se está seleccionando el archivo.
        if(requestCode == COD_SELECCION_ARCHIVOS && resultCode == RESULT_OK){
            //Obtenemos la uri del archivo del resulado del intent, es decir del documento seleccionado.
        Uri uriArchivo = data.getData();
            if (uriArchivo != null) {
                //Establecemos en el campo de ruta la dirección del documento.
                EditText editTextRuta = findViewById(R.id.editTextRuta);
                editTextRuta.setText(uriArchivo.toString());
                //Limpiamos las líneas.
                lineas.clear();
            }
        }
        //Si se está guardando el archivo.
        if(requestCode == COD_CREAR_ARCHIVO && resultCode == RESULT_OK){
            //Obtenemos la uri del archivo.
            Uri nuevaUri = data.getData();
            if(nuevaUri != null){
                //Declaramos un nuevo StringBuilder para añadir las nuevas líneas añadidas como parte del documento a las líneas existentes.
                StringBuilder builder = new StringBuilder();
                for (String linea : lineas){
                    //Utilizando la función append en todas las líneas por cada línea del documento.
                    builder.append(linea).append("\n");
                }
                //Llamamos contenido a lo construido con el StringBuilder y lo pasamos a String.
                String contenido = builder.toString();
                try{
                    //Con OutputStream y el getContentResolver abrimos un nuevo OutputStream para modificar en el archivo que es nuevaUri y lo ponemos en modo w(write).
                    OutputStream os = getContentResolver().openOutputStream(nuevaUri, "w");
                    //Comprobmaos que el OutputStream no sea nulo.
                    if(os != null){
                        //Escribimos el contenido con os.write en carácteres con codificación UTF-8.
                        os.write(contenido.getBytes(StandardCharsets.UTF_8));
                        //Limpiamos la salida y cerramos.
                        os.flush();
                        os.close();
                        Toast.makeText(MainActivity.this, "Archivo guardado",Toast.LENGTH_SHORT).show();
                    }
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }
    //Función de listener para actualizar el contasdor de palabras al borrarse una línea.
    @Override
    public void lineaBorrada() {
        actualizarContador(contadorPalabras);
    }
}