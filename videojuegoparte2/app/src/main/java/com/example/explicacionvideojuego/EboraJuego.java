package com.example.explicacionvideojuego;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class EboraJuego extends SurfaceView implements SurfaceHolder.Callback, View.OnTouchListener {
    private SurfaceHolder holder; //controla surfaceview para manejar el dibujo en pantalla
    AppCompatActivity micontexto;
    //Coordenadas máximmas de la pantalla
    public int maxX=0;
    public int maxY=0;
    //creamos objetos tipo bitmap
    Bitmap mapa;
    //variables para dimensiones mapa
    private int mapa_h,mapa_w,dest_mapa_Y;
    private float pos_inicial_mapa=0;
    private int contadorFrames=0;
    private static final int textoInicialx=50; //comenzara a 50 pixeles desde el borde izquierdo de la pantalla
    private static final int textoInicialy=20;
    private BucleJuego bucle;
    private float deltaT;//para medir el tiempo transcurrido entre
    // cada iteración del bucle del juego, lo que permite actualizar los elementos del juego de manera proporcional al tiempo real
    // y mantener la velocidad de actualización constante independientemente de la tasa de fotogramas (FPS).

    public EboraJuego(AppCompatActivity context){
        super (context);
        holder=getHolder();
        holder.addCallback(this);
        micontexto=context;

    }
    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        getHolder().addCallback(this);
        Canvas c= holder.lockCanvas();
        maxX = c.getWidth();
        maxY= c.getHeight();
        holder.unlockCanvasAndPost(c);
        mapa = BitmapFactory.decodeResource(getResources(),R.drawable.mapamario);
        mapa_h=mapa.getHeight();
        mapa_w=mapa.getWidth();
        dest_mapa_Y=(maxY-mapa_h)/2;

        deltaT=1f/BucleJuego.MAX_FPS; //tiempo de cada frame

        //creamos el gameloop

        bucle = new BucleJuego(getHolder(), this);
        setFocusable(true);
        bucle.start();

    }
    public void renderizar(Canvas canvas){
        if (canvas!=null){
            Paint mypaint = new Paint();
            mypaint.setStyle(Paint.Style.STROKE);
            canvas.drawColor(Color.RED);
            canvas.drawBitmap(mapa, new Rect((int)(pos_inicial_mapa),0,
                    (int)(maxX+pos_inicial_mapa),mapa_h), new Rect(0, dest_mapa_Y,maxX,dest_mapa_Y+mapa_h),null);
            mypaint.setStyle(Paint.Style.FILL);
            mypaint.setTextSize(40);
            Rect textbounds = new Rect();
            mypaint.getTextBounds("Frames ejecutados",0,1,textbounds);
            canvas.drawText("Frames ejecutados: "+ contadorFrames,textoInicialx,textoInicialy+textbounds.height(),mypaint);

        }
    }
    public void actualizar(){
        contadorFrames++;
    }
    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }
}
