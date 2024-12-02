package com.example.sharemybike2;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private final List<BikesContent.Bike> mValues;

    public MyItemRecyclerViewAdapter(List<BikesContent.Bike> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        BikesContent.Bike bike = mValues.get(position);

        holder.ownerTextView.setText(bike.getOwner());
        holder.descriptionTextView.setText(bike.getDescription());
        holder.locationTextView.setText(bike.getCity() + ", " + bike.getLocation());
        holder.bikeImageView.setImageBitmap(bike.getPhoto());

        holder.emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = bike.getEmail();
                String subject = "Solicitud de bicicleta";
                String message = "Dear Mr/Mrs " + bike.getOwner() + ":\n" +
                        "I'd like to use your bike at " + bike.getLocation() + " (" + bike.getCity() + ")\n" +
                        "for the following date: " + BikesContent.selectedDate + "\n" +
                        "Can you confirm its availability?\n" +
                        "Kindest regards";

                // Depuración de valores
                Log.d("Email Intent", "Email: " + email);
                Log.d("Email Intent", "Subject: " + subject);
                Log.d("Email Intent", "Message: " + message);

                // Crea el Intent
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", email, null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
                emailIntent.putExtra(Intent.EXTRA_TEXT, message);

                // Verifica si hay una app de correo instalada que pueda manejar este Intent
                if (emailIntent.resolveActivity(view.getContext().getPackageManager()) != null) {
                    // Si existe, abre la aplicación para enviar el email
                    view.getContext().startActivity(Intent.createChooser(emailIntent, "Enviar email..."));
                } else {
                    // Si no hay una app de correo disponible, muestra un mensaje de error
                    Toast.makeText(view.getContext(), "No hay aplicaciones de correo disponibles", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final ImageView bikeImageView;
        public final TextView ownerTextView;
        public final TextView descriptionTextView;
        public final TextView locationTextView;
        public final Button emailButton;

        public ViewHolder(View view) {
            super(view);
            bikeImageView = view.findViewById(R.id.bikeImageView);
            ownerTextView = view.findViewById(R.id.ownerTextView);
            descriptionTextView = view.findViewById(R.id.descriptionTextView);
            locationTextView = view.findViewById(R.id.locationTextView);
            emailButton = view.findViewById(R.id.emailButton);
        }
    }
}
