package com.example.explicacionvideojuego;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Enemigo {
    private EboraJuego juego;
    public int nivel;
    public float VELOCIDAD_ENEMIGO;

    public float coordenada_x;
    public float coordenada_y;

    public Enemigo(EboraJuego j, int n) {
        this.juego = j;
        this.nivel = n;

        // 5 segundos en cruzar
        this.VELOCIDAD_ENEMIGO = j.maxX / 5f / BucleJuego.MAX_FPS;
        this.coordenada_y = juego.posicionMario[juego.Y] - alto();
        this.coordenada_x = juego.maxX;
    }

    // Actualiza la coordenada del enemigo con respecto a la coordenada de la nave
    public void actualizaCoordenadas() {
        coordenada_x -= VELOCIDAD_ENEMIGO;
    }

    public void dibujar(Canvas c, Paint p) {
        c.drawBitmap(juego.enemigo_tonto, coordenada_x, coordenada_y, p);
    }

    public int ancho() {
        return juego.enemigo_tonto.getWidth();
    }

    public int alto() {
        return juego.enemigo_tonto.getHeight();
    }
}





