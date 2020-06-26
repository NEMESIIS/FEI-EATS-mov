package com.example.fei_eats_mov;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fei_eats_mov.login.MainActivity;

public class ActivityInformacion extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion);

        //--------Botón Salir de pantalla Información
        final Button btnRegresar = findViewById(R.id.btnSalir1);

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registrar = new Intent(ActivityInformacion.this, MainActivity.class);
                startActivity(registrar);
                finish();
            }
        });
        //------------Termina botón de regresar
    }
}
