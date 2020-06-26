package com.example.fei_eats_mov;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

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

public class ActivityDefault2 extends AppCompatActivity {

    private DatabaseReference ddulces;
    ListView viewlista;
    ArrayList<String> listastrings;
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);

        //ddulces = FirebaseDatabase.getInstance().getReference();
        //Query query = ddulces.child("Productos");

       // viewlista = (ListView)findViewById(R.id.listProductos);
        //listastrings= new ArrayList<String>(0);
/*
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for (DataSnapshot produ: dataSnapshot.getChildren()){
                        String nombre = produ.child("Nombre Producto").getValue().toString();
                        String precio = produ.child("Precio Producto").getValue().toString();
                        String descripcion = produ.child("descripcion").getValue().toString();
                        String categoria = produ.child("Categoria").getValue().toString();

                        listastrings.add(System.getProperty ("line.separator")+
                                "Producto:    "+ nombre + System.getProperty ("line.separator")+
                                "Precio:     $"+ precio+ System.getProperty ("line.separator")+
                                "Descripción: "+ descripcion+ System.getProperty ("line.separator")+
                                "Categoria:   "+ categoria+ System.getProperty ("line.separator"));

                        adapter = new ArrayAdapter<String>(ActivityDefault2.this, android.R.layout.simple_list_item_1,listastrings);
                        viewlista.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
*/
        //---------Inicia apartado de botones


        //---Ver Categoria dulces
        final TextView txtDulces = findViewById(R.id.txtDulces);

        txtDulces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), ActivityDulces.class);
                startActivityForResult(intent, 0);
            }
        });
        //--Ver perfil imagen
        @SuppressLint("WrongViewCast") final ImageView imgDulces = findViewById(R.id.imgDulces);

        imgDulces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), ActivityDulces.class);
                startActivityForResult(intent, 0);
            }
        });
        //---------Ver categoria Comida
        final TextView txtComida = findViewById(R.id.txtComida);

        txtComida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), ActivityComida.class);
                startActivityForResult(intent, 0);
            }
        });
        //--Ver perfil imagen
        @SuppressLint("WrongViewCast") final ImageView imgComida = findViewById(R.id.imgComida);

        imgComida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), ActivityComida.class);
                startActivityForResult(intent, 0);
            }
        });
        //---------Ver categoria Todos
        final TextView txtTodos = findViewById(R.id.txtTodos);

        txtTodos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), ActivityTodos.class);
                startActivityForResult(intent, 0);
            }
        });
        //--Ver perfil imagen
        @SuppressLint("WrongViewCast") final ImageView imgTodos = findViewById(R.id.imgTodos);

        imgTodos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), ActivityTodos.class);
                startActivityForResult(intent, 0);
            }
        });
        //Botón Regresar
        final Button  btnRegresar2 = findViewById(R.id.btnRegresar2);

        btnRegresar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registrar = new Intent(ActivityDefault2.this, ActivityPrincipal.class);
                startActivity(registrar);
                finish();
            }
        });
    }
}
