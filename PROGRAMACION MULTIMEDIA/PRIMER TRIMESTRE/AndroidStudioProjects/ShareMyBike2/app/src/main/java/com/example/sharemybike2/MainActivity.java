package com.example.sharemybike2;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1001;
    private ImageButton locationImageButton;
    private String email = "";
    private TextView direccionPostal;
    private FusedLocationProviderClient fusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        direccionPostal = findViewById(R.id.ubicacion);

        Button loginButton = findViewById(R.id.btnLogin);
        EditText editTextEmail = findViewById(R.id.direccionContacto);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = editTextEmail.getText().toString();
                if (comprobarEmail() && comprobarCodPostal()) {
                    Intent intent = new Intent(MainActivity.this, BikeActivity.class);
                    startActivity(intent);
                }
            }
        });

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        locationImageButton = findViewById(R.id.btnUbicacion);
        locationImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
                    abrirMapa();
                    obtenerUbicacionYActualizar();
                } else {
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            LOCATION_PERMISSION_REQUEST_CODE);
                }
            }
        });
    }

    private boolean comprobarCodPostal(){
        if(direccionPostal.getText().toString().equals("Dirección postal")){
            Toast.makeText(MainActivity.this, "Indica tu dirección postal haciendo clic en el botón de ubicación", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean comprobarEmail() {
        if (email.isEmpty()) {
            Toast.makeText(MainActivity.this, "El email no puede estar vacío", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                Toast.makeText(MainActivity.this, "Introduce un email correcto", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }

    private void obtenerUbicacionYActualizar() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<android.location.Location>() {
                    @Override
                    public void onSuccess(android.location.Location location) {
                        if (location != null) {
                            obtenerCodigoPostal(location.getLatitude(), location.getLongitude());
                        } else {
                            Toast.makeText(MainActivity.this, "No se pudo obtener la ubicación", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void obtenerCodigoPostal(double latitud, double longitud) {
        Geocoder geocoder = new Geocoder(MainActivity.this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(latitud, longitud, 1);
            if (addresses != null && !addresses.isEmpty()) {
                Address address = addresses.get(0);
                String codigoPostal = address.getPostalCode();
                if (codigoPostal != null) {
                    direccionPostal.setText(codigoPostal);
                } else {
                    direccionPostal.setText("Código postal no disponible");
                }
            } else {
                direccionPostal.setText("No se encontró la dirección");
            }
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(MainActivity.this, "Error al obtener el código postal", Toast.LENGTH_SHORT).show();
        }
    }


    private void abrirMapa() {
        Uri gmmIntentUri = Uri.parse("geo:40.4168,-3.7038");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");

        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        } else {
            Toast.makeText(this, "No se encontró ninguna aplicación de mapas", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                obtenerUbicacionYActualizar();
            } else {
                Toast.makeText(this, "Permiso de ubicación denegado", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
