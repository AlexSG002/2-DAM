package com.example.explicacionvideojuego;

import android.content.Context;
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

    public int maxX = 0;

    public int maxY = 0;

    Bitmap mapa;

    private int mapa_h, mapa_w, dest_mapaY;

    private float pos_inicial_mapa = 0;

    private int contadorFrames = 0;

    private static final int textoInicialx = 50;

    private static final int textoInicialy = 20;

    private BucleJuego bucle;

    private float desplazamientoX = 0;
    private float velocidadX = 5;

    private float deltaT;

    private SurfaceHolder holder;
    AppCompatActivity miContexto;

    public EboraJuego(AppCompatActivity context) {
        super(context);
        holder = getHolder();
        holder.addCallback(this);
        miContexto = context;

    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        getHolder().addCallback(this);
        Canvas c = holder.lockCanvas();
        maxX = c.getWidth();
        maxY = c.getHeight();
        holder.unlockCanvasAndPost(c);
        mapa = BitmapFactory.decodeResource(getResources(), R.drawable.mapamario);
        mapa_h = mapa.getHeight();
        mapa_w = mapa.getWidth();
        dest_mapaY = (maxY - mapa_h) / 2;
        deltaT = 1f / BucleJuego.MAX_FPS;

        bucle = new BucleJuego(getHolder(), this);
        setFocusable(true);
        bucle.start();

    }

    public void renderizar(Canvas canvas) {
        if (canvas != null) {
            Paint myPaint = new Paint();
            myPaint.setStyle(Paint.Style.STROKE);
            canvas.drawColor(Color.RED);

            canvas.drawBitmap(mapa, new Rect((int) (pos_inicial_mapa), 0, (int) (maxX + pos_inicial_mapa), mapa_h),
                    new Rect(0, dest_mapaY, maxX, dest_mapaY + mapa_h), null);

            myPaint.setStyle(Paint.Style.FILL);
            myPaint.setTextSize(40);


            Rect textBounds = new Rect();
            myPaint.getTextBounds("Frames ejecutados", 0, 1, textBounds);
            canvas.drawText("Frames ejecutados: " + contadorFrames, textoInicialx,
                    textoInicialy + textBounds.height(), myPaint);
        }
    }

    public void actualizar() {
        contadorFrames++;
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}
