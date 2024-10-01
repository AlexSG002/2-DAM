package edu.pmdm.ut2_pruebatextview;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    //Declaro una variable de tipo TextView para recoger un TextView del layout
    TextView miTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Obtengo el textoView llamado "tvWrapContent" del layout para cambiarle el texto
        miTextView=(TextView) findViewById(R.id.tvWrapContent);

    }

    @Override
    protected void onStop() {
        super.onStop();
        //Cambio el texto en el método onResume
        miTextView.setText("He cambiado el texto");
    }


}