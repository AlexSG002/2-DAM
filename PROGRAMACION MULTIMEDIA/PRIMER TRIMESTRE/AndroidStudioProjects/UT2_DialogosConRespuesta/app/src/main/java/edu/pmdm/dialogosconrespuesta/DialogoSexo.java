package edu.pmdm.dialogosconrespuesta;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class DialogoSexo extends DialogFragment {

    OnRespuestaSexo respuesta;

    /* Este método es llamado al hacer el show() de la clase DialogFragment() */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Usamos la clase Builder para construir el diálogo
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //Escribimos el título
        builder.setTitle("Pregunta muy importante:");
        //Escribimos la pregunta
        builder.setMessage("¿Eres una chica?");
        //añadimos el botón de Si y su acción asociada
        builder.setPositiveButton("¡SI!", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id) {
                        respuesta.onRespuesta("Es una chica!");
                    }
                });
        //añadimos el botón de No y su acción asociada
        builder.setNegativeButton("¡NO!", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                respuesta.onRespuesta("Es un chico!");
            }
        });
        // Crear el AlertDialog y devolverlo
        return builder.create();
    }

    //interfaz para la comunicación entre la Actividad y el Fragmento
    public interface OnRespuestaSexo {
        public void onRespuesta(String s);
    }

    //Se invoca cuando el fragmento se añade a la actividad
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        respuesta=(OnRespuestaSexo)context;
    }

}

