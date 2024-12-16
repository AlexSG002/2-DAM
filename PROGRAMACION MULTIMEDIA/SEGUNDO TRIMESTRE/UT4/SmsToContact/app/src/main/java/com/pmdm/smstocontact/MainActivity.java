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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import android.Manifest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private LinearLayout ll;
    private final int CODIGO_PERMISO_LEER_CONTACTOS = 1;
    private final int CODIGO_PERMISO_ENVIAR_SMS = 1;
    private ListView listViewContactos;
    private ContactoAdapter contactoAdapter;
    private EditText editTextNombre;
    private EditText editTextApellido;
    private Button buttonFiltrarNombre;
    private Button buttonFiltrarApellido;
    private Button buttonSeleccionar;
    private Button buttonBorrarCampos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonSeleccionar = findViewById(R.id.buttonSeleccionar);
        ll = findViewById(R.id.linearLayout);
        editTextNombre = findViewById(R.id.editTextNombre);
        editTextApellido = findViewById(R.id.editTextApellido);
        buttonFiltrarNombre = findViewById(R.id.buttonFiltrarNombre);
        buttonFiltrarApellido = findViewById(R.id.buttonFiltrarApellido);
        buttonBorrarCampos = findViewById(R.id.buttonLimpiarCampos);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, CODIGO_PERMISO_LEER_CONTACTOS);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, CODIGO_PERMISO_ENVIAR_SMS);
        }

        buttonSeleccionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ll.setVisibility(View.VISIBLE);
                buttonFiltrarApellido.setVisibility(View.VISIBLE);
                buttonFiltrarNombre.setVisibility(View.VISIBLE);
                editTextApellido.setVisibility(View.VISIBLE);
                editTextNombre.setVisibility(View.VISIBLE);
                buttonBorrarCampos.setVisibility(View.VISIBLE);
                LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
                View listViewLayout = inflater.inflate(R.layout.listview, ll, false);

                listViewContactos = listViewLayout.findViewById(R.id.listViewContactos);

                ArrayList<Contacto> contactos = buscar("", "");
                if (contactos != null) {
                    contactoAdapter = new ContactoAdapter(MainActivity.this, contactos);
                    listViewContactos.setAdapter(contactoAdapter);
                }

                ll.addView(listViewLayout);
            }
        });

        buttonFiltrarNombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombreFiltro = editTextNombre.getText().toString().trim();
                String apellidoFiltro = "";

                ArrayList<Contacto> contactosFiltrados = buscar(nombreFiltro, apellidoFiltro);
                if (contactoAdapter != null) {
                    contactoAdapter.actualizarListaContactos(contactosFiltrados);
                }
            }
        });

        buttonFiltrarApellido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombreFiltro = editTextNombre.getText().toString().trim();
                String apellidoFiltro = editTextApellido.getText().toString().trim();

                ArrayList<Contacto> contactosFiltrados = buscar(nombreFiltro, apellidoFiltro);
                if (contactoAdapter != null) {
                    contactoAdapter.actualizarListaContactos(contactosFiltrados);
                }
            }
        });

        buttonBorrarCampos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextNombre.setText("");
                editTextApellido.setText("");
            }
        });

    }

    public void irADetallesContacto(Contacto contacto) {
        DetallesContacto fragment = DetallesContacto.newInstance(contacto);
        fragment.show(getSupportFragmentManager(), "DATOS");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CODIGO_PERMISO_LEER_CONTACTOS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permiso concedido para leer contactos", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permiso denegado para leer contactos", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == CODIGO_PERMISO_ENVIAR_SMS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permiso concedido para enviar SMS", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permiso denegado para enviar SMS", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @SuppressLint("Range")
    public ArrayList<Contacto> buscar(String nombre, String apellido) {
        ContentResolver cr = getContentResolver();

        ArrayList<Contacto> listaContactos = new ArrayList<>();

        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                new String[]{
                        ContactsContract.Contacts._ID,
                        ContactsContract.Contacts.DISPLAY_NAME,
                        ContactsContract.Contacts.HAS_PHONE_NUMBER,
                        ContactsContract.Contacts.PHOTO_URI
                },
                null,
                null,
                ContactsContract.Contacts.DISPLAY_NAME + " ASC");

        if (cur != null && cur.getCount() > 0) {
            while (cur.moveToNext()) {
                String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
                String displayName = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                String photoUri = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.PHOTO_URI));

                String[] dividirNombre = displayName.split(" ", 2);
                String nombreContacto = dividirNombre.length > 0 ? dividirNombre[0] : "";
                String apellidoContacto = dividirNombre.length > 1 ? dividirNombre[1] : "";

                String numeroTelefono = "";
                if (Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                    Cursor cursorTelefono = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER},
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id}, null);
                    if (cursorTelefono != null && cursorTelefono.moveToNext()) {
                        numeroTelefono = cursorTelefono.getString(cursorTelefono.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        cursorTelefono.close();
                    }
                }

                boolean matchesNombre = nombre.isEmpty() || nombreContacto.toLowerCase().startsWith(nombre.toLowerCase());
                boolean matchesApellido = apellido.isEmpty() || apellidoContacto.toLowerCase().startsWith(apellido.toLowerCase());

                if (matchesNombre && matchesApellido) {
                    Contacto contactoObjeto = new Contacto(id, nombreContacto, apellidoContacto, numeroTelefono, photoUri);
                    listaContactos.add(contactoObjeto);
                }
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

                Toast.makeText(this, "SMS enviado a " + contacto.getNombre() + " " + contacto.getApellido(), Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(this, "No se pudo enviar el SMS", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "El contacto no tiene un número de teléfono válido.", Toast.LENGTH_SHORT).show();
        }
    }
}