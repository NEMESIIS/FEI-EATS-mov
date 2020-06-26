package com.example.fei_eats_mov;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.fei_eats_mov.login.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

public class ActivityUsuario extends AppCompatActivity {
    private EditText TelefonoEdt;
    private EditText CorreoEdt;
    private EditText ContrasenaEdt;
    private Button RegistrarBtn;
    private EditText Nombretext;

    //VARIABLES PARA ALMACENAR LOS DATOS
    private String telefono = "";
    private String correo = "";
    private String contrasena = "";
    private String nombre ="";
    private static String urlFoto ="https://firebasestorage.googleapis.com/v0/b/fei-uv.appspot.com/o/fotosPerfil%2Fuser.png?alt=media&token=a367e233-200d-407f-a693-fb30c4de3b81";
    String fotou;
    private Button registarProducto;
    private Button btnFotoP;
    private final int GALLERY_INTENT = 1;
    private final int REQUEST_IMAGE_CAPTURE = 1;
    private ImageView fotoP;
    //VARIABLES DE FIREBASE
    private FirebaseDatabase dDatabase;
    private DatabaseReference dReference;
    private FirebaseAuth mAuth = null; //AUTENTICACIÓN
    private StorageReference dStorage;

    private FirebaseUser user;

    private FirebaseAuth firebaseAuth = null;

    Spinner sp_rol;

    //VARIABLES DE FIREBASE
    //FirebaseAuth mAuth; //AUTENTICACIÓN
    DatabaseReference mDatabase;//USAR LA BASE DE DATOS REAL TIME


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);

        TelefonoEdt = (EditText) findViewById(R.id.TelefonoEdt);
        CorreoEdt = (EditText) findViewById(R.id.CorreoEdt);
        ContrasenaEdt =(EditText) findViewById(R.id.ContrasenaEdt);
        RegistrarBtn = (Button) findViewById(R.id.RegistrarBtn);
        Nombretext = (EditText) findViewById(R.id.Nombre);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        btnFotoP =(Button) findViewById (R.id.button);
        fotoP = findViewById (R.id.imageView5);

        dDatabase = FirebaseDatabase.getInstance();
        dReference = FirebaseDatabase.getInstance().getReference();
        dStorage = FirebaseStorage.getInstance().getReference();
        sp_rol = findViewById(R.id.sp_rol);


        RegistrarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Se asigna los que el usuario ingresan en los EditText a las variables
                telefono = TelefonoEdt.getText().toString();
                correo = CorreoEdt.getText().toString();
                contrasena = ContrasenaEdt.getText().toString();
                nombre = Nombretext.getText().toString();

                if(!telefono.isEmpty() && !correo.isEmpty() && !contrasena.isEmpty() && !nombre.isEmpty()){
                    if(contrasena.length()>=6 && telefono.length()==10){
                        registrarUsuario();
                    }else{
                        Toast.makeText(ActivityUsuario.this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_LONG).show();
                        Toast.makeText(ActivityUsuario.this, "El telefono debe ser de 10 dígitos", Toast.LENGTH_LONG).show();
                    }

                }else{
                    Toast.makeText(ActivityUsuario.this, "Completar campos vacíos", Toast.LENGTH_LONG).show();
                }
            }
        });


        final TextView textViewLogin = findViewById(R.id.textLogin);

        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(ActivityUsuario.this, MainActivity.class);
                startActivity(login);
                finish();
            }
        });

        btnFotoP.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (Intent.ACTION_PICK);
                intent.setType ("image/*");
                startActivityForResult (intent,GALLERY_INTENT);
            }
        });

    }



    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult (requestCode, resultCode, data);

        if (requestCode == GALLERY_INTENT && resultCode == RESULT_OK) {
            Uri uri = data.getData ( );
            StorageReference filePath = dStorage.child ("fotosPerfil").child (uri.getLastPathSegment ( ));

            filePath.putFile (uri).addOnSuccessListener (new OnSuccessListener<UploadTask.TaskSnapshot>( ) {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    if (taskSnapshot.getMetadata ( ) != null) {
                        if (taskSnapshot.getMetadata ( ).getReference ( ) != null) {
                            Task<Uri> result = taskSnapshot.getStorage ( ).getDownloadUrl ( );
                            result.addOnSuccessListener (new OnSuccessListener<Uri> ( ) {
                                @Override
                                public void onSuccess(Uri uri) {
                                    final String imageUrl = uri.toString ( );
                                    fotou = uri.toString();
                                    Glide.with (ActivityUsuario.this)
                                            .load (imageUrl)
                                            .fitCenter()
                                            .centerCrop()
                                            //Asigna el valor de lo que tiene la URL al imageView correspondiente
                                            .into (fotoP);
                                    Toast.makeText (ActivityUsuario.this, "Foto de Subida con éxito", Toast.LENGTH_SHORT).show ( );

                                }
                            });
                        }
                    }
                }
            });

        }

    }
    private void registrarUsuario(){
        mAuth.createUserWithEmailAndPassword(correo,contrasena).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            String rol1 = sp_rol.getSelectedItem().toString();
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Map<String, Object> map = new HashMap<>();
                    map.put("Telefono", telefono);
                    map.put("Correo", correo);
                    map.put("Contrasena", contrasena);
                    map.put("Nombre", nombre);
                    map.put("fotoPerfilURL",fotou);
                    map.put("rol",rol1);

                    String id = mAuth.getCurrentUser().getUid();
                    mDatabase.child("Usuarios").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {
                            if(task2.isSuccessful()){
                                startActivity( new Intent(ActivityUsuario.this, ActivityPrincipal.class));
                                finish();
                            }else{
                                Toast.makeText(ActivityUsuario.this, "No se crearon los datos", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }else{
                    Toast.makeText(ActivityUsuario.this, "No se pudo registrar el usuario, intenta con otro correo", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}

