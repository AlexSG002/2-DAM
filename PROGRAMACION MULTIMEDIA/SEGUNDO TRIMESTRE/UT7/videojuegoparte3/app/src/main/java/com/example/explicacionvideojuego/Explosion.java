package com.example.explicacionvideojuego;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.MediaPlayer;

public class Explosion {
    private static final int NUMERO_IMAGENES_EN_SECUENCIA = 17;

    private int ancho_sprite;
    private int alto_sprite;
    private int estado;

    public float coordenada_x, coordenada_y;
    private EboraJuego juego;
    private MediaPlayer mediaPlayer;

    public Explosion(EboraJuego j, float x, float y) {
        this.coordenada_x = x;
        this.coordenada_y = y;
        this.juego = j;
        this.ancho_sprite = juego.explosion.getWidth() / NUMERO_IMAGENES_EN_SECUENCIA;
        this.alto_sprite = juego.explosion.getHeight();
        this.estado = -1; // Recién creado

        iniciarReproduccionSonido();
    }

    private void iniciarReproduccionSonido() {
        mediaPlayer = MediaPlayer.create(juego.getContext(), R.raw.explosion);
        mediaPlayer.setOnCompletionListener(mp -> mp.release());
        mediaPlayer.start();
    }

    public void actualizarEstado() {
        estado++;
    }

    public void dibujar(Canvas c, Paint p) {
        if (!haTerminado()) {
            int posicionSprite = estado * ancho_sprite;

            // Calculamos el cuadrado del sprite que vamos a dibujar
            Rect origen = new Rect(posicionSprite, 0, posicionSprite + ancho_sprite, alto_sprite);

            // Calculamos donde vamos a dibujar la porción del sprite
            Rect destino = new Rect(
                    (int) coordenada_x, (int) coordenada_y,
                    (int) coordenada_x + ancho_sprite, (int) coordenada_y + juego.explosion.getHeight()
            );

            c.drawBitmap(juego.explosion, origen, destino, p);
        }
    }

    public boolean haTerminado() {
        return estado >= NUMERO_IMAGENES_EN_SECUENCIA;
    }
}

