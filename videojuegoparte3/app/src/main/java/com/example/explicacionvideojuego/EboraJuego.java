package com.example.explicacionvideojuego;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.graphics.Canvas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MotionEventCompat;

import java.util.ArrayList;

public class EboraJuego extends SurfaceView implements SurfaceHolder.Callback, View.OnTouchListener {
    private SurfaceHolder holder; //controla surfaceview para manejar el dibujo en pantalla
    AppCompatActivity miContexto; // usamos appcopactactivity para crear el contexto ya que mantiene fucionalidades especificas de una actividad, ciclo de vida compatibilidades..
    //Coordenadas máximmas de la pantalla
    public int maxX=0;
    public int maxY=0;
    //creamos objetos tipo bitmap
    Bitmap mapa,mario;

    //variables para dimensiones mapa
    private int mapa_h,mapa_w,dest_mapa_Y;
    // variable posicion inicial del mapa
    private float pos_inicial_mapa=0;
    //coordenadas iniciales del texto en pantalla en pixeles:
    private static final int textoInicialx=50; //comenzara a 50 pixeles desde el borde izquierdo de la pantalla
    private static final int textoInicialy=20;
    // contador de frames que nos permitira controlar el juego
    private int contadorFrames=0;
    private int mario_h,mario_w; //dimensiones de mario

    //Posicion y movimiento de mario
    private int estado_mario=1;
    public float [] posicionMario=new float[2];
    private float [] velocidadMario=new float[2];
    private float [] mInicialMario=new float[2];
    private float [] gravedad=new float [2];

    // constantes para acceder a valores de coordenadas dentro del array
    public final int X=0;
    public final int Y=1;
    private float deltaT;//para medir el tiempo transcurrido entre
    // cada iteración del bucle del juego, lo que permite actualizar los elementos del juego de manera proporcional al tiempo real
    // y mantener la velocidad de actualización constante independientemente de la tasa de fotogramas (FPS).
    private float tiempoCrucePantalla=10;
    private BucleJuego bucle;
    /*Array de Touch para toques en la pantalla */
    private ArrayList<Toque> toques = new ArrayList<Toque>();
    boolean hayToque=false;

