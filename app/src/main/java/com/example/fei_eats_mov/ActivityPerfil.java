package com.example.fei_eats_mov;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.fei_eats_mov.login.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.content.ContentValues.TAG;

public class ActivityPerfil extends AppCompatActivity {
    private final int GALLERY_INTENT = 1;
    private FirebaseDatabase dDatabase;
    private FirebaseAuth firebaseAuth = null;

    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        final TextView nombre = findViewById (R.id.textView3);
        final TextView telefono = findViewById (R.id.textView14);
        final TextView correo = findViewById (R.id.textView13);
        final Button button2 = findViewById(R.id.button2);
        final Button button3 =  findViewById(R.id.button3);
        final ImageView image4 = findViewById (R.id.imageView4);
        final Button eliminarUsuario = findViewById(R.id.btnEliminar);

        dDatabase = FirebaseDatabase.getInstance();
        final FirebaseUser user = firebaseAuth.getInstance().getCurrentUser();
        dDatabase.getReference ("Usuarios").child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    //Obtiene el valor de cada campo de un usuario
                    String nombre1 = dataSnapshot.child("Nombre").getValue().toString();
                    String telefono1 = dataSnapshot.child("Telefono").getValue().toString();
                    String correo1 = dataSnapshot.child("Correo").getValue().toString();
                    Uri photoUrl = Uri.parse (dataSnapshot.child ("fotoPerfilURL").getValue().toString ());

                    //Asigna el valor de la variable al TextView correspondiente
                    nombre.setText("Nombre: " + nombre1);
                    telefono.setText("Teléfono: " + telefono1);
                    correo.setText("Correo: " + correo1);
                    //Uso librería Glide para poder mostrar la foto
                    Glide.with (ActivityPerfil.this)
                            .load (photoUrl)
                            .fitCenter()
                            .centerCrop()
                            //Asigna el valor de lo que tiene la URL al imageView correspondiente
                            .into (image4);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        eliminarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth = FirebaseAuth.getInstance();
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ActivityPerfil.this);

                // Configura el titulo.
                alertDialogBuilder.setTitle("¿Deseas eliminar tu perfil?");

                // Configura el mensaje.
                alertDialogBuilder
                        .setMessage("Al dar click en si, ya no tendrás acceso a tu cuenta y perderas todos tus productos ")
                        .setCancelable(false)
                        .setPositiveButton("Si",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                //Si la respuesta es afirmativa se procede a eliminar la autenticación
                                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                                // Obtiene credenciales de autenticación del usuario para volver a autenticarse.
                                AuthCredential credential = EmailAuthProvider.getCredential("user@example.com", "password1234");

                                user.reauthenticate(credential)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                user.delete()
                                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                if (task.isSuccessful()) {
                                                                    Log.d(TAG, "User account deleted.");
                                                                    mAuth.signOut();
                                                                    startActivity(new Intent(ActivityPerfil.this, MainActivity.class));
                                                                    finish();
                                                                }
                                                            }
                                                        });

                                            }
                                        });
                            }
                        })
                        .setNegativeButton("No",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        }).create().show();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityPerfil.this, EditarActivity.class));
            }
        });
        //Botón Regresar
        final Button  btnRegresar2 = findViewById(R.id.regresarpri);

        btnRegresar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registrar = new Intent(ActivityPerfil.this, ActivityPrincipal.class);
                startActivity(registrar);
                finish();
            }
        });

        // boton para editar foto de perfil
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), EditarFoto.class);
                startActivityForResult(intent, 0);
            }
        });
    }




    //

}
