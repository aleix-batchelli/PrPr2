package com.example.practica_final;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.practica_final.users.UserManager;

public class FeedActivity extends AppCompatActivity {

    private ImageButton profileButton;
    private ImageButton giftButton;
    private ImageButton homeButton;
    private ImageButton messagesButton;
    private ImageButton friendsButton;
    private final int ID_SETTINGS_ACTIVITY = 4;

    private UserManager userManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feed);
        setComponents();
        setProfileButtonSettings();

    }

    private void setProfileButtonSettings() {
        profileButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create new activity for register view
                Intent intent = new Intent(FeedActivity.this,SettingsActivity.class);
                startActivityForResult(intent,ID_SETTINGS_ACTIVITY);
            }
        }));
    }



    private void setComponents() {
        this.profileButton = findViewById(R.id.profile);
        this.giftButton = findViewById(R.id.gifts);
        this.homeButton = findViewById(R.id.home);
        this.messagesButton = findViewById(R.id.mail);
        this.friendsButton = findViewById(R.id.friends);
    }
    /*
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode!= Activity.RESULT_OK){
            return;
        }

        if(requestCode==ID_SETTINGS_ACTIVITY){
            return;
        }
    }*/
}