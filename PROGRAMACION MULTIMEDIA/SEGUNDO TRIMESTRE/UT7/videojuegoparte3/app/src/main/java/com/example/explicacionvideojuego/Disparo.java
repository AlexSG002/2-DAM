package com.example.explicacionvideojuego;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.MediaPlayer;

public class Disparo {

    public float coordenada_x, coordenada_y;

    private EboraJuego juego;

    private float velocidad;

    private MediaPlayer mediaPlayer;

    private float MAX_SEGUNDOS_EN_CRUZAR_PANTALLA = 3;

    public Disparo(EboraJuego j, float x, float y){
        juego = j;
        coordenada_x = x;
        coordenada_y = y;

        velocidad = j.maxX/MAX_SEGUNDOS_EN_CRUZAR_PANTALLA/BucleJuego.MAX_FPS;

        mediaPlayer = MediaPlayer.create(j.getContext(),R.raw.disparo);
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
            }
        });
        mediaPlayer.start();
    }

    public void ActualizarCoordenadas(){
        coordenada_x += velocidad;
    }

    public void Dibujar(Canvas c, Paint p){
        c.drawBitmap(juego.disparo, coordenada_x, coordenada_y, p);
    }

    public int Ancho(){
        return juego.disparo.getWidth();
    }

    public int Alto(){
        return juego.disparo.getHeight();
    }

    public boolean FueraDePantalla(){
        return coordenada_y < 0;
    }

}
