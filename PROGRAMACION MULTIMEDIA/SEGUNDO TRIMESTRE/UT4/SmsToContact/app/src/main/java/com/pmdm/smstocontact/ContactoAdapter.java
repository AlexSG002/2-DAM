package com.pmdm.smstocontact;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactoAdapter extends BaseAdapter {

    private Context contexto;
    private ArrayList<Contacto> listaContactos;

    // Constructor
    public ContactoAdapter(Context contexto, ArrayList<Contacto> listaContactos) {
        this.contexto = contexto;
        this.listaContactos = listaContactos;
    }

    @Override
    public int getCount() {
        return listaContactos.size();
    }

    @Override
    public Object getItem(int position) {
        return listaContactos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(contexto);
            convertView = inflater.inflate(R.layout.listview_item, parent, false);
        }

        Contacto contacto = listaContactos.get(position);

        TextView textViewNombre = convertView.findViewById(R.id.textViewContactName);
        TextView textViewNumero = convertView.findViewById(R.id.textViewContactNumber);
        ImageView imageViewFoto = convertView.findViewById(R.id.imageViewContactPhoto);

        String nombreCompleto = contacto.getNombre() + " " + contacto.getApellido();
        textViewNombre.setText(nombreCompleto);
        textViewNumero.setText(contacto.getNumero());

        if (contacto.getUriFoto() != null) {
            imageViewFoto.setImageURI(Uri.parse(contacto.getUriFoto()));
            if (imageViewFoto.getDrawable() == null) {
                imageViewFoto.setImageResource(R.drawable.ic_launcher_foreground);
            }
        } else {
            imageViewFoto.setImageResource(R.drawable.ic_launcher_foreground);
        }

        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (contexto instanceof MainActivity) {
                    ((MainActivity) contexto).irADetallesContacto(contacto);
                }
                return true;
            }
        });

        return convertView;
    }

    public void actualizarListaContactos(ArrayList<Contacto> nuevosContactos) {
        this.listaContactos = nuevosContactos;
        notifyDataSetChanged();
    }
}
