package com.example.fei_eats_mov.productos.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fei_eats_mov.R;
import com.example.fei_eats_mov.productos.MensajeV;

import java.util.ArrayList;

public class MensajeAdapterV extends RecyclerView.Adapter<MensajeAdapterV.ViewHolder>{
    private int resource2;
    private ArrayList<MensajeV>mensajesList2;

    public MensajeAdapterV(ArrayList<MensajeV> mensajesList, int resource) {
        this.mensajesList2 = mensajesList;
        this.resource2 = resource;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup2, int i) {
        View view2 = LayoutInflater.from(viewGroup2.getContext()).inflate(resource2, viewGroup2, false);
        return new ViewHolder(view2);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolderv, int index) {
        MensajeV mensajev = mensajesList2.get(index);

        viewHolderv.textViewMensajeV1.setText(mensajev.getNombre());
        viewHolderv.textViewMensajeV2.setText(mensajev.getCorreo());
        viewHolderv.textViewMensajeV3.setText(mensajev.getCelular());


    }


    @Override
    public int getItemCount() {
        return mensajesList2.size();
    }


    public class  ViewHolder extends RecyclerView.ViewHolder{
        //Contexto


        private TextView textViewMensajeV1;
        private TextView textViewMensajeV2;
        private TextView textViewMensajeV3;

        //para botón
       // Button btnLlamada;
        //private ImageView Imagen;
        public View view2;

        public ViewHolder(View view2){
            super(view2);
            this.view2 = view2;
            //Para el botón - llamada

            this.textViewMensajeV1 = view2.findViewById(R.id.textViewMensajeV1);
            this.textViewMensajeV2 = view2.findViewById(R.id.textViewMensajeV2);
            this.textViewMensajeV3 = view2.findViewById(R.id.textViewMensajeV3);

            //Para botón
            //this.btnLlamada = view2.findViewById(R.id.btnLlamada);
            // this.Imagen =view.findViewById(R.id.imgProducto);

        }

    }

}
