package com.example.mimediacontroller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
//Clase MusicReciver que recibe el intent y con BroadCastReceiver inicia el servicio de musica a través de un intent donde le pasamos la acción para manejar la música desde la notificación.
public class MusicReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String accion = intent.getAction();
        if(accion != null){
            Intent i = new Intent(context, MusicService.class);
            i.setAction(accion);
            context.startService(i);
        }
    }
}
