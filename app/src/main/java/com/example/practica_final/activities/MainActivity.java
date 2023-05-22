package com.example.practica_final.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.practica_final.R;

public class MainActivity extends AppCompatActivity {

    private static final int ID_MAIN_ACTIVITY = 0;
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
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
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
        if (requestCode == ID_MAIN_ACTIVITY) {
            return;
        }
    }

}