package com.example.pyshco;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    EditText EmailLogin;
    EditText passLogin;
    Button btnLogin;
    TextView TextRegistrarme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    EmailLogin= findViewById(R.id.EmailLogin);
    passLogin = findViewById(R.id.passLogin);
    btnLogin = findViewById(R.id.btnLogin);
    TextRegistrarme= findViewById(R.id.TextRegistrarme);

    }



    public void Registrarse (View view) {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
    public void Inicio (View view) {
        Intent intent = new Intent(this, Inicio.class);
        startActivity(intent);
    }

}