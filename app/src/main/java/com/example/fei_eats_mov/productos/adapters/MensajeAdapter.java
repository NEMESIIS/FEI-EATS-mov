package com.example.fei_eats_mov.productos.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fei_eats_mov.R;
import com.example.fei_eats_mov.productos.Mensaje;

import java.util.ArrayList;

public class MensajeAdapter extends RecyclerView.Adapter<MensajeAdapter.ViewHolder> {
    private int resource;
    private ArrayList<Mensaje>mensajesList;

    public MensajeAdapter(ArrayList<Mensaje> mensajesList, int resource) {
        this.mensajesList = mensajesList;
        this.resource = resource;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(resource, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int index) {
        Mensaje mensaje = mensajesList.get(index);

        viewHolder.textViewMensaje.setText(mensaje.getNombre());
        viewHolder.textViewMensaje2.setText(mensaje.getPrecio());
        viewHolder.textViewMensaje3.setText(mensaje.getDescripcion());
    }
//--
    @Override
    public int getItemCount() {
        return mensajesList.size();
    }

    public class  ViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewMensaje;
        private TextView textViewMensaje2;
        private TextView textViewMensaje3;
        //private ImageView Imagen;
        public View view;

        public ViewHolder(View view){
            super(view);
            this.view = view;
            this.textViewMensaje = view.findViewById(R.id.textViewMensaje);
            this.textViewMensaje2 = view.findViewById(R.id.textViewMensaje2);
            this.textViewMensaje3 = view.findViewById(R.id.textViewMensaje3);
            // this.Imagen =view.findViewById(R.id.imgProducto);

        }

    }

}
