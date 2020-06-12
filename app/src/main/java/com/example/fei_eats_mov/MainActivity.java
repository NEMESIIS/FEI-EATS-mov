package com.example.fei_eats_mov;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    //variables ocupadas para el login
    //private FirebaseAuth mAuth;
    private EditText etUsuario, etContra;
    private FirebaseAuth auth;
    private ProgressBar progressBar;
    private Button btnIniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       //obtener instancia de autenticación de firebase
        auth = FirebaseAuth.getInstance();
        //Si la instancia es distinta de null
        if (auth.getCurrentUser() !=null){
            startActivity(new Intent(MainActivity.this,ActivityDefault.class));
            finish();
        }

       //obtención de referencias
        etUsuario = findViewById(R.id.etUsuario);
        etContra = findViewById(R.id.etContra);
        btnIniciar = findViewById(R.id.btnIniciar);

        //Obtener instancia de autenticación firebase
        auth = FirebaseAuth.getInstance();

        //Acción para el botón ingresar
        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //obtener valores de los editText
                String usuario = etUsuario.getText().toString();
                final String password = etContra.getText().toString();
                //Validación
                if (TextUtils.isEmpty(usuario)){
                    Toast.makeText(getApplicationContext(), "Introduce el Usuario",Toast.LENGTH_SHORT).show();
                    return;
                }
                //Validar si se ingresó contraseña
                if (TextUtils.isEmpty(password)){
                    Toast.makeText(getApplicationContext(),"Introduce la contraseña",Toast.LENGTH_SHORT).show();
                    return;
                }

                //Si usuario existe
                auth.signInWithEmailAndPassword(usuario,password)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                      Intent intent = new Intent(MainActivity.this, ActivityDefault.class);
                      startActivity(intent);
                      finish();
                            }
                        });
            }
        });


        final TextView  txtRegistrar = findViewById(R.id.txtRegistrar);

        txtRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registrar = new Intent(MainActivity.this, ActivityUsuario.class);
                startActivity(registrar);
                finish();
            }
        });

        //Regristro de producto prueba
        final TextView  txtRegistrar2 = findViewById(R.id.txtRegistrar2);

        txtRegistrar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registrar2 = new Intent(MainActivity.this, ActivityDefault.class);
                startActivity(registrar2);
                finish();
            }
        });

    }
}
