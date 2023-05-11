package com.example.practica_final;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private Button loginButton;
    private Button registerButton;
    private EditText emailET;
    private EditText passwordET;
    private final int ID_REGISTER_ACTIVITY = 2;
    private final int ID_FEED_ACTIVITY = 3;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        setComponents();

        registerButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create new activity for register view
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivityForResult(intent,ID_REGISTER_ACTIVITY);
            }
        }));

        loginButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get data from email edit text and password edit text
                String email = emailET.getText().toString();
                String password = passwordET.getText().toString();

                // check if the credentials got are correct
                boolean correctLogin = checkCredentials(email, password);
                // create new activity for feed view
                if (correctLogin) {
                    Intent intent = new Intent(LoginActivity.this,FeedActivity.class);
                    startActivityForResult(intent,ID_FEED_ACTIVITY);
                } else {
                    Toast.makeText(LoginActivity.this, R.string.incorrectCredentials, Toast.LENGTH_SHORT);
                }

            }
        }));

    }

    private boolean checkCredentials(String email, String password) {
        // TODO Create function that checks credentials provided to the API


        return true;
    }

    private void setComponents() {
        this.loginButton = findViewById(R.id.loginButton);
        this.registerButton = findViewById(R.id.registerButton);
        this.emailET = findViewById(R.id.emailEditText);
        this.passwordET = findViewById(R.id.passwordEditText);
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode!= Activity.RESULT_OK){
            return;
        }

        if(requestCode==ID_REGISTER_ACTIVITY){
            return;
        }
    }
}
