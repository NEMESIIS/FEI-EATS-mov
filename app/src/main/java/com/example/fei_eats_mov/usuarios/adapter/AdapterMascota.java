package com.example.fei_eats_mov.usuarios.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fei_eats_mov.R;
import com.example.fei_eats_mov.usuarios.pojo.Mascota;

import java.util.List;

public class AdapterMascota extends RecyclerView.Adapter<AdapterMascota.viewholderMascotas> {
    List<Mascota>mascotaList;

    public AdapterMascota(List<Mascota> mascotaList) {
        this.mascotaList = mascotaList;
    }

    @NonNull
    @Override
    public viewholderMascotas onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_mascotas,parent,false);
       viewholderMascotas holder = new viewholderMascotas(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewholderMascotas holder, int position) {
        Mascota ms = mascotaList.get(position);

        holder.tv_nombre.setText(ms.getNombre());
        holder.tv_correo.setText(ms.getCorreo());
        holder.tv_telefono.setText(ms.getTelefono());

    }

    @Override
    public int getItemCount() {
        return mascotaList.size();
    }

    public class viewholderMascotas extends RecyclerView.ViewHolder {
        TextView tv_nombre, tv_correo,tv_telefono;

        public viewholderMascotas(@NonNull View itemView) {
            super(itemView);
            tv_nombre = itemView.findViewById(R.id.tv_nombre);
            tv_correo = itemView.findViewById(R.id.tv_correo);
            tv_telefono = itemView.findViewById(R.id.tv_telefono);

        }
    }
}
