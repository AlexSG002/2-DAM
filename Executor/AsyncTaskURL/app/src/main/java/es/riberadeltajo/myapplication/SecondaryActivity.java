package es.riberadeltajo.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SecondaryActivity extends AppCompatActivity {
    //Declaramos las variables que vamos a necesitar.
    private LinearLayout ll;
    private static final int MAX_IMAGENES = 3;
    //Este arrayList de array bytes será donde guardemos las imagenes que hemos procesado antes con el buffer.
    private static ArrayList<byte[]> imagenesBytes = new ArrayList<>();
    private static int contadorImagenes=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);
        ll = findViewById(R.id.LinearLayout);
        Button bVolver = findViewById(R.id.buttonVolver);
        //Obtenemos la imagen del intent que hemos establecido antes.
        byte[] nuevaImagenBytes = getIntent().getByteArrayExtra("IMAGEN");
        //Si la imagen no es nula.
        if (nuevaImagenBytes != null) {
            //Creamos un bitmap de la imagen decodificando los bytes con la herramienta BitmapFactory, en este caso solo para obtener el tamaño de la imagen.
            Bitmap bitmap = BitmapFactory.decodeByteArray(nuevaImagenBytes, 0, nuevaImagenBytes.length);
            //Obtengo el alto y el ancho para comprobar que cumple con los requisitos de tamaño.
            int ancho = bitmap.getWidth();
            int alto = bitmap.getHeight();
            //Validamos que la imagen cumpla los requisitos.
            if (validarImagenes(ancho, alto)) {
                //Comprobamos que al añadir la imagen no nos pasemos del máximo de imágenes.
                if (imagenesBytes.size() < MAX_IMAGENES) {
                    //Añadimos al array de bytes de imagen la nueva imagen.
                    imagenesBytes.add(nuevaImagenBytes);
                    contadorImagenes++;
                } else {
                    Toast.makeText(this, "El número máximo de imágenes es 3", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "La imagen no cumple con el tamaño mínimo de 200x200 px o el máximo de 1920x1080 px", Toast.LENGTH_SHORT).show();
            }
        }
        //Mostramos las imágenes.
        mostrarImagenes();
        //Volvemos a la activity principal
        bVolver.setOnClickListener(view -> {
            //Creamos un intent de la Secondary a la Main.
            Intent intent = new Intent(SecondaryActivity.this, MainActivity.class);
            //Establecemos una flag para que en vez de crear una activity nueva de main activity nos abra la ya existente.
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            //Iniciamos la activity.
            startActivity(intent);
            //Cambiamos el texto del botón con el método que hemos creado en MainActivity.
            MainActivity.cambiarTextoBoton();
            //Cambiamos el texto del contador de imágenes con el método que hemos creado en MainActivity.
            MainActivity.cambiarContador(contadorImagenes);
        });
    }


    //Método para mostrar las imágenes.
    private void mostrarImagenes() {
        //Borramos las vistas ya existentes para evitar problemas de imagenes solapadas.
        ll.removeAllViews();
        //Recorremos el arrayList de imagenes para ir "imprimiendo en la activity" las imágenes guardadas en el array.
        for (int i = 0; i < imagenesBytes.size(); i++) {
            //Obtenemos una imagen.
            byte[] imagenBytes = imagenesBytes.get(i);
            //Decodificamos una imagen y la convertimos a Bitmap esta vez para representarla en la activity.
            Bitmap bitmap = BitmapFactory.decodeByteArray(imagenBytes, 0, imagenBytes.length);
            //Declarmaos e inicializamos un objeto de tipo LinearLayout para añadirle las vistas de las imágenes.
            LinearLayout nuevaImagen = new LinearLayout(this);
            //Establecemos los parámetros de la vista.
            nuevaImagen.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams nuevaImagenParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            nuevaImagenParams.setMargins(250, 50,50, 50);
            nuevaImagen.setLayoutParams(nuevaImagenParams);
            //Añadimos dinámicamente un nuevo imageView de tamaño preestablecido a 500x500.
            ImageView imagen = new ImageView(this);
            LinearLayout.LayoutParams imagenParams = new LinearLayout.LayoutParams(
                    500,
                    500
            );
            //Establecemos la forma y el espaciado de la imagen.
            imagenParams.setMargins(0, 0, 50, 0);
            imagen.setLayoutParams(imagenParams);
            //Con esto escalamos la imagen para que se recorte hacia el centro en caso de ser más grande hasta 500x500.
            imagen.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imagen.setImageBitmap(bitmap);
            //Añadimos la imagen a el "contenedor" de tipo LinearLayout que hemos creado antes, es decir a la vista.
            nuevaImagen.addView(imagen);
            //Añadimos la vista a el LinearLayout ya existente lo que representa las imágenes en la activity.
            ll.addView(nuevaImagen);
        }
    }
    //Método para resetar las imágenes.
    public static void resetearImagenes(){
        //Simplemente limpia el array de imágenes.
        imagenesBytes.clear();
    }
    //Método para validar el tamaño de las imágenes.
    private boolean validarImagenes(int ancho, int alto){
        int minAncho=200;
        int minAlto=200;
        int maxAncho=1920;
        int maxAlto=1080;

        if(ancho < minAncho || alto < minAlto || ancho > maxAncho || alto > maxAlto){
            return false;
        }
        return true;
    }

}
