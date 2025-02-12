package com.pmdm.nuevasPreferencias;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;



public class Detalles extends PreferenceFragmentCompat {

    //Fragmento detalles para representar el xml con las opciones para cambiar.
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        //Establecemos las preferencias desde el archivo detalles, en la carpeta xml.
        setPreferencesFromResource(R.xml.detalles, rootKey);
    }
}