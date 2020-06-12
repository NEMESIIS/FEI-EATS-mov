package com.example.fei_eats_mov;

import android.os.Bundle;
import android.widget.ArrayAdapter;
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

public class ActivityUsuario2 extends AppCompatActivity {
    private DatabaseReference ddulces;
    ListView viewlista;
    ArrayList<String> listastrings;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);
/*
        ddulces = FirebaseDatabase.getInstance().getReference();

        Query query = ddulces.child("Usuarios");

        viewlista = (ListView)findViewById(R.id.listUsuarios);
        listastrings= new ArrayList<String>(0);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for (DataSnapshot usu: dataSnapshot.getChildren()){
                        String nombre = usu.child("Nombre: ").getValue().toString();
                        String telefono = usu.child("Telefono: ").getValue().toString();


                        listastrings.add(nombre+" - "+telefono);
                        adapter = new ArrayAdapter<String>(ActivityUsuario2.this, android.R.layout.simple_list_item_1,listastrings);
                        viewlista.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
*/
    }




}
