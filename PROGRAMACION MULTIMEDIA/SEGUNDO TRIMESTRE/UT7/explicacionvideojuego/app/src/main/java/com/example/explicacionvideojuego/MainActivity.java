package com.example.explicacionvideojuego;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.animation.AnimatorSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        AnimarBoton();
        AnimarImageView();
        AnimarMan();

        Button b = findViewById(R.id.button);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ActividadJuego.class);
                startActivity(i);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void AnimarMan(){
        AnimationDrawable animacion_robot;
        ImageView imgRobot = (ImageView) findViewById(R.id.robot);
        imgRobot.setBackgroundResource(R.drawable.man);
        animacion_robot= (AnimationDrawable) imgRobot.getBackground();
        animacion_robot.start();

        AnimatorSet animadorRobot= new AnimatorSet();
        ObjectAnimator trasladar= ObjectAnimator.ofFloat(imgRobot,"translationX",0,800);
        trasladar.setDuration(10000);
        trasladar.setRepeatMode(ObjectAnimator.RESTART);
        trasladar.setRepeatCount(ObjectAnimator.INFINITE);
        animadorRobot.play(trasladar);
        animadorRobot.start();
    }
    public void AnimarImageView(){
        ImageView miImagen= findViewById(R.id.imageView);
        Animation miAnimcion= AnimationUtils.loadAnimation(this,R.anim.animacion);
        miImagen.startAnimation(miAnimcion);

    }
    public void AnimarBoton(){
        AnimatorSet animadorBton = new AnimatorSet();
        Button b=findViewById(R.id.button);

        //1ª animación, trasladar desde la izquierda (800 pixeles menos hasta la posición
        //inicial (0)
        ObjectAnimator trasladar= ObjectAnimator.ofFloat(b,"translationX",-800,0);
        trasladar.setDuration(5000);//duración 5 segundos

        //2ª Animación fade in de 8 segundos
        ObjectAnimator fade = ObjectAnimator.ofFloat(b, "alpha", 0f, 1f);
        fade.setDuration(8000);

        //3ª Animación
        ObjectAnimator rotar=ObjectAnimator.ofFloat(b,"rotationY",0,360);
        rotar.setDuration(5000);

        //4ª animación
        ObjectAnimator color=ObjectAnimator.ofArgb(b,"backgroundColor",
                Color.argb(128,255,0,0),Color.argb(128,0,0,255));
        color.setDuration(5000);

        //5ª animación
        ObjectAnimator trasladarY=ObjectAnimator.ofFloat(b,"translationY",1000,0);
        trasladarY.setDuration(5000);

        animadorBton.play(trasladar).with(fade).with(rotar).with(color).with(trasladarY);
        animadorBton.start();
    };
}