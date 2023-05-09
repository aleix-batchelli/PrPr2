package com.example.practica_final;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button loginButton;
    private Button registerButton;
    private EditText emailET;
    private EditText passwordET;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        setComponents();

        registerButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create new activity for register view
            }
        }));

        loginButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get data from email edit text and password edit text and check credentials

                // create new activity for feed view
            }
        }));

    }

    private void setComponents() {
        this.loginButton = findViewById(R.id.loginButton);
        this.registerButton = findViewById(R.id.registerButton);
        this.emailET = findViewById(R.id.emailEditText);
        this.passwordET = findViewById(R.id.passwordEditText);
    }

}