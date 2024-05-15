package com.example.pyshco;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import de.hdodenhof.circleimageview.CircleImageView;

public class Chats extends AppCompatActivity {
    private CircleImageView fotoPerfil;
    private TextView nombre;
    private RecyclerView mensaje;
    private EditText txtMensaje;
    private Button btnEnviar;

    private AdapterMensajes adapter;

    private ImageButton btnEnviarFoto;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private static final int PHOTO_SEND =1;
    private FirebaseStorage storage;
    private StorageReference storageReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats);


        fotoPerfil = (CircleImageView) findViewById(R.id.fotoPerfil);
        nombre= (TextView) findViewById(R.id.nommbre);
        mensaje= (RecyclerView) findViewById(R.id.mensaje);
        txtMensaje=(EditText) findViewById(R.id.txtMensaje);
        btnEnviar=(Button) findViewById(R.id.btnEnviar);
        btnEnviarFoto= (ImageButton)findViewById(R.id.btnEnviarFoto);

        database = FirebaseDatabase.getInstance();
        databaseReference= database.getReference("chat");//Sala de chat

        storage= FirebaseStorage.getInstance();


        adapter = new AdapterMensajes(this);
        LinearLayoutManager l = new LinearLayoutManager(this);
        mensaje.setLayoutManager(l);
        mensaje.setAdapter(adapter);
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.push().setValue(new Mensaje(txtMensaje.getText().toString(), nombre.getText().toString(),"","1","00:00"));
                txtMensaje.setText("");
            }
        });
        btnEnviarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.setType("image/jpeg");
                i.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                startActivityForResult(Intent.createChooser(i, "Selecciona una foto"),PHOTO_SEND);
            }
        });



        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                setScrollbar();
            }
        });


        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String previousChildName) {

               Mensaje m = dataSnapshot.getValue(Mensaje.class);
               adapter.addMensaje(m);

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void setScrollbar(){

        mensaje.scrollToPosition(adapter.getItemCount()-1);
    }
    protected void onActivityResult (int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PHOTO_SEND && resultCode == RESULT_OK){
            Uri u = data.getData();
            storageReference = storage.getReference("imagenes_chat");
            final StorageReference fotoReferencia = storageReference.child(u.getLastPathSegment());
            fotoReferencia.putFile(u).addOnSuccessListener(this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri u = taskSnapshot.getStorage().getDownloadUrl().getResult();
                    Mensaje m = new Mensaje("Kevin te a enviado una foto", nombre.getText().toString(),"",u.toString(),"2","00:00");
                    databaseReference.push().setValue(m);
                }
            });

        }
    }
}