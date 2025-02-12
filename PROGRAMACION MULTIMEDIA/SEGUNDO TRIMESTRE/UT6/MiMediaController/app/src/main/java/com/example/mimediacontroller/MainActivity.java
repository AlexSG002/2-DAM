package com.example.mimediacontroller;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.OpenableColumns;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements MediaController.MediaPlayerControl {
    //Declaramos las variables necesarias que usaremos globalmente en la app.
    private MediaController mc;
    private MediaPlayer mp;
    private Handler h;
    private List<String> playlist = new ArrayList<>();
    private boolean reproduciendo = false;
    private SharedPreferences sharedPreferences;
    private static final String NOM_PREFERENCIAS = "PreferenciasMusica";
    private int cancionActual = 0;
    private TextView textViewTiempo;
    private ActivityResultLauncher<Intent> launcherSeleccionarCancion;
    private ImageButton buttonPlay;
    private static MainActivity instancia;
    private List<String> listaTitulos = new ArrayList<>();
    private TextView textViewNombre;
    private ListView listViewPlaylist;
    private ArrayAdapter<String> playlistAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Declaramos la instancia de MainActivity para utilizar sus métodos en las otras clases.
        instancia = this;
        setContentView(R.layout.activity_main);
        //Comprobamos y obtenemos los permisos de notificación.
        if (checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, 101);
        }


        textViewTiempo = findViewById(R.id.textViewTiempo);
        textViewNombre = findViewById(R.id.textViewNombre);
        //Inicializamos las variables para manejar el contenido multimedia.
        h = new Handler();
        mc = new MediaController(this);
        mc.setMediaPlayer(this);
        mc.setAnchorView(findViewById(R.id.main));
        //Añadimos las dos canciones por defecto a la playlist
        playlist.add("android.resource://" + getPackageName() + "/" +R.raw.musica);
        playlist.add("android.resource://" + getPackageName() + "/" +R.raw.musica2);
        //Lo mismo con los títulos.
        listaTitulos.add("Canción 1 - Música de la aplicación");
        listaTitulos.add("Canción 2 - Música de la aplicación");
        //Obtenemos las preferencias, la última canción y la posición en la que se quedó.
        sharedPreferences = getSharedPreferences(NOM_PREFERENCIAS, MODE_PRIVATE);
        cancionActual = Math.min(sharedPreferences.getInt("ultimaCancion", 0), playlist.size() - 1);
        int ultimaPosicion = sharedPreferences.getInt("ultimaPosicion", 0);
        //Creamos un nuevo MediaPlayer con la canción obtenida.
        mp = MediaPlayer.create(MainActivity.this, Uri.parse(playlist.get(cancionActual)));
        if (mp != null) {
            //Vamos a la última posición siempre que no sea nulo el mediaPlayer.
            mp.seekTo(ultimaPosicion);
        }
        //Establecemos el tiempo y la duración de la última posición.
        String tiempoActual = formatoTiempo(ultimaPosicion);
        String tiempoTotal = formatoTiempo(mp.getDuration());
        textViewTiempo.setText(tiempoActual + "/ " + tiempoTotal);
        //Actualizamos el título.
        actualizarTitulo();
        //Configuramos el launcher para ir a seleccionar canción.
        launcherSeleccionarCancion = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result ->{
                    if(result.getResultCode() == RESULT_OK && result.getData() != null){
                        //Obtenemos la uri de la canción
                        Uri uriCancionSeleccionada = result.getData().getData();
                        if(uriCancionSeleccionada != null){
                            //Y la añadimos a la playlist como string.
                            playlist.add(uriCancionSeleccionada.toString());
                            //Y el nombre a la lista de titulos.
                            String nombreCancion = obtenerNombreArchivo(uriCancionSeleccionada);
                            listaTitulos.add(nombreCancion);
                            //Establecemos la cancion actual como la última en la lista.
                            cancionActual = playlist.size() - 1;
                            //Si no es nulo soltamos el mediaPlayer para poder abrirlo de nuevo.
                            if(mp != null) {
                                mp.release();
                            }
                            //Cargamos la canción pasada por archivo.
                            mp = MediaPlayer.create(MainActivity.this, Uri.parse(playlist.get(cancionActual)));
                            mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                @Override
                                public void onPrepared(MediaPlayer mediaPlayer) {
                                    mp.start();
                                        h.post(() -> mc.show());
                                        actualizarTiempo();
                                        actualizarTitulo();
                                }
                            });
                        }
                        Toast.makeText(MainActivity.this, "Canción añadida a playlist", Toast.LENGTH_SHORT).show();
                    }
                });
        //Configuramos la vista para la playlist.
        listViewPlaylist = findViewById(R.id.listViewPlaylist);
        //Configuramos el adaptador de la playlist a un item simple de lista y le establecemos a la lista los titulos.
        playlistAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaTitulos){
            @NonNull
            @Override
            //Obtenemos la vista.
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                //Modificamos el texto de la vista.
                TextView texto = (TextView) view.findViewById(android.R.id.text1);

                texto.setTextColor(Color.BLACK);
                texto.setTypeface(null, Typeface.NORMAL);
                String itemTexto = listaTitulos.get(position);
                //Si la posición de la lista coinicide con la canción Actual establecemos el texto de la canción en rojo e indicamos que está sonando.
                if(position == cancionActual){
                    texto.setText(itemTexto + "(Sonando)");
                    texto.setTextColor(Color.RED);
                    texto.setTypeface(null, Typeface.BOLD);
                    //Y si es la siguiente lo ponemos en azul y un texto de siguiente.
                }else if(position == (cancionActual +1) % listaTitulos.size()){
                    texto.setText(itemTexto + "(Siguiente) ");
                    texto.setTextColor(Color.BLUE);
                }
            return view;
            }
        };
        //Establecemos el adapter a la ListView.
        listViewPlaylist.setAdapter(playlistAdapter);

        buttonPlay = findViewById(R.id.buttonPlay);
        //Ejecutamos el play.
        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Si no hay mediaPlayer creamos uno.
                if (mp == null) {
                    mp = MediaPlayer.create(MainActivity.this, Uri.parse(playlist.get(cancionActual)));
                    mc.setMediaPlayer(MainActivity.this);
                    mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mediaPlayer) {
                            //Lo iniciamos y mostramos el mediaController, actualizamos el tiempo, mostramos la notificación y cambiamos el icono a pausa.
                            mp.start();
                            h.post(() -> mc.show());
                            actualizarTiempo();
                            mostrarNotificacion();
                            buttonPlay.setImageResource(R.drawable.ic_pause);
                            reproduciendo = true;
                            mc.show(0);                        }
                    });
                } else {
                    //Si ya existe un mediaPlayer simplemente pausamos.
                    if (mp.isPlaying()) {
                        mp.pause();
                        buttonPlay.setImageResource(R.drawable.ic_play);
                        mostrarNotificacion();
                        reproduciendo = false;
                    } else {
                        //En caso contrario simplemente reanudamos.
                        mp.start();
                        buttonPlay.setImageResource(R.drawable.ic_pause);
                        mostrarNotificacion();
                        actualizarTiempo();
                        reproduciendo = true;
                    }
                }
                mc.show(0);
            }
        });


        ImageButton buttonStop = findViewById(R.id.buttonStop);
        //En el botón stop eliminamos los callBacks al handler para evitar punteros nulos al cerrar el mediaController y el mediaPlayer.
        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mp != null){
                    h.removeCallbacksAndMessages(null);
                    //Escondemos el mediaController
                    mc.hide();
                    //Libremos el mediaPlayer y lo establecemos a nulo para forzar una nueva creación de mediaPlayer.
                    mp.release();
                    mp = null;
                    buttonPlay.setImageResource(R.drawable.ic_play);
                    reproduciendo = false;
                    textViewTiempo.setText("00:00 / 00:00");
                    //Obtenemos el id de la notificación para pararlo y eliminar la notificación.
                    NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    if (manager != null) {
                        manager.cancel(1);
                    }
                }
            }
        });


        ImageButton buttonNext = findViewById(R.id.buttonNext);
        //Botón next con el que pasamos a la siguiente canción que se reproduce automáticamente, ya que liberamos el mediaPlayer actual e iniciamos uno nuevo con la canción siguiente.
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reproduciendo = true;
                buttonPlay.setImageResource(R.drawable.ic_pause);
                if(mp != null){
                    mp.release();
                }
                //Establecemos la canción Actual como la siguiente y la guardamos en sharedPreferences como la última canción.
                cancionActual = (cancionActual +1) % playlist.size();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("ultimaCancion", cancionActual);
                editor.apply();
                mp = MediaPlayer.create(MainActivity.this, Uri.parse(playlist.get(cancionActual)));
                mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mediaPlayer) {
                        mp.start();
                        h.post(()->mc.show());
                        actualizarTiempo();
                        mostrarNotificacion();
                        actualizarTitulo();
                    }
                });
            }
        });

        ImageButton buttonPrevious = findViewById(R.id.buttonPrevious);
        //Lo mismo para el botón de anterior pero con la canción anterior.
        buttonPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reproduciendo = true;
                buttonPlay.setImageResource(R.drawable.ic_pause);
                if(mp != null){
                    mp.release();
                }
                cancionActual = (cancionActual -1 + playlist.size()) % playlist.size();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("ultimaCancion", cancionActual);
                editor.apply();
                mp = MediaPlayer.create(MainActivity.this, Uri.parse(playlist.get(cancionActual)));
                mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mediaPlayer) {
                        mp.start();
                        h.post(()->mc.show());
                        actualizarTiempo();
                        mostrarNotificacion();
                        actualizarTitulo();
                    }
                });
            }
        });

        ImageButton buttonArchivos = findViewById(R.id.buttonArchivos);
        //Botón para seleccionar y añadir una canción desde archivos.
        buttonArchivos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seleccionarCancion();
            }
        });
    }



    //Método para actualizar el título de la canción al cambiar la canción.
    private void actualizarTitulo() {
        if(cancionActual >= 0 && cancionActual < listaTitulos.size()){
            textViewNombre.setText(listaTitulos.get(cancionActual));
        }else{
            textViewNombre.setText("Canción desconocida");
        }

        if(playlistAdapter != null) {
            playlistAdapter.notifyDataSetChanged();
        }
    }
    //Método para obtener los nombres de las canciones.
    private String obtenerNombreArchivo(Uri uri){
        String resultado = "Desconocido";
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        if(cursor!=null){
            int indice = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
            if (indice != -1 && cursor.moveToFirst()){
                resultado = cursor.getString(indice);
            }
            cursor.close();
        }
        return resultado;
    }
    //Método para actualizar el tiempo de la canción y su duración.
    private void actualizarTiempo(){
        h.postDelayed(() ->{
            if(mp != null && reproduciendo){
                int posicionActual = mp.getCurrentPosition();
                int duracion = mp.getDuration();
                String tiempoActual = formatoTiempo(posicionActual);
                String tiempoTotal = formatoTiempo(duracion);
                textViewTiempo.setText(tiempoActual + " / " +tiempoTotal );
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("ultimaPosicion", posicionActual);
                editor.apply();
                if (mp.isPlaying()) {
                    actualizarTiempo();
                }
            }
        }, 0);
    }
    //Método para seleccionarCanción.
    private void seleccionarCancion(){
        Intent i = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        i.setType("audio/*");
        i.addCategory(Intent.CATEGORY_OPENABLE);
        launcherSeleccionarCancion.launch(i);
    }
    //Método para formatear el tiempo.
    private String formatoTiempo(int milisegundos){
        return String.format("%02d:%02d", (milisegundos/60000), ((milisegundos % 60000) / 1000));
    }

    @Override
    public void start() {
        if (mp != null && !mp.isPlaying()) {
            mp.start();
            buttonPlay.setImageResource(R.drawable.ic_pause);
            mostrarNotificacion();
            actualizarTiempo();
            reproduciendo = true;
            mc.show(0);
        }
    }

    @Override
    public void pause() {
        if (mp != null && mp.isPlaying()) {
            mp.pause();
            buttonPlay.setImageResource(R.drawable.ic_play);
            mostrarNotificacion();
            reproduciendo = false;
            mc.show(0);
        }
    }

    @Override
    public int getDuration() {
        return mp.getDuration();
    }

    @Override
    public int getCurrentPosition() {
        return mp.getCurrentPosition();
    }

    @Override
    public void seekTo(int i) {
        mp.seekTo(i);
    }

    @Override
    public boolean isPlaying() {
        return reproduciendo;
    }

    @Override
    public int getBufferPercentage() {
        return 0;
    }

    @Override
    public boolean canPause() {
        return true;
    }

    @Override
    public boolean canSeekBackward() {
        return true;
    }

    @Override
    public boolean canSeekForward() {
        return true;
    }

    @Override
    public int getAudioSessionId() {
        return mp.getAudioSessionId();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN)
            if(!mc.isShowing())
                mc.show(0);
            else
                mc.hide();
        return false;
    }
    //Método para mostrar la notificación y crear la barra de notificaciones.
    private void mostrarNotificacion() {
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (manager != null) {
            manager.notify(1, crearBarraNotificaciones());
        }
    }
    //Método para pausar la música desde la barra de notificaciones que llamamos en la clase MusicService.
    public void pausarMusica(){
        if (mp != null && mp.isPlaying()) {
            mp.pause();
            buttonPlay.setImageResource(R.drawable.ic_play);
            actualizarNotificacion();
            reproduciendo = false;
            actualizarTiempo();
            mc.show(0);
        }
    }
    //Método para reanudar la música desde la barra de notificaciones que llamamos en la clase MusicService.
    public void reanudarMusica(){
        if (mp != null && !mp.isPlaying()) {
            mp.start();
            buttonPlay.setImageResource(R.drawable.ic_pause);
            mostrarNotificacion();
            reproduciendo = true;
            actualizarTiempo();
            mc.show(0);
        }
    }
    //Método para obtener la instancia de MainActivity.
    public static MainActivity getInstance() {
        return instancia;
    }

    //Método para actualizar la notificación si se pausa o reanuda.
    private static void actualizarNotificacion() {
        NotificationManager manager = (NotificationManager) instancia.getSystemService(NOTIFICATION_SERVICE);
        if (manager != null) {
            manager.notify(1, instancia.crearBarraNotificaciones());
        }
    }
    //Método para crear la barra de notificaciones.
    private Notification crearBarraNotificaciones() {

        //Creaamos un Intent y un PendingIntent que pasaremos a la clase MusicReceiver para que ejecute un método u otro según la acción recibida del intent.
        Intent pausaIntent = new Intent(this, MusicReceiver.class);
        pausaIntent.setAction("PAUSE");
        PendingIntent pendingIntentPausa = PendingIntent.getBroadcast(this, 1, pausaIntent, PendingIntent.FLAG_IMMUTABLE);

        Intent reproducirIntent = new Intent(this, MusicReceiver.class);
        reproducirIntent.setAction("PLAY");
        PendingIntent pendingIntentReproducir = PendingIntent.getBroadcast(this, 2, reproducirIntent, PendingIntent.FLAG_IMMUTABLE);
        //Creación de la notificación como tal.
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "music_channel")
                .setContentTitle("Reproduciendo música")
                .setContentText("Maneja la reproducción")
                .setSmallIcon(R.drawable.ic_music)
                .setOngoing(true)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .addAction(new NotificationCompat.Action(
                        R.drawable.ic_pause,
                        "Pausar",
                        pendingIntentPausa
                ))
                .addAction(new NotificationCompat.Action(
                        R.drawable.ic_play,
                        "Reanudar",
                        pendingIntentReproducir
                ));
        //Comprobamos la versión del sdk para crear o no el canal de notificaciones.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("music_channel", "Música", NotificationManager.IMPORTANCE_LOW);
            NotificationManager manager = getSystemService(NotificationManager.class);
            if (manager != null) {
                manager.createNotificationChannel(channel);
            }
        }
        return builder.build();
    }
    //Al maximizar de nuevo la app se actualizan los datos con la música como estaba sonando de fondo.
    @Override
    protected void onResume() {
        super.onResume();
        if (mp != null) {
            if (mp.isPlaying()) {
                buttonPlay.setImageResource(R.drawable.ic_pause);
            } else {
                buttonPlay.setImageResource(R.drawable.ic_play);
            }
            int ultimaPosicion = sharedPreferences.getInt("ultimaPosicion", 0);
            String tiempoActual = formatoTiempo(ultimaPosicion);
            String tiempoTotal = formatoTiempo(mp.getDuration());
            textViewTiempo.setText(tiempoActual + " / " + tiempoTotal);

            if(mc != null && mc.isShowing() == false){
                h.post(() ->mc.show(0));
            }
        }
    }
}