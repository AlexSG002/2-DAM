package es.riberadeltajo.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SecondaryActivity extends AppCompatActivity {
    private LinearLayout ll;
    private static final int MAX_IMAGENES = 3;
    private static ArrayList<byte[]> imagenesBytes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);
        ll = findViewById(R.id.LinearLayout);
        Button bVolver = findViewById(R.id.buttonVolver);

        byte[] nuevaImagenBytes = getIntent().getByteArrayExtra("IMAGEN");
        if (nuevaImagenBytes != null) {
            if (imagenesBytes.size() < MAX_IMAGENES) {
                imagenesBytes.add(nuevaImagenBytes);
            } else {
                Toast.makeText(this, "El número máximo de imágenes es 3", Toast.LENGTH_SHORT).show();
            }
        }

        mostrarImagenes();

        bVolver.setOnClickListener(view -> {
            Intent intent = new Intent(SecondaryActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
        });
    }

    private void mostrarImagenes() {
        ll.removeAllViews();
        for (int i = 0; i < imagenesBytes.size(); i++) {
            byte[] imagenBytes = imagenesBytes.get(i);
            Bitmap bitmap = BitmapFactory.decodeByteArray(imagenBytes, 0, imagenBytes.length);

            LinearLayout nuevaImagen = new LinearLayout(this);
            nuevaImagen.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams nuevaImagenParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            nuevaImagenParams.setMargins(250, 50,50, 50);
            nuevaImagen.setLayoutParams(nuevaImagenParams);

            ImageView imagen = new ImageView(this);
            LinearLayout.LayoutParams imagenParams = new LinearLayout.LayoutParams(
                    500,
                    500
            );
            imagenParams.setMargins(0, 0, 50, 0);
            imagen.setLayoutParams(imagenParams);
            imagen.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imagen.setImageBitmap(bitmap);

            nuevaImagen.addView(imagen);
            ll.addView(nuevaImagen);
        }
    }

}
