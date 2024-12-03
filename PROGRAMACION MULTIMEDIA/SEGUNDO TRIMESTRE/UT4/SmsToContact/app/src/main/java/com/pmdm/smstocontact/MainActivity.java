package com.pmdm.smstocontact;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private LinearLayout ll;
    private final int CODIGO_PERMISO_LEER_CONTACTOS = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button bSeleccionar = findViewById(R.id.buttonSeleccionar);
        ll = findViewById(R.id.linearLayout);
        bSeleccionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = LayoutInflater.from(MainActivity.this);

                View listaView = inflater.inflate(R.layout.listview, ll);
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == CODIGO_PERMISO_LEER_CONTACTOS){
            if(grantResults.length > 0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){

            }
        }
    }

    @SuppressLint("Range")
    public ArrayList<String> buscar(){

        ContentResolver cr = getContentResolver();
        String contacto = "";

        String filtro = ContactsContract.Contacts.DISPLAY_NAME + " LIKE ?";
        String[] args_filtro = {"%" + contacto + "%"};

        String[] proyeccion = {
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.Contacts.HAS_PHONE_NUMBER,
                ContactsContract.Contacts.PHOTO_ID
        };

        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI, proyeccion, filtro, args_filtro, null);
        ArrayList<String> listaContactos = new ArrayList<>();
        if (cur != null && cur.getCount() > 0) {
            while (cur.moveToNext()) {
                String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                if(Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)))>0){
                    listaContactos.add(name);
                }
            }

            cur.close();
            return listaContactos;
        } else {
            Toast.makeText(MainActivity.this, "No se encontraron contactos", Toast.LENGTH_SHORT).show();
            return null;
        }
    }

}
