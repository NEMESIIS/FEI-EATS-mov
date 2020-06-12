package com.example.fei_eats_mov;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;

public class ActivityPrincipal extends AppCompatActivity {

    private DatabaseReference ddulces;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);




        //Métodos

        //---Ver perfil texto
        final TextView txtPerfil = findViewById(R.id.txtPerfil);

        txtPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registrar = new Intent(ActivityPrincipal.this, ActivityPerfil.class);
                startActivity(registrar);
                finish();
            }
        });
        //--Ver perfil imagen
        @SuppressLint("WrongViewCast") final ImageView imageView4 = findViewById(R.id.imageView4);

        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registrar = new Intent(ActivityPrincipal.this, ActivityPerfil.class);
                startActivity(registrar);
                finish();
            }
        });


        //---agregar producto texto
        final TextView txtProducto = findViewById(R.id.txtProducto);

        txtProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registrar = new Intent(ActivityPrincipal.this, ActivityProducto.class);
                startActivity(registrar);
                finish();
            }
        });
        //---agregar producto imagen
        @SuppressLint("WrongViewCast") final ImageView imageView7 = findViewById(R.id.imageView7);

        imageView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registrar = new Intent(ActivityPrincipal.this, ActivityProducto.class);
                startActivity(registrar);
                finish();
            }
        });
        //-----ver vendedores texto
        final TextView txtVendedores = findViewById(R.id.txtVendedores);

        txtVendedores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registrar = new Intent(ActivityPrincipal.this, ActivityDefault.class);
                startActivity(registrar);
                finish();
            }
        });
        //-----ver vendedores imagen
        @SuppressLint("WrongViewCast") final ImageView imageView3 = findViewById(R.id.imageView3);

        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registrar = new Intent(ActivityPrincipal.this, ActivityDefault.class);
                startActivity(registrar);
                finish();
            }
        });


        //-----ver productos texto
        final TextView txtProductos = findViewById(R.id.txtProductos);

        txtProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registrar = new Intent(ActivityPrincipal.this, ActivityDefault2.class);
                startActivity(registrar);
                finish();
            }
        });
        //----Ver productos imagen
        @SuppressLint("WrongViewCast") final ImageView imageView6 = findViewById(R.id.imageView6);

        imageView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registrar = new Intent(ActivityPrincipal.this, ActivityDefault2.class);
                startActivity(registrar);
                finish();
            }
        });

        //Botón Salir/Cerrar Sesión
        final Button btnCerrarSesion = findViewById(R.id.btnCerrarSesion);

        btnCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registrar = new Intent(ActivityPrincipal.this, MainActivity.class);
                startActivity(registrar);
                finish();
            }
        });

    }
}
