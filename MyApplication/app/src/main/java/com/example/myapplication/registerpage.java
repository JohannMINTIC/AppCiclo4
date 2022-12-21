package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;

public class registerpage extends AppCompatActivity {

    EditText mPhone,mFullname, mPassword, mEmail;
    Button mRegisterBtn;
    FirebaseAuth fAuth;
    ProgressBar progressBar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerpage);

        mEmail        = findViewById(R.id.edtEmail);
        mFullname     = findViewById(R.id.edtName);
        mPassword     = findViewById(R.id.edtPass);
        mPhone        = findViewById(R.id.edtPhone);
        mRegisterBtn  = findViewById(R.id.button1);

        fAuth         = FirebaseAuth.getInstance();
        progressBar   = findViewById(R.id.progressBar);


        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
        mRegisterBtn.setOnClickListener(view -> {
            String email    = mEmail.getText().toString().trim();
            String password = mPassword.getText().toString().trim();

            if(TextUtils.isEmpty(email)) {
                mEmail.setError("Correo Requerido");
                return;
            }
            if(TextUtils.isEmpty(password)){
                mPassword.setError("Contraseña Requerida");
                return;
            }
            if(password.length() < 6){
                mPassword.setError("Contraseña debe ser de 6 carácteres");
                return;
            }

            progressBar.setVisibility(View.VISIBLE);
            //Se registra usuario en firebase
            
            fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    Toast.makeText(registerpage.this, "Usuario Creado", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }else{
                    Toast.makeText(registerpage.this, "Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}