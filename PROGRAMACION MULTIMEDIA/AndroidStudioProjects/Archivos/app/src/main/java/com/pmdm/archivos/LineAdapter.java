package com.pmdm.archivos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LineAdapter extends RecyclerView.Adapter<LineAdapter.LineViewHolder> {

    private List<String> listaLineas;

    public LineAdapter(List<String> listaLineas){
        this.listaLineas = listaLineas;
    }

    @NonNull
    @Override
    public LineAdapter.LineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_1, parent, false);
        return new LineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LineAdapter.LineViewHolder holder, int position) {
        String linea = listaLineas.get(position);
        holder.textView.setText(linea);
    }

    @Override
    public int getItemCount() {
        return listaLineas.size();
    }

    static class LineViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public LineViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(android.R.id.text1);
        }
    }
}
