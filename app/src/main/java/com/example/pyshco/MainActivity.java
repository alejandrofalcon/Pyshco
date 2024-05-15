package com.example.pyshco;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//import com.google.android.gms.auth.api.signin.GoogleSignIn;
//import com.google.android.gms.auth.api.signin.GoogleSignInClient;
//import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {

    EditText EmailLogin;
    EditText passLogin;
    Button btnLogin;
    TextView TextRegistrarme;
    Button btnGmail;
    private FirebaseAuth mAuth;

    //private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp(this);

        mAuth = FirebaseAuth.getInstance();

        btnGmail= findViewById(R.id.btnGmail);
        EmailLogin= findViewById(R.id.EmailLogin);
        passLogin = findViewById(R.id.passLogin);
        btnLogin = findViewById(R.id.btnLogin);
        TextRegistrarme= findViewById(R.id.TextRegistrarme);

       /* GoogleSignInOptions gso= new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);*/





        btnLogin.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            loginUser();
        }
    });
}

    private void loginUser() {
        String email = EmailLogin.getText().toString().trim();
        String password = passLogin.getText().toString().trim();

        // Validar si el correo electrónico y la contraseña no están vacíos
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(MainActivity.this, "Por favor ingresa el correo y la contraseña.",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        // Iniciar sesión con el correo electrónico y la contraseña proporcionados
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Inicio de sesión exitoso
                            Log.d("MainActivity", "signInUser:success");
                            Toast.makeText(MainActivity.this, "Inicio de sesión exitoso.",
                                    Toast.LENGTH_SHORT).show();

                            // Redirigir a la actividad "Inicio"
                            Intent intent = new Intent(MainActivity.this, Inicio.class);
                            startActivity(intent);
                            finish(); // Finalizar la actividad actual

                        } else {
                            // Fallo en el inicio de sesión
                            Log.w("MainActivity", "signInUser:failure", task.getException());

                            // Mostrar mensaje de fallo en el inicio de sesión
                            Toast.makeText(MainActivity.this, "Fallo en el inicio de sesión.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Manejar cualquier error general de la tarea
                        Log.e("MainActivity", "signInUser:failure", e);
                        Toast.makeText(MainActivity.this, "Fallo en el inicio de sesión.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
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