package com.example.pyshco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Inicio extends AppCompatActivity {

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_inicio);

        ImageView imageChat = findViewById(R.id.imageChat);

        imageChat.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                // Aquí abres la nueva actividad al hacer clic en la imagen
                Intent intent = new Intent(Inicio.this, Chats.class);
                startActivity(intent);
            }
        });
    }

}