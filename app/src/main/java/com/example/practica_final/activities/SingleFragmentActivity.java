package com.example.practica_final.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.practica_final.ProfileFragment;
import com.example.practica_final.R;
import com.example.practica_final.fragments.FeedFragment;
import com.example.practica_final.fragments.FriendManagementFragment;

public class SingleFragmentActivity extends AppCompatActivity {


    private ImageButton profileButton;
    private ImageButton giftButton;
    private ImageButton homeButton;
    private ImageButton messagesButton;
    private ImageButton friendsButton;
    public FragmentManager fragmentManager;

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

        fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.feedLayout);

        if (fragment == null) {
            fragment = new FeedFragment();
            fragmentManager.beginTransaction().add(R.id.feedLayout, fragment).commit();
        }

    }

    private void setMenuComponents() {
        this.profileButton = findViewById(R.id.profile);
        this.giftButton = findViewById(R.id.gifts);
        this.homeButton = findViewById(R.id.home);
        this.messagesButton = findViewById(R.id.mail);
        this.friendsButton = findViewById(R.id.friends);
    }
    private void setMessagesButton() {
        messagesButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create new activity for register view


            }
        }));
    }

    private void setHomeButton() {
        homeButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create new activity for register view
                Fragment fragment = new FeedFragment();
                fragmentManager.beginTransaction().add(R.id.feedLayout, fragment).commit();

            }
        }));
    }

    private void setGiftButton() {
        giftButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Fragment fragment = new FeedFragment();
                //fragmentManager.beginTransaction().add(R.id.feedLayout, fragment).commit();
            }
        }));
    }


    private void setFriendsButtonSettings() {
        friendsButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create new activity for register view
                System.out.println("BOTO FRIENDS APRETAT");
                Fragment fragment = new FriendManagementFragment(SingleFragmentActivity.this);
                fragmentManager.beginTransaction().add(R.id.feedLayout, fragment).commit();

                //Intent intent = new Intent(SingleFragmentActivity.this, FriendsActivity.class);
                //intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                //startActivity(intent);

            }
        }));
    }

    private void setProfileButtonSettings() {
        profileButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create new activity for register view
                System.out.println("BOTO APRETAT");

                Fragment fragment = new ProfileFragment();
                fragmentManager.beginTransaction().add(R.id.feedLayout, fragment).commit();
            }
        }));
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = new FeedFragment();
        fragmentManager.beginTransaction().add(R.id.feedLayout, fragment).commit();
    }
}
