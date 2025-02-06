package com.pmdm.archivos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
//Clase de adpatador de líneas para establecerlas en el recyclerView.
public class LineAdapter extends RecyclerView.Adapter<LineAdapter.LineViewHolder> {
    //Declaramos las variables locales que vamos a utilizar, una lista de líneas y un listener para actualizar las líneas borradas.
    private List<String> listaLineas;
    private ListenerLineaBorrada listenerLineaBorrada;
    //Constructor.
    public LineAdapter(List<String> listaLineas, ListenerLineaBorrada listenerLineaBorrada){
        this.listaLineas = listaLineas;
        this.listenerLineaBorrada = listenerLineaBorrada;
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
        //Obtenemos las líneas de la posición del adapter en la lista de líneas.
        String linea = listaLineas.get(position);
        //Establecemos el texto para cada objeto/vista línea el texto de cada línea del documento.
        holder.textView.setText(linea);

        //Le añadimos al objeto/vista línea un onLongClickListener.
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                //Borramos de la lista de líneas la línea en la que se encuentre el holder en el adaptador (que es la línea en la que mantenemos pulsado).
                listaLineas.remove(holder.getAdapterPosition());
                //Le notificamos de la eliminación al adaptador para que se actualice.
                notifyItemRemoved(holder.getAdapterPosition());
                //Comprobamos al listener que exista una línea borrada.
                if(listenerLineaBorrada != null){
                    //Si existe llamamos al listener.
                    listenerLineaBorrada.lineaBorrada();
                }
                return true;
            }
        });
    }
    //Para representar correctamente el número de líneas establecemos el ItemCount al tamaño de la lista de líneas que obtenemos de cada documento.
    @Override
    public int getItemCount() {
        return listaLineas.size();
    }
    //Dentro del holder.
    static class LineViewHolder extends RecyclerView.ViewHolder {
        //Declaramos un textView para representar cada línea.
        TextView textView;
        public LineViewHolder(@NonNull View itemView) {
            super(itemView);
            //Inicializamos la variable a un recurso de android texto paran representar las líneas.
            textView = itemView.findViewById(android.R.id.text1);
        }
    }
    //Interfaz del listener con el método para detectar las líneas borradas cuando se le llama.
    public interface ListenerLineaBorrada{
        void lineaBorrada();
    }

}
