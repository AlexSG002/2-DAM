package com.pmdm.nuevasPreferencias;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.preference.PreferenceManager;



//Implementamos el Listener para el cambio de preferencias y que se representen en la pantalla en tiempo real.
public class DetallesActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {
    private static final int CODIGO_PERMISOS_NOTIFICACION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);
        solicitarPermisosNotificacion();
        //Declaramos las preferencias y las registramos en el listener.
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.registerOnSharedPreferenceChangeListener(this);
    }

    //Al cambiar las preferencias.
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        //Comprobamos el modo nocturno apra cambiarlo.
        if (key.equals("modoNocturno")) {
            boolean modo = sharedPreferences.getBoolean("modoNocturno", false);
            if (modo) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        }
        //Comprobamos las notificaciones, si se han habilitado enviamos una notificación
        if (key.equals("notificaciones")) {
            boolean habilitar = sharedPreferences.getBoolean("notificaciones", false);
            if (habilitar) {
                enviarNotificacion();
            } else {
            }
        }
    }

    //Método para solicitar los permisos de notificación.
    private void solicitarPermisosNotificacion() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.POST_NOTIFICATIONS},
                    CODIGO_PERMISOS_NOTIFICACION
            );
        } else {
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CODIGO_PERMISOS_NOTIFICACION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            } else {
            }
        }
    }

    //Método para enviar notificaciones.
    private void enviarNotificacion() {
        Toast.makeText(this, "Enviando notificación", Toast.LENGTH_SHORT).show();
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String channelId = "canal_notificaciones";
        String channelName = "Canal de Notificaciones";

        //Creamos el canal de notificaciones.
        NotificationChannel channel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT);
        notificationManager.createNotificationChannel(channel);

        //Creamos la notificación.
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle("Notificación activada")
                .setContentText("Has habilitado las notificaciones.")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        notificationManager.notify(1, builder.build());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.unregisterOnSharedPreferenceChangeListener(this);
    }


}
