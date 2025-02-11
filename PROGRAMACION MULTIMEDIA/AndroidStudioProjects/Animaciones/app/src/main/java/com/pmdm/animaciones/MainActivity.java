package com.pmdm.animaciones;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
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
        animarBoton();
        animarImageView();
        animarMan();
        animarRobot();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


    }

    public void animarBoton() {
        AnimatorSet animadorBoton = new AnimatorSet();
        Button b = findViewById(R.id.button);

        ObjectAnimator trasladar = ObjectAnimator.ofFloat(b, "translationX", -800, 0);
        trasladar.setDuration(10000);

        ObjectAnimator fade = ObjectAnimator.ofFloat(b, "alpha", 0f, 1f);
        fade.setDuration(2000);

        ObjectAnimator rotar = ObjectAnimator.ofFloat(b, "rotationY", 0, 360);
        rotar.setDuration(10000);

        ObjectAnimator color = ObjectAnimator.ofArgb(b, "backgroundColor",
                Color.argb(128, 255, 0, 0), Color.argb(128, 0, 0, 255));
        color.setDuration(10000);

        ObjectAnimator trasladarY =  ObjectAnimator.ofFloat(b, "translationY", 1000,0);
        trasladarY.setDuration(8000);

        ObjectAnimator vibrar = ObjectAnimator.ofFloat(b ,"rotation", 0f, 20f, 0f, -20f, 0f);
        vibrar.setRepeatCount(100);
        vibrar.setDuration(100);

        animadorBoton.play(trasladar).with(fade).with(rotar).with(color).with(trasladarY).with(vibrar);
        animadorBoton.start();

    }

    public void animarImageView(){
        ImageView miImagen = findViewById(R.id.imageView);
        Animation miAnimacion = AnimationUtils.loadAnimation(this, R.anim.animacion);
        miImagen.startAnimation(miAnimacion);
    }

    public void animarMan(){
        AnimationDrawable animacionMan;
        ImageView imgMan = (ImageView) findViewById(R.id.man);
        imgMan.setBackgroundResource(R.drawable.man);
        animacionMan = (AnimationDrawable) imgMan.getBackground();
        animacionMan.start();

        AnimatorSet animadorMan = new AnimatorSet();
        ObjectAnimator trasladar = ObjectAnimator.ofFloat(imgMan, "translationX", 0,800);
        trasladar.setDuration(1000);
        trasladar.setRepeatMode(ObjectAnimator.RESTART);
        trasladar.setRepeatCount(ObjectAnimator.INFINITE);

        ObjectAnimator trasladarY = ObjectAnimator.ofFloat(imgMan, "translationY", 0,800);
        trasladarY.setDuration(10000);
        trasladarY.setRepeatMode(ObjectAnimator.RESTART);
        trasladarY.setRepeatCount(ObjectAnimator.INFINITE);
        animadorMan.play(trasladar).with(trasladarY);
        animadorMan.start();
    }

    public void animarRobot(){
        AnimationDrawable animacionRobot;
        ImageView imgRobot = (ImageView) findViewById(R.id.robot);
        imgRobot.setBackgroundResource(R.drawable.robot);
        animacionRobot = (AnimationDrawable) imgRobot.getBackground();
        animacionRobot.start();

        AnimatorSet animatorRobot = new AnimatorSet();
        ObjectAnimator trasladar = ObjectAnimator.ofFloat(imgRobot, "translationX", 0,-800);
        trasladar.setDuration(10000);
        trasladar.setRepeatMode(ObjectAnimator.RESTART);
        trasladar.setRepeatCount(ObjectAnimator.INFINITE);
        animatorRobot.play(trasladar);
        animatorRobot.start();
    }

}