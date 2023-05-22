package com.example.practica_final.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.practica_final.R;

public class CustomActivity extends AppCompatActivity {
    private ImageButton profileButton;
    private ImageButton giftButton;
    private ImageButton homeButton;
    private ImageButton messagesButton;
    private ImageButton friendsButton;
    private final int ID_SETTINGS_ACTIVITY = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feed);
        setMenuComponents();
        setProfileButtonSettings();
        setFriendsButtonSettings();
        setGiftButton();
        setHomeButton();
        setMessagesButton();
    }

    public void setMessagesButton() {
        messagesButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create new activity for register view
                Intent intent = new Intent(CustomActivity.this, MessagesActivity.class);
            }
        }));
    }

    public void setHomeButton() {
        homeButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create new activity for register view
                Intent intent = new Intent(CustomActivity.this, FeedActivity.class);
            }
        }));
    }

    public void setGiftButton() {
        giftButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create new activity for register view
                Intent intent = new Intent(CustomActivity.this, GiftManagementActivity.class);
            }
        }));
    }


    private void setFriendsButtonSettings() {
        friendsButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create new activity for register view
                Intent intent = new Intent(CustomActivity.this, FriendManagementActivity.class);
            }
        }));
    }

    private void setProfileButtonSettings() {
        profileButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create new activity for register view
                Intent intent = new Intent(CustomActivity.this, SettingsActivity.class);
            }
        }));
    }


    private void setMenuComponents() {
        this.profileButton = findViewById(R.id.profile);
        this.giftButton = findViewById(R.id.gifts);
        this.homeButton = findViewById(R.id.home);
        this.messagesButton = findViewById(R.id.mail);
        this.friendsButton = findViewById(R.id.friends);
    }
}
