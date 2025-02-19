package com.example.explicacionvideojuego;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import java.util.ArrayList;

public class Control {
    public boolean pulsado = false;
    public float coordenada_x, coordenada_y;
    private Bitmap imagen;
    private Context mContexto;
    public String nombre;

    public Control(Context c, float x, float y){
        coordenada_x = x;
        coordenada_y = y;
        mContexto = c;
    }

    public void Cargar(int recurso){
        imagen = BitmapFactory.decodeResource(mContexto.getResources(), recurso);
    }

    public void Dibujar(Canvas c, Paint p){
        c.drawBitmap(imagen, coordenada_x, coordenada_y, p);
    }

    public int Ancho(){
        return imagen.getWidth();
    }

    public int Alto(){
        return imagen.getHeight();
    }

    public void comprueba_pulsado(int x, int y){
        boolean aux = false;
        if(x>coordenada_x && x<coordenada_x+Ancho() && y>coordenada_y && y<coordenada_y+Alto()){
            aux = true;
            Log.d("CONTROL","Botón "+nombre + "ha sido pulsado");
        }
    }

    public void comprueba_soltado(ArrayList<Toque> lista){
        boolean aux = false;
        for(Toque t: lista){
            if(t.x>coordenada_x && t.x<coordenada_x+Ancho() && t.y>coordenada_y && t.y<coordenada_y+Alto()){
                aux = true;
            }
        }
        if(!aux){
            pulsado = false;
        }
    }

}
