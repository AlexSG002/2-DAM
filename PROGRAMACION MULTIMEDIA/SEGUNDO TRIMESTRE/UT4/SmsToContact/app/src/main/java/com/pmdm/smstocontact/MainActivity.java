package com.pmdm.smstocontact;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import android.Manifest;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private LinearLayout ll;
    private final int CODIGO_PERMISO_LEER_CONTACTOS = 1;
    private ListView listViewContactos; // Variable para el ListView
    private ContactoAdapter contactoAdapter; // Adaptador personalizado para el ListView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button bSeleccionar = findViewById(R.id.buttonSeleccionar);
        ll = findViewById(R.id.linearLayout);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            // Si no se tiene el permiso, se solicita
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, CODIGO_PERMISO_LEER_CONTACTOS);
        }

        // Establecer el evento para el botón
        bSeleccionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ll.setVisibility(View.VISIBLE);

                LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
                View listViewLayout = inflater.inflate(R.layout.listview, ll, false);

                listViewContactos = listViewLayout.findViewById(R.id.listViewContactos);

                ArrayList<Contacto> contactos = buscar();
                if (contactos != null) {
                    contactoAdapter = new ContactoAdapter(MainActivity.this, contactos);
                    listViewContactos.setAdapter(contactoAdapter);
                }

                ll.addView(listViewLayout);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CODIGO_PERMISO_LEER_CONTACTOS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            }
        }
    }

    @SuppressLint("Range")
    public ArrayList<Contacto> buscar() {
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
        ArrayList<Contacto> listaContactos = new ArrayList<>();

        if (cur != null && cur.getCount() > 0) {
            while (cur.moveToNext()) {
                String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                String numTelefono = "";

                if (Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                    Cursor cursorTelefono = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER},
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id}, null);
                    if (cursorTelefono != null && cursorTelefono.moveToNext()) {
                        numTelefono = cursorTelefono.getString(cursorTelefono.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        cursorTelefono.close();
                    }
                }

                Contacto contactoObjeto = new Contacto(id, name, numTelefono);
                listaContactos.add(contactoObjeto);
            }
            cur.close();
        }

        return listaContactos;
    }

    public void enviarSMS(Contacto contacto, String mensaje) {
        String numeroTelefono = contacto.getNumero();

        if (numeroTelefono != null && !numeroTelefono.isEmpty()) {
            try {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(numeroTelefono, null, mensaje, null, null);

                Toast.makeText(MainActivity.this, "SMS enviado a " + contacto.getNombre(), Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(MainActivity.this, "No se pudo enviar el SMS", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(MainActivity.this, "El contacto no tiene un número de teléfono válido.", Toast.LENGTH_SHORT).show();
        }
    }
}
