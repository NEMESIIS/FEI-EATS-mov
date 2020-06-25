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
import com.example.fei_eats_mov.productos.adapters.MensajeAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class ActivityDulces extends AppCompatActivity {
    private StorageReference dStorage;
    private ImageView fotoP;
    private DatabaseReference ddulces;
    private ListView viewlista;
    private MensajeAdapter mAdapter;
    private ArrayAdapter<String> adapter;
    private ArrayList<Mensaje> mMensajesList = new ArrayList<>();
    // private ProductoModelo mAdapter;
    private RecyclerView mRecyclerView;

   // private SlideshowViewModel slideshowViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dulces);
        mRecyclerView = findViewById(R.id.recyclerViewMensajes321);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(ActivityDulces.this));

        //Referencias
        ddulces = FirebaseDatabase.getInstance().getReference();
        getMensajesFromFirebase();


        //--------Botón Regresar a Actividad Seleccón de Producto
        final Button btnRegresar = findViewById(R.id.btnRegresar321);

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registrar = new Intent(ActivityDulces.this, ActivityDefault2.class);
                startActivity(registrar);
                finish();
            }
        });
        //-------Termina apartado de botón
    }
//-----------Método getMensajesFromFirebase
private void getMensajesFromFirebase(){
    Query query = ddulces.child("Productos").orderByChild("Categoria").equalTo("Dulces");
    query.addListenerForSingleValueEvent(new ValueEventListener(){
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            if (dataSnapshot.exists()) {

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String nombre = ds.child("Nombre Producto").getValue().toString();
                    String precio = ds.child("Precio Producto").getValue().toString();
                    String descripcion = ds.child("descripcion").getValue().toString();
                    //--Aquí va el apartado de la imagen


                    mMensajesList.add(new Mensaje("Producto:  "+nombre, "Precio:   $ "+precio,descripcion));

                }

                mAdapter = new MensajeAdapter(mMensajesList, R.layout.item_producto);
                mRecyclerView.setAdapter(mAdapter);
            }
        }
        //---
        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });
}


}