    Control controles[]=new Control[4];
    /* Definición de controles cada boton esta en una posicion especifica del array controles */
    private final int IZQUIERDA=0;
    private final int DERECHA=1;
    private final int DISPARO=2;
    private final int SALTO=3;
    private boolean salto_iniciado=false;
    public  EboraJuego(AppCompatActivity context){
        super(context);
        holder=getHolder();
        holder.addCallback(this);
        miContexto=context;

    }
    public void CargaControles(){ //cargamos lo botones tactiles de movimiento, disparo, salto..
        float aux;

        //flecha_izda
        controles[IZQUIERDA]=new Control(getContext(),0,maxY/5*4); //posicion en pantalla
        controles[IZQUIERDA].Cargar( R.drawable.flecha_izda); //imagen
        controles[IZQUIERDA].nombre="IZQUIERDA"; //nombre control
        //flecha_derecha
        controles[DERECHA]=new Control(getContext(),
                controles[0].Ancho()+controles[0].coordenada_x+5,controles[0].coordenada_y);
        controles[DERECHA].Cargar(R.drawable.flecha_dcha);
        controles[DERECHA].nombre="DERECHA";

        //disparo
        aux=5.0f/7.0f*maxX; //en los 5/7 del ancho
        controles[DISPARO]=new Control(getContext(),aux,controles[0].coordenada_y);
        controles[DISPARO].Cargar(R.drawable.disparo);
        controles[DISPARO].nombre="DISPARO";

        aux=4.0f/7.0f*maxX; //en los 4/7 del ancho
        controles[SALTO]=new Control(getContext(),aux,controles[0].coordenada_y);
        controles[SALTO].Cargar(R.drawable.flecha_up);
        controles[SALTO].nombre="SALTO";

        if(controles[SALTO].pulsado){
            velocidadMario[Y]=-(maxX/tiempoCrucePantalla)*2; //velocidad inicial Y, y disminuye por lo que mario sube
            gravedad[X]=0;
            gravedad[Y]=-velocidadMario[Y]*2;
            salto_iniciado=true;
        }
    }
    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) { //creamos la supervicie
        getHolder().addCallback(this);
        Canvas c=holder.lockCanvas();
        maxX = c.getWidth();
        maxY = c.getHeight();
        holder.unlockCanvasAndPost(c);

        mapa = BitmapFactory.decodeResource(getResources(),R.drawable.mapamario);//bitmap del mapa
        // dimensiones del mapa
        mapa_h=mapa.getHeight();
        mapa_w=mapa.getWidth();
        dest_mapa_Y=(maxY-mapa_h)/2; // centramos el mapa

        //cargamos la imagen de mario
        mario = BitmapFactory.decodeResource(getResources(), R.drawable.mario);
        mario_h = mario.getHeight();
        mario_w = mario.getWidth();

        //posicionamos a mario
        mInicialMario[Y]=dest_mapa_Y+mapa_h*0.9f;
        mInicialMario[X]=maxY*0.1f;

        posicionMario[X]=mInicialMario[X];
        posicionMario[Y]=mInicialMario[Y];
        CargaControles();
        deltaT=1f/BucleJuego.MAX_FPS; //tiempo de cada frame

        velocidadMario[X]=maxX/tiempoCrucePantalla;
        // creamoe sl game loop
        bucle = new BucleJuego(getHolder(),this);
        setOnTouchListener(this);
        setFocusable(true);
        bucle.start();


    }
    public void renderizar(Canvas canvas){
        if (canvas!=null){ // por si surfaceview no se ha inicializado
            Paint mypaint = new Paint(); // objeto paint para dibujar los objetos dentro
            mypaint.setStyle(Paint.Style.STROKE); // de esta manera solo fija el estilo a solo contornos.
            // pintamos todo canvas de rojo para evitar imagenes residuales de frames anteriores
            canvas.drawColor(Color.RED);
            canvas.drawBitmap(mapa, new Rect((int) pos_inicial_mapa, 0, (int) (maxX + pos_inicial_mapa), mapa_h),
                    new Rect(0, dest_mapa_Y, maxX, dest_mapa_Y + mapa_h), null);
            // Dibujar una segunda copia del mapa justo después de la primera para el efecto de scroll infinito
            canvas.drawBitmap(mapa, new Rect((int) (pos_inicial_mapa - mapa_w), 0, (int) (maxX + pos_inicial_mapa - mapa_w), mapa_h),
                    new Rect(0, dest_mapa_Y, maxX, dest_mapa_Y + mapa_h), null);

            //dibujamos a mario con su animacion
            int puntero_mario_sprite=mario_w/21*estado_mario;  //mario tiene 21 frames en su prite sheet
            canvas.drawBitmap(mario,
                    new Rect(puntero_mario_sprite,0,puntero_mario_sprite+mario_w/21,mario_h*2/3), //recortamos el frame de mario
                    new Rect((int)posicionMario[X],(int) posicionMario[Y]-mario_h*2/3,(int)posicionMario[X]+mario_w/21,(int)posicionMario[Y]),
                    null); //posicionamos a mario en la pantalla
            //pintamos un texto
            mypaint.setStyle(Paint.Style.FILL);
            mypaint.setTextSize(40);
            Rect textBounds = new Rect();
            mypaint.getTextBounds("Frames ejecutados",0,1,textBounds);
            canvas.drawText("Frames Ejecutados: "+contadorFrames,textoInicialx,textoInicialy+textBounds.height(),mypaint);

            //dibuja los controles
            mypaint.setAlpha(200);
            for(int i=0;i<4;i++){
                controles[i].Dibujar(canvas,mypaint);
            }
        }


    }
    public void actualizar(){
        contadorFrames++;
        float desplazamientomapa =5f;
        pos_inicial_mapa+= desplazamientomapa;
        //mario salta
        if(controles[SALTO].pulsado){
            velocidadMario[Y]=-(maxX/tiempoCrucePantalla)*2; //velocidad inicial Y, y disminuye por lo que mario sube
            gravedad[X]=0;
            gravedad[Y]=-velocidadMario[Y]*2;
            salto_iniciado=true;
        }
        if (pos_inicial_mapa >= mapa_w){
            pos_inicial_mapa -=mapa_w;
        }
        //mario corre derecha
        if(controles[DERECHA].pulsado) {
            // cada 3 frames cambia la animacion de mario
            if(contadorFrames%3==0){
                estado_mario++;
                if(estado_mario==4) estado_mario=1; //vuelva a 1 cuando llega a 4
            }

            if(posicionMario[X]<0.7f*maxX) { //si no ha llegado al 70% de ancho de la pantalla se mueve
                posicionMario[X] = posicionMario[X] + deltaT * velocidadMario[X];
                velocidadMario[X] = velocidadMario[X] + deltaT * gravedad[X];
            }else{
                pos_inicial_mapa=pos_inicial_mapa + deltaT * velocidadMario[X]; //si ha llegado no mueve a mario si no que desplaza el fondo
                velocidadMario[X] = velocidadMario[X] + deltaT * gravedad[X];
            }
        }

        //mario corre izquierda
        if(controles[IZQUIERDA].pulsado) {
            if(contadorFrames%3==0){
                estado_mario++;
                if(estado_mario==4) estado_mario=1;
            }

            if(posicionMario[X]>0) { //si mario no esta en el borde izquierdo se mueve hacia atras
                posicionMario[X] = posicionMario[X] - deltaT * velocidadMario[X];
                velocidadMario[X] = velocidadMario[X] - deltaT * gravedad[X];
            }
        }

        if(salto_iniciado) { //actualiza la posicion y de mario segun la velocidad y actualiza la gravedad para ajustar el arco
            posicionMario[Y] = posicionMario[Y] + deltaT * velocidadMario[Y];
            velocidadMario[Y] = velocidadMario[Y] + deltaT * gravedad[Y];
        }

        //rebote vertical
        if(posicionMario[Y]>=mInicialMario[Y]){
            salto_iniciado=false;
            velocidadMario[Y]=0;
            posicionMario[Y]=mInicialMario[Y];
        }
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        int index;
        int x,y;

        // Obtener el pointer asociado con la acción
        index = MotionEventCompat.getActionIndex(event);
        // alamcenamos las corredenadas donde ocurrio el toque para que el usurario pueda tocar multiples botones al mismo tiempo
        x = (int) MotionEventCompat.getX(event, index);
        y = (int) MotionEventCompat.getY(event, index);
        // identificamos el evento
        switch(event.getActionMasked()){
            case MotionEvent.ACTION_DOWN: // primer dedo toca la pantalla
            case MotionEvent.ACTION_POINTER_DOWN: // otro dedo toca la pantalla
                hayToque=true;

                synchronized(this) {
                    toques.add(index, new Toque(index, x, y));
                }

                //se comprueba si se ha pulsado
                for(int i=0;i<4;i++)
                    controles[i].comprueba_pulsado(x,y);
                break;

            case MotionEvent.ACTION_POINTER_UP: // un dedo levanta el toque pero hay otros tocando
                synchronized(this) {
                    toques.remove(index);
                }

                //se comprueba si se ha soltado el botón
                for(int i=0;i<4;i++)
                    controles[i].comprueba_soltado(toques);
                break;

            case MotionEvent.ACTION_UP: // ultimo dedo levanta el toque
                synchronized(this) {
                    toques.clear();
                }
                hayToque=false;
                //se comprueba si se ha soltado el botón
                for(int i=0;i<4;i++)
                    controles[i].comprueba_soltado(toques);
                break;
        }

        return true;
    }
}
