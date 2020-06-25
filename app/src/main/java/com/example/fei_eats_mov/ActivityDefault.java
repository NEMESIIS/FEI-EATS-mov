package com.example.fei_eats_mov;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fei_eats_mov.productos.Mensaje;
import com.example.fei_eats_mov.productos.MensajeV;
import com.example.fei_eats_mov.productos.adapters.MensajeAdapterV;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class ActivityDefault extends AppCompatActivity {
    private StorageReference dStorage;
    private ImageView fotoP;
    private DatabaseReference dvendedores;
    private ListView viewlista;
    private MensajeAdapterV mAdapter2;
    private ArrayAdapter<String> adapter;
    private ArrayList<MensajeV> mMensajesList2 = new ArrayList<>();
    // private ProductoModelo mAdapter;
    private RecyclerView mRecyclerView2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);
        mRecyclerView2 = findViewById(R.id.recyclerViewVendedores);

        mRecyclerView2.setLayoutManager(new LinearLayoutManager(ActivityDefault.this));

        //Referencias
        dvendedores = FirebaseDatabase.getInstance().getReference();
        getMensajesFromFirebase();


        //--------Botón Regresar a Actividad Pantalla Principal
        final Button btnRegresar = findViewById(R.id.btnRegresar4567);

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registrar = new Intent(ActivityDefault.this, ActivityPrincipal.class);
                startActivity(registrar);
                finish();
            }
        });
        //-------Termina apartado de botón
    }
    //-----------Método getMensajesFromFirebase
    private void getMensajesFromFirebase(){
        Query query = dvendedores.child("Usuarios").orderByChild("rol").equalTo("Vendedor");
        query.addListenerForSingleValueEvent(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        String nombre = ds.child("nombre").getValue().toString();
                        String usuario = ds.child("usuario").getValue().toString();
                        String celular = ds.child("celular").getValue().toString();
                        //--Aquí va el apartado de la imagen


                        mMensajesList2.add(new MensajeV("Nombre:  "+nombre,"Usuario:  "+usuario,"Celular:  "+celular));

                    }

                    mAdapter2 = new MensajeAdapterV(mMensajesList2, R.layout.item_vendedor);
                    mRecyclerView2.setAdapter(mAdapter2);
                }
            }
            //---
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}

