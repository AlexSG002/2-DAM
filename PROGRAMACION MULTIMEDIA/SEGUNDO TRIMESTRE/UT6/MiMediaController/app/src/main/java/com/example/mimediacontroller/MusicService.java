package com.example.mimediacontroller;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;
//Clase MusicService que maneja la música desde la notificación con los métodos que inicializamos en MainActivity.
public class MusicService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent != null && intent.getAction() != null){
            MainActivity main = MainActivity.getInstance();
            switch (intent.getAction()){
                case "PAUSE":
                    main.pausarMusica();
                    break;

                case "PLAY":
                    main.reanudarMusica();
                    break;
            }
        }
        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
