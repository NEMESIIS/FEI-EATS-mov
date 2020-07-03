package com.example.fei_eats_mov;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;


public class EditarFoto extends AppCompatActivity {

    private final int GALLERY_INTENT = 1;
    private StorageReference dStorage;
    private ProgressDialog dProgressDialog;
    private FirebaseDatabase dDatabase;
    private FirebaseAuth firebaseAuth = null;
    private static String urlFoto ="https://firebasestorage.googleapis.com/v0/b/dulces-d61f6.appspot.com/o/fotosPerfil%2Fuser.png?alt=media&token=df7464f7-7379-4d7f-aef8-23f49dc2474c";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_editar_foto);

        final Button btn20 =  findViewById(R.id.button20);
        final Button btn22 =  findViewById(R.id.button22);
        final ImageView foto = findViewById (R.id.imageView20);

        dStorage = FirebaseStorage .getInstance().getReference();
        dProgressDialog = new ProgressDialog (this);

                                                                /*SECCIÓN DE BOTONES*/
        /*--------------------------------------------------------------------------------------------------------------------------*/
        //Botón para abrir la galería del dispositivo
        btn20.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (Intent.ACTION_PICK);
                intent.setType ("image/*");
                startActivityForResult (intent,GALLERY_INTENT);
            }
        });

        //Boton para eliminar la foto de perfil de usuario
        btn22.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(EditarFoto.this);

                // Configura el titulo.
                alertDialogBuilder.setTitle("Eliminar foto");

                // Configura el mensaje.
                alertDialogBuilder
                        .setMessage("¿Seguro que deseas eliminar la foto de perfil?")
                        .setCancelable(false)
                        .setPositiveButton("Si",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                //Si la respuesta es afirmativa se elimina la foto.
                                Map<String, Object> map = new HashMap<>();
                                map.put("fotoPerfilURL", urlFoto);

                                FirebaseUser user = firebaseAuth.getInstance ().getCurrentUser();
                                dDatabase.getReference ().child("Usuarios").child(user.getUid()).updateChildren(map);

                                Toast.makeText (EditarFoto.this, "Foto de perfil eliminada", Toast.LENGTH_SHORT).show ( );
                            }
                        })
                        .setNegativeButton("No",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        }).create().show();
            }
        });




                                        /*MÉTODO PARA MOSTRAR LA FOTO DE PERFIL*/
    /*-----------------------------------------------------------------------------------------------------------------------------*/
        dDatabase = FirebaseDatabase.getInstance();
        FirebaseUser user = firebaseAuth.getInstance ().getCurrentUser();
        dDatabase.getReference ("Usuarios").child(user.getUid ()).addValueEventListener (new ValueEventListener ( ) {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Uri photoUrl = Uri.parse (dataSnapshot.child ("fotoPerfilURL").getValue().toString ());
                Glide.with (EditarFoto.this)
                        .load (photoUrl)
                        .fitCenter()
                        .centerCrop()
                        //Asigna el valor de lo que tiene la URL al imageView correspondiente
                        .into (foto);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        }

                                              //Método para subir la foto
    /*-----------------------------------------------------------------------------------------------------------------------------*/

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult (requestCode, resultCode, data);

        if (requestCode == GALLERY_INTENT && resultCode == RESULT_OK) {
            //ProgressDialog muestra que se está subiendo la imágen
            dProgressDialog.setTitle ("Subiendo foto...");
            dProgressDialog.setMessage ("Subiendo foto, espera");
            dProgressDialog.setCancelable (false);
            dProgressDialog.show ();

            Uri uri = data.getData ( );
            StorageReference filePath = dStorage.child ("fotosPerfil").child (uri.getLastPathSegment ( ));

            filePath.putFile (uri).addOnSuccessListener (new OnSuccessListener<UploadTask.TaskSnapshot> ( ) {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    if (taskSnapshot.getMetadata ( ) != null) {
                        if (taskSnapshot.getMetadata ( ).getReference ( ) != null) {
                            Task<Uri> result = taskSnapshot.getStorage ( ).getDownloadUrl ( );
                            result.addOnSuccessListener (new OnSuccessListener<Uri> ( ) {
                                @Override
                                public void onSuccess(Uri uri) {
                                    dProgressDialog.dismiss ();
                                    String imageUrl = uri.toString ( );

                                    Map<String, Object> map = new HashMap<> ();
                                    map.put ("fotoPerfilURL", imageUrl);

                                    FirebaseUser user = firebaseAuth.getInstance ().getCurrentUser ();
                                    dDatabase.getReference ().child ("Usuarios").child (user.getUid ()).updateChildren (map);

                                    Toast.makeText (EditarFoto.this, "Foto Subida con éxito", Toast.LENGTH_SHORT).show ();

                                }
                            });
                        }
                    }
                }
            });

        }
                                                    //Método para subir la foto con la cámara
        /*-----------------------------------------------------------------------------------------------------------------------------*/


    }


}
