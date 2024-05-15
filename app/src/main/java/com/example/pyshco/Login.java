package com.example.pyshco;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    EditText editTextEmail, editTextPassword;
    Button btnRegistro;


    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();


        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        btnRegistro = findViewById(R.id.btnRegistro);

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });


    }


    private void registerUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(Login.this, "Por favor ingresa el correo y la contraseña.",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        // Crear un nuevo usuario con el correo y contraseña proporcionados
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Registro exitoso
                            Log.d("Login", "createUserWithEmail:success");
                            Toast.makeText(Login.this, "Registro exitoso.",
                                    Toast.LENGTH_SHORT).show();

                            // Redirigir al usuario a la actividad "Inicio"
                            Intent intent = new Intent(Login.this, Inicio.class);
                            startActivity(intent);
                            finish(); // Finalizar la actividad actual

                        } else {
                            // Fallo en el registro
                            Log.w("Login", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(Login.this, "Fallo en el registro.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }




    public void InicioSesion (View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}