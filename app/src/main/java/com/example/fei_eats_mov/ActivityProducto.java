package com.example.fei_eats_mov;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ActivityProducto extends AppCompatActivity {

    private EditText etNombreP;
    private EditText etPrecioP;
    private EditText etDescripcionP;
    private EditText etVendedorx;
    private EditText etCelularx;
    private Spinner spCategoriaP;
    private Button btnRegistrarP,btnFotoP;
    private final int GALLERY_INTENT = 1;
    private final int REQUEST_IMAGE_CAPTURE = 1;
    private ImageView fotoP;

    //VARIABLES PARA ALMACENAR LOS DATOS
    private String nombreP = "";
    private String precioP = "";
    private String descripcionP = "";
    private String categoriaP ="";
    private String id_usaurio="";
    private String vendedorx="";
    private String celularx="";
    String fotou;

    //VARIABLES DE FIREBASE

    private FirebaseDatabase dDatabase;
    private DatabaseReference dReference;
    private FirebaseAuth mAuth = null; //AUTENTICACIÓN
    private StorageReference dStorage;

    private FirebaseUser user;

    private FirebaseAuth firebaseAuth = null;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_producto);

        etNombreP = findViewById(R.id.etNombreP);
        etPrecioP = findViewById(R.id.etPrecioP);
        etDescripcionP = findViewById(R.id.etDescripcionP);
        etVendedorx = findViewById(R.id.etVdendedorx);
        etCelularx = findViewById(R.id.etCelularx);

        spCategoriaP = findViewById(R.id.spCategoriaP);
        btnRegistrarP = findViewById(R.id.btnRegistrarP);

        fotoP = findViewById (R.id.imageView5);

        dDatabase = FirebaseDatabase.getInstance();
        dReference = FirebaseDatabase.getInstance().getReference();
        dStorage = FirebaseStorage.getInstance().getReference();

        user = mAuth.getInstance ().getCurrentUser();

        //Botón regresar
        //Botón Regresar
        final Button  btnB = findViewById(R.id.btnB);

        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registrar = new Intent(ActivityProducto.this, ActivityPrincipal.class);
                startActivity(registrar);
                finish();
            }
        });

        //Botón registrar
        btnRegistrarP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombreP = etNombreP.getText().toString();
                precioP = etPrecioP.getText().toString();
                descripcionP = etDescripcionP.getText().toString();
                vendedorx = etVendedorx.getText().toString();
                celularx = etCelularx.getText().toString();
                categoriaP = spCategoriaP.getSelectedItem().toString();

              //  id_usaurio = dDatabase.getReference ("Usuarios").child(user.getUid()).toString();

                if(nombreP.isEmpty() || precioP.isEmpty() || descripcionP.isEmpty() ||vendedorx.isEmpty()|| celularx.isEmpty()){
                    validarDatos();

                }else {
                    guardarProducto();
                    limpiarDatos();
                    //Toast.makeText(ActivityProducto.this, "Producto Registrado", Toast.LENGTH_LONG).show();
                    Toast.makeText(ActivityProducto.this,"Producto Registrado", Toast.LENGTH_LONG).show();
                    Intent intCanciones2 = new Intent(ActivityProducto.this, ActivityPrincipal.class);
                    startActivity(intCanciones2);
                }

            }
        });
/*
        btnFotoP.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (Intent.ACTION_PICK);
                intent.setType ("image/*");
                startActivityForResult (intent,GALLERY_INTENT);
            }
        });
*/

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult (requestCode, resultCode, data);

        if (requestCode == GALLERY_INTENT && resultCode == RESULT_OK) {
            Uri uri = data.getData ( );
            StorageReference filePath = dStorage.child ("fotosProducto").child (uri.getLastPathSegment ( ));

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
                                    Glide.with (ActivityProducto.this)
                                            .load (imageUrl)
                                            .fitCenter()
                                            .centerCrop()
                                            //Asigna el valor de lo que tiene la URL al imageView correspondiente
                                            .into (fotoP);
                                    Toast.makeText (ActivityProducto.this, "Foto de Subida con éxito", Toast.LENGTH_SHORT).show ( );

                                }
                            });
                        }
                    }
                }
            });

        }


    }

    public void guardarProducto(){
        String id_producto = UUID.randomUUID().toString();
        Map<String, Object> map = new HashMap<>();
        map.put("ID_usuario", id_usaurio);
        map.put("Nombre Producto", nombreP);
        map.put("Precio Producto", precioP);
        map.put("Vendedor",vendedorx);
        map.put("Celular",celularx);
        map.put("Rol","Vendedor");
        map.put("descripcion", descripcionP);
        map.put("Categoria", categoriaP);
        map.put("fotoProductoURL", fotou);


        dReference.child("Productos").child(id_producto).setValue(map);
    }

    public void limpiarDatos(){
        etNombreP.setText("");
        etPrecioP.setText("");
        etDescripcionP.setText("");
        fotoP.setImageResource(R.drawable.dulces);
        fotou = "";




    }

    public void validarDatos(){
        if(nombreP.isEmpty()){
            etNombreP.setError("Requerido");
        }

        if(precioP.isEmpty()){
            etPrecioP.setError("Requerido");
        }

        if(descripcionP.isEmpty()){
            etDescripcionP.setError("Requerido");
        }

        if (vendedorx.isEmpty()){
            etVendedorx.setError("Requerido");
        }

        if (celularx.isEmpty()){
            etCelularx.setError("Requerido");
        }
    }
}