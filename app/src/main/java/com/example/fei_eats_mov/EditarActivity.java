package com.example.fei_eats_mov;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class EditarActivity extends AppCompatActivity {

    //Variables ocupadas
    private EditText NombreE;
    private EditText TelefonoE;
    private EditText CorreoE;
    private EditText ContrasenaE;
    private Button btnCambios;


    //VARIABLES PARA ALMACENAR LOS DATOS
    private String nombrex = "";
    private String telefonox = "";
    private String correox = "";
    private String contrasenax = "";


    //Referencias
    private DatabaseReference dDatabase;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_edit);

        final Button btnCambios = findViewById(R.id.btnCambios);
     //   NombreE = (EditText) findViewById(R.id.txtNombre);
        final EditText TelefonoE = findViewById(R.id.txtTelefono);
        final EditText CorreoE = findViewById(R.id.txtCorreo);
        final EditText ContrasenaE = findViewById(R.id.txtContrasena);

        ContrasenaE.setEnabled(false);
        CorreoE.setEnabled(false);

        dDatabase = FirebaseDatabase.getInstance().getReference(); //Aqui obtienes la referencia de la base de datos
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        //Referencia al nodo              aqui la referencia al usuario
        assert user != null;
        dDatabase.child("Usuarios").child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    //Obtiene el valor de cada campo de un usuario
                    String nombre1 = dataSnapshot.child("Nombre").getValue().toString();
                    String telefono1 = dataSnapshot.child("Telefono").getValue().toString();
                    String correo1 = dataSnapshot.child("Correo").getValue().toString();
                    String contrasena1 = dataSnapshot.child("Contrasena").getValue().toString();

                    //Asigna el valor de la variable al TextView correspondiente
                    NombreE.setText(nombre1);
                    TelefonoE.setText(telefono1);
                    CorreoE.setText(correo1);
                    ContrasenaE.setText(contrasena1);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        btnCambios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombrex = NombreE.getText().toString();
                telefonox = TelefonoE.getText().toString();
                correox = CorreoE.getText().toString();
                contrasenax = ContrasenaE.getText().toString();

                Map<String, Object> map = new HashMap<>();
                map.put("Telefono", telefonox);
                map.put("Correo", correox);
                map.put("Contrasena", contrasenax);
                map.put("Nombre", nombrex);

                dDatabase = FirebaseDatabase.getInstance().getReference(); //Aqui obtienes la referencia de la base de datos
                FirebaseUser user = firebaseAuth.getInstance ().getCurrentUser();

                dDatabase.child("Usuarios").child(user.getUid()).updateChildren(map);
                //Regresa a la pantalla de inicio
                startActivity( new Intent(EditarActivity.this, ActivityPrincipal.class));
                //Muestra un Toast al usuario de que los datos fuern actualizados correctamente
                Toast toast1 = Toast.makeText(getApplicationContext(), "Datos actualizados", Toast.LENGTH_SHORT);
                toast1.show();
                finish();


            }
        });

    }

}