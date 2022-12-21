package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class singinpage extends AppCompatActivity {

    EditText mPassword, mEmail;
    Button mlogginBtn;
    FirebaseAuth fAuth;
    ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singinpage);

        mEmail        = findViewById(R.id.edtEmail);
        mPassword     = findViewById(R.id.edtPass);
        progressBar   = findViewById(R.id.progressBar2);
        fAuth         = FirebaseAuth.getInstance();
        mlogginBtn    = findViewById(R.id.buttonSig);

        mlogginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                //Autenticación Usuario

                fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(singinpage.this, "Logeado Exitosamente", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), Home.class));
                        }else{
                            Toast.makeText(singinpage.this, "Datos Incorrectos !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

    }
}