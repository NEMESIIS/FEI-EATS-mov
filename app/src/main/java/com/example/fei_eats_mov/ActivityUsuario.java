package com.example.fei_eats_mov;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ActivityUsuario extends AppCompatActivity {

    EditText etNombreU;
    EditText etCorreoU;
    EditText etUsuarioU;
    EditText etTelefonoU;
    EditText etContrasenaU;

    Spinner sp_rol;

    Button btnRegistrar;
    private DatabaseReference ddulces;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);

        etNombreU = findViewById(R.id.etNombreU);
        etCorreoU = findViewById(R.id.etCorreoU);
        etUsuarioU = findViewById(R.id.etUsuarioU);
        etTelefonoU = findViewById(R.id.etTelefonoU);
        etContrasenaU = findViewById(R.id.etContrasenaU);



        sp_rol = findViewById(R.id.sp_rol);
        btnRegistrar = findViewById(R.id.btnRegistrar);

        //Texto
        final TextView txtIniciarSesion = findViewById(R.id.txtIniciarSesion);

        ddulces = FirebaseDatabase.getInstance().getReference("Usuarios");

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarUsuario();
            }
        });

        txtIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registrar = new Intent(ActivityUsuario.this, MainActivity.class);
                startActivity(registrar);
                finish();
            }
        });

    }

    //Función para el registro de Usuarios
    public void registrarUsuario(){
        String nombre = etNombreU.getText().toString();
        String correo = etCorreoU.getText().toString();
        String usuario = etUsuarioU.getText().toString();
        String telefono = etTelefonoU.getText().toString();
        String contrasena = etContrasenaU.getText().toString();
        String rol = sp_rol.getSelectedItem().toString();



        if (!TextUtils.isEmpty(nombre)){
            String id =ddulces.push().getKey();
            Usuario usu = new Usuario(id,nombre,correo,usuario,telefono,contrasena,rol);
            ddulces.child(id).setValue(usu);

            Toast.makeText(this,"Usuario Registrado", Toast.LENGTH_LONG).show();

            Intent intUsuario2 = new Intent(ActivityUsuario.this,MainActivity.class);
            startActivity(intUsuario2);
        }else{
            Toast.makeText(ActivityUsuario.this,"Se debe rellenar todos los campos", Toast.LENGTH_LONG).show();
        }
    }

    //Funcón para el texto



}
