package com.example.fei_eats_mov;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityPerfil extends AppCompatActivity {



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);







        //------Botones
        //Botón Regresar
        final Button btnBackPer = findViewById(R.id.btnBackPer);

        btnBackPer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registrar = new Intent(ActivityPerfil.this, ActivityPrincipal.class);
                startActivity(registrar);
                finish();
            }
        });
    }
}
