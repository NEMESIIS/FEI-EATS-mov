package com.example.fei_eats_mov;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.fei_eats_mov.productos.adapters.MensajeAdapterV;
import com.example.fei_eats_mov.usuarios.adapter.AdapterMascota;
import com.example.fei_eats_mov.usuarios.pojo.Mascota;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ActivityUsuario2 extends AppCompatActivity {
    private DatabaseReference ref;
    private ListView viewlista;
    private ArrayList<Mascota> list;
    private AdapterMascota adapter;
    private RecyclerView rv;
    private SearchView searchView;
    private LinearLayoutManager lm;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios2);

        ref = FirebaseDatabase.getInstance().getReference().child("Usuarios");
        rv = findViewById(R.id.rv);
        searchView = findViewById(R.id.search);
        lm = new LinearLayoutManager(this);
        rv.setLayoutManager(lm);
        list = new ArrayList<>();
        adapter = new AdapterMascota(list);
        rv.setAdapter(adapter);


        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        Mascota ms = snapshot.getValue(Mascota.class);

                        list.add(ms);
                    }
                    adapter.notifyDataSetChanged();


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
/*
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                buscar(s);
                return true;
            }
        });
*/











        //--------Botón Regresar a Actividad Pantalla Default
        final Button btnRegresar = findViewById(R.id.btnRegresaru2);

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registrar = new Intent(ActivityUsuario2.this, ActivityDefault.class);
                startActivity(registrar);
                finish();
            }
        });
        //------------Termina botón de regresar
    }

    private void buscar(String s) {
        ArrayList<Mascota>milista = new ArrayList<>();
        for (Mascota obj: list){
            if (obj.getNombre().toLowerCase().contains(s.toLowerCase())){
                milista.add(obj);
            }

        }
        AdapterMascota adapter = new AdapterMascota(milista);
        rv.setAdapter(adapter);
    }
}
