package com.example.practica_final;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageView logo;

    private final int ID_LOGIN_ACTIVITY = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logo = (ImageView) findViewById(R.id.initialLogo);
        logo.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create new activity for register view
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivityForResult(intent,ID_LOGIN_ACTIVITY);
            }
        }));


    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode!= Activity.RESULT_OK){
            return;
        }

        if(requestCode==ID_LOGIN_ACTIVITY){
            return;
        }
    }

}