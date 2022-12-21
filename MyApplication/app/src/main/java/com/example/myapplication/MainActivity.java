package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    TextView mCreateBtn;
    Button mbuttonReg;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCreateBtn = findViewById(R.id.textlog);
        mCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),singinpage.class));
            }
        });
        mbuttonReg = findViewById(R.id.buttonReg);
        mbuttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),registerpage.class));
            }
        });

    }

    public void logout (View view){
        FirebaseAuth.getInstance().signOut();//Deslogueo
        startActivity(new Intent(getApplicationContext(),Home.class));
        finish();
    }

}