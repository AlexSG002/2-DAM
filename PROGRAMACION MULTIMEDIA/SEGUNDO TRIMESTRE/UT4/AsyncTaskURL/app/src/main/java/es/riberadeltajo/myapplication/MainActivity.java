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

    private TextView txtDescarga;
    private ExecutorService executorService;
    private Handler mainHandler;
    Button bDescargarImagen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        executorService = Executors.newSingleThreadExecutor();
        mainHandler = new Handler(Looper.getMainLooper());
        bDescargarImagen = findViewById(R.id.btnDescargar2);

        bDescargarImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DescargarImagen();
                EditText editTextImagen = findViewById(R.id.editTextImagen);
                editTextImagen.setText("");
            }
        });
    }

    public void Descargar(View v) {
        EditText edURL = findViewById(R.id.edURL);
        txtDescarga = findViewById(R.id.txtDescarga);
        txtDescarga.setMovementMethod(new ScrollingMovementMethod());

        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            String url = edURL.getText().toString();
            executorService.execute(new DescargaPaginaWeb(url));
        } else {
            txtDescarga.setText("No se ha podido establecer conexión a internet");
        }
    }

    public void DescargarImagen(){
        EditText editTextImagen = findViewById(R.id.editTextImagen);
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            String url = editTextImagen.getText().toString();
            executorService.execute(new DescargarImagen(url));
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
                byte[] imagenBytes = descargaImagen(url);

                Intent i = new Intent(MainActivity.this, SecondaryActivity.class);
                i.putExtra("IMAGEN",imagenBytes);
                mainHandler.post(()->startActivity(i));
            }catch (IOException e){
                mainHandler.post(()->Toast.makeText(MainActivity.this, "Error al descargar la imagen",Toast.LENGTH_SHORT).show());
            }
        }

        private byte[] descargaImagen(String myurl) throws IOException {
            InputStream is = null;
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            try {
                URL url = new URL(myurl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000 /* milisegundos */);
                conn.setConnectTimeout(15000 /* milisegundos */);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);

                conn.connect();
                int response = conn.getResponseCode();
                if (response != HttpURLConnection.HTTP_OK) {
                    throw new IOException("Error en la conexión: " + response);
                }
                is = conn.getInputStream();
                byte[]datos = new byte[1024];
                int nRead;

                while((nRead = is.read(datos, 0, datos.length))!=-1){
                    buffer.write(datos, 0, nRead);
                }

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
                conn.setReadTimeout(10000 /* milisegundos */);
                conn.setConnectTimeout(15000 /* milisegundos */);
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
}
//Foto del bicho: https://www.hola.com/horizon/square/e48159e847bc-cristiano-ronaldo.jpg
//Foto de messi: https://s.france24.com/media/display/451ed2b8-eed6-11ea-afdd-005056bf87d6/w:1280/p:16x9/messi-1805.jpg
