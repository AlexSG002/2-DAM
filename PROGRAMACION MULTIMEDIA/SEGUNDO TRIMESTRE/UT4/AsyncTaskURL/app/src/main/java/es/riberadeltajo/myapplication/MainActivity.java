package es.riberadeltajo.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    //Declaramos las variables que vamos a usar.
    private TextView txtDescarga;
    private ExecutorService executorService;
    private Handler mainHandler;
    private static Button bDescargarImagen;
    private Button bDescargarURL;
    private Button bReset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Inicializamos el executor service y el handler.
        executorService = Executors.newSingleThreadExecutor();
        mainHandler = new Handler(Looper.getMainLooper());
        bDescargarImagen = findViewById(R.id.btnDescargar2);
        bDescargarURL = findViewById(R.id.btnDescargar);
        bReset = findViewById(R.id.buttonReset);
        //Botón para resetar las imágenes.
        bReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Método dentro de la Secondary Activity que resetea las imágenes.
                SecondaryActivity.resetearImagenes();
                Toast.makeText(MainActivity.this, "Se han reseteado las imágenes.", Toast.LENGTH_SHORT).show();
            }
        });


        //Al pulsar el botón descargar imagen ejecutamos el método DescargarImagen().
        bDescargarImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DescargarImagen();
                //Limpiamos el texto de la URL
                EditText editTextImagen = findViewById(R.id.editTextImagen);
                editTextImagen.setText("");
            }
        });
        //Cambié el tipo de entrada del evento onClickListener del método por defecto.
        bDescargarURL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Descargar();
            }
        });
    }
    //Método para comprobar que las imagenes sean válidas.
    public boolean comprobarImagen(String url){
        //Declaramos las extensiones válidas, yo he optado por poner jpeg, jpg y png.
        String[] extensionesValidas = {".jpeg",".jpg",".png"};
        //Comprobamos
        for(String extension : extensionesValidas){
            if(url.toLowerCase().endsWith(extension)){
                return true;
            }
        }
        return false;
    }

    public boolean comprobarTexto(String url){
        String extensionTXT = ".txt";

        if(url.toLowerCase().endsWith(extensionTXT)){
            return true;
        }
        return false;
    }

    public void Descargar() {
        EditText edURL = findViewById(R.id.edURL);
        txtDescarga = findViewById(R.id.txtDescarga);
        txtDescarga.setMovementMethod(new ScrollingMovementMethod());

        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            String url = edURL.getText().toString();
            if(comprobarTexto(url)) {
                executorService.execute(new DescargaPaginaWeb(url));
            }else{
                txtDescarga.setText("La url introducida no es un texto (extensión .txt)");
            }
        } else {
            txtDescarga.setText("No se ha podido establecer conexión a internet");
        }
    }
    //Método para descargar las imágenes desde url
    public void DescargarImagen(){
        EditText editTextImagen = findViewById(R.id.editTextImagen);
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            String url = editTextImagen.getText().toString();
            //Comprobamos que la imagen sea válida
            if(comprobarImagen(url)) {
                //Ejecutamos la clase DescargarImagen que contiene el método Run e implementa Runnable como un hilo.
                executorService.execute(new DescargarImagen(url));
            }else{
                Toast.makeText(MainActivity.this, "Introduce una URL de imagen válida (jpg, jpeg o png).",Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(MainActivity.this, "No se ha podido establecer conexión a internet",Toast.LENGTH_SHORT).show();
        }
    }

    private class DescargarImagen implements Runnable{
    private final String url;

        private DescargarImagen(String url) {
            this.url = url;
        }

        @Override
        public void run() {
            try{
                //Declaramos un array de bytes para formar las imágenes y lo inicializamos a lo que devuelva el método descargaImagen en función de la url.
                byte[] imagenBytes = descargaImagen(url);
                //Declaramos e inicializamos un nuevo intent de la Main activity a la Secondary Activity.
                Intent i = new Intent(MainActivity.this, SecondaryActivity.class);
                //Le pasamos la imagen como dato.
                i.putExtra("IMAGEN",imagenBytes);
                //Iniciamos la activity con el método post.
                mainHandler.post(()->startActivity(i));

                //Con este catch atrapamos los errores en caso de que lo introducido no sea una url.
            }catch (IOException e){
                mainHandler.post(()->Toast.makeText(MainActivity.this, "Introduce una URL correcta(htps://www.ejemplo.png/jpg/jpeg)",Toast.LENGTH_SHORT).show());
            }
        }
        //Método que devuelve un array de bytes, obtiene la imagen de la url y la va construyendo en bloques bytes de 1024 bytes.
        private byte[] descargaImagen(String myurl) throws IOException {
            //Establecemos el inputStream a null.
            InputStream is = null;
            //Declaramos un buffer de tipo ByteArrayOutputStream, para ir cargando las imagenes en bloques e ir construyendola poco a poco antes de tener la imagen completa en bytes.
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            try {
                //Obtenemos la url.
                URL url = new URL(myurl);
                //Nos conectamos con protocolo HTTP.
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);

                conn.connect();
                int response = conn.getResponseCode();
                //Si la conexión no fue existosa.
                if (response != HttpURLConnection.HTTP_OK) {
                    throw new IOException("Error en la conexión: " + response);
                }
                //Si la conexión fue exitosa obtenemos la entrada de la conexión es decir la imagen.
                is = conn.getInputStream();
                //Declaramos un nuevo array de bytes de nombre datos y de tamaño 1024, estos son nuestros bloques, que iremos rellenando de "imagen" poco a poco.
                byte[]datos = new byte[1024];
                //Declaramos un entero nLeido para continuar el siguiente byte en el último leído y para leer hasta el final de bytes.
                int nLeido;

                //Mientras nLeido sea leido, leemos datos hasta el final de los datos mientras nLeido sea mayor que 0, el fin del flujo llega cuando los datos son iguales a -1.
                while((nLeido = is.read(datos, 0, datos.length))!=-1){
                    //Escribimos en el buffer los datos y lo leido.
                    buffer.write(datos, 0, nLeido);
                }
                //Devolvemos el buffer como un array de bytes, que sería la imagen completa.
                return buffer.toByteArray();
            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    private class DescargaPaginaWeb implements Runnable {
        private final String url;

        public DescargaPaginaWeb(String url) {
            this.url = url;
        }

        @Override
        public void run() {
            try {
                String resultado = descargaUrl(url);

                mainHandler.post(() -> txtDescarga.setText(resultado));
            } catch (IOException e) {
                mainHandler.post(() -> txtDescarga.setText("Imposible cargar la web! URL mal formada"));
            }
        }

        private String Leer(InputStream is) {
            try {
                ByteArrayOutputStream bo = new ByteArrayOutputStream();
                int i = is.read();
                while (i != -1) {
                    bo.write(i);
                    i = is.read();
                }
                return bo.toString();
            } catch (IOException e) {
                return "";
            }
        }

        private String descargaUrl(String myurl) throws IOException {
            InputStream is = null;
            try {
                URL url = new URL(myurl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);

                conn.connect();
                int response = conn.getResponseCode();
                if (response != HttpURLConnection.HTTP_OK) {
                    throw new IOException("Error en la conexión: " + response);
                }
                is = conn.getInputStream();

                return Leer(is);
            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executorService.shutdown();
    }
    //Método para cambiar el texto del botón en la otra clase al pulsar volver.
    public static void cambiarTextoBoton(){
        //Cambiamos el texto del botón.
        bDescargarImagen.setText("Volver a descargar");
    }
}
//Foto del bicho: https://www.hola.com/horizon/square/e48159e847bc-cristiano-ronaldo.jpg
//Foto de messi: https://s.france24.com/media/display/451ed2b8-eed6-11ea-afdd-005056bf87d6/w:1280/p:16x9/messi-1805.jpg
//Foto de dinho: https://estaticos-cdn.prensaiberica.es/clip/ae1f1131-ae9d-490d-999b-1e702bf4c109_alta-libre-aspect-ratio_default_0.jpg
