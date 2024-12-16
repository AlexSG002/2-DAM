package com.pmdm.smstocontact;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DetallesContacto extends DialogFragment {

    private static final String DATOS_CONTACTO = "DATOS";
    private Contacto contacto;
    private Button buttonEnviarMensaje;
    private Button buttonEnviar;
    private EditText editTextMensaje;

    public static DetallesContacto newInstance(Contacto contacto) {
        DetallesContacto fragment = new DetallesContacto();
        Bundle args = new Bundle();
        args.putParcelable(DATOS_CONTACTO, contacto);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detalles_contacto, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            contacto = getArguments().getParcelable(DATOS_CONTACTO);
        }

        ImageView imageView = view.findViewById(R.id.imageViewFotoDetalles);
        TextView textViewName = view.findViewById(R.id.textViewNombreDetalles);
        TextView textViewNumber = view.findViewById(R.id.textViewNumeroDetalles);

        buttonEnviarMensaje = view.findViewById(R.id.buttonEnviarMensaje);
        buttonEnviar = view.findViewById(R.id.buttonEnviar);
        editTextMensaje = view.findViewById(R.id.editTextMensaje);

        buttonEnviarMensaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonEnviar.setVisibility(View.VISIBLE);
                editTextMensaje.setVisibility(View.VISIBLE);
                Window ventana = getDialog().getWindow();
                WindowManager.LayoutParams params = ventana.getAttributes();
                params.height = (int) (getResources().getDisplayMetrics().heightPixels * 0.8);
                ventana.setAttributes(params);
            }
        });

        buttonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mensaje = editTextMensaje.getText().toString().trim();
                if (!mensaje.isEmpty()) {
                    if (getActivity() instanceof MainActivity) {
                        ((MainActivity) getActivity()).enviarSMS(contacto, mensaje);
                        dismiss();
                    } else {
                        Toast.makeText(getContext(), "Error al enviar el mensaje", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "El mensaje está vacío", Toast.LENGTH_SHORT).show();
                }
            }
        });

        if (contacto != null) {
            String nombreCompleto = contacto.getNombre() + " " + contacto.getApellido();
            textViewName.setText(nombreCompleto);
            textViewNumber.setText(contacto.getNumero());

            if (contacto.getUriFoto() != null) {
                imageView.setImageURI(Uri.parse(contacto.getUriFoto()));
                if (imageView.getDrawable() == null) {
                    imageView.setImageResource(R.drawable.ic_launcher_foreground);
                }
            } else {
                imageView.setImageResource(R.drawable.ic_launcher_foreground);
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if(getDialog()!=null){
            Window ventana = getDialog().getWindow();
            if(ventana!=null){
                WindowManager.LayoutParams params = ventana.getAttributes();
                params.width = (int) (getResources().getDisplayMetrics().widthPixels * 0.9);
                params.height = (int) (getResources().getDisplayMetrics().heightPixels * 0.35);
                ventana.setAttributes(params);
            }
        }
    }
}
