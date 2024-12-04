package com.pmdm.smstocontact;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactoAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Contacto> listaContactos;

    // Constructor
    public ContactoAdapter(Context context, ArrayList<Contacto> listaContactos) {
        this.context = context;
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
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_item, parent, false);
        }

        Contacto contacto = listaContactos.get(position);

        TextView nameTextView = convertView.findViewById(R.id.textViewContactName);
        TextView numberTextView = convertView.findViewById(R.id.textViewContactNumber);
        ImageView photoImageView = convertView.findViewById(R.id.imageViewContactPhoto);

        nameTextView.setText(contacto.getNombre());
        numberTextView.setText(contacto.getNumero());



        return convertView;
    }
}
