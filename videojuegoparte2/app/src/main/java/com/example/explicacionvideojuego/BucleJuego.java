package com.example.explicacionvideojuego;

import static android.content.ContentValues.TAG;

import android.graphics.Canvas;
import android.nfc.Tag;
import android.util.Log;
import android.view.SurfaceHolder;


public class BucleJuego extends Thread {
    private EboraJuego juego;
    private SurfaceHolder surfaceHolder;
    private boolean JuegoEnEjecucion=true;
    public final static int MAX_FPS=30;
    public final static int TIEMPO_FRAME = 1000/ MAX_FPS;
    private final static int   MAX_FRAMES_SALTADOS = 5;
    BucleJuego(SurfaceHolder sh, EboraJuego s){
        juego=s;
        surfaceHolder = sh;
    }

    public void run(){
        Canvas canvas;
        Log.d (TAG,"Comienza el gameloop");
        long tiempoComienzo;
        long tiempoDiferencia;
        int tiempoDormir;
        int framesASaltar;
        while(JuegoEnEjecucion){
            canvas=null;
            try{
                canvas= this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder){
                    tiempoComienzo= System.currentTimeMillis();
                    framesASaltar=0;
                    juego.actualizar();
                    juego.renderizar(canvas);
                    tiempoDiferencia= System.currentTimeMillis()-tiempoComienzo;
                    tiempoDormir =(int) (TIEMPO_FRAME-tiempoDiferencia);
                    if (tiempoDormir>0){
                        try{
                            Thread.sleep(tiempoDormir);
                        }catch (InterruptedException e){

                        }
                    }
                    while (tiempoDormir<0 && framesASaltar > MAX_FRAMES_SALTADOS){
                        juego.actualizar();
                        tiempoDormir +=TIEMPO_FRAME;
                        framesASaltar++;
                    }
                }
            } finally {
                if (canvas !=null ){
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }Log.d(TAG,"nueva iteracion");
    }
    public void fin(){
        JuegoEnEjecucion=false;
    }
}
