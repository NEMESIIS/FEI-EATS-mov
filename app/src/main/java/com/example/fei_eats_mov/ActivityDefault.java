package com.example.fei_eats_mov;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ActivityDefault extends AppCompatActivity {

    //Variables
    private DatabaseReference ddulces;
    ListView viewlista;
    ArrayList<String> listastrings;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);

        ddulces = FirebaseDatabase.getInstance().getReference();
        Query query = ddulces.child("Usuarios");
       // Query query = ddulces.child("Usuarios").equalTo("rol","Vendedor");
       // Query query = ddulces.child.equalTo("rol", "Vendedor");
       // Query query = ddulces.orderByChild("Usuarios").equalTo("rol","Vendedor");



        viewlista = (ListView)findViewById(R.id.listUsuarios);
        listastrings= new ArrayList<String>(0);


        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for (DataSnapshot usu: dataSnapshot.getChildren()){
                        String nombre = usu.child("nombre").getValue().toString();
                        String usuario = usu.child("usuario").getValue().toString();
                        String telefono = usu.child("celular").getValue().toString();
                      //  String rol = usu.child("rol").getValue().toString();


                        listastrings.add(System.getProperty ("line.separator")+
                                "Nombre:   "+ nombre + System.getProperty ("line.separator")+
                                "Usuario:  "+ usuario+ System.getProperty ("line.separator")+
                                "Teléfono: "+ telefono+ System.getProperty ("line.separator")
                                );

                        adapter = new ArrayAdapter<String>(ActivityDefault.this, android.R.layout.simple_list_item_1,listastrings);
                        viewlista.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });







        //Botón Regresar
        final Button  btnRegresar = findViewById(R.id.btnRegresar);

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registrar = new Intent(ActivityDefault.this, ActivityPrincipal.class);
                startActivity(registrar);
                finish();
            }
        });
    }
}
