package com.example.practica_final.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.practica_final.R;

public class FriendManagementActivity extends AppCompatActivity {
    private Button listFriendsButton;
    private Button friendRequestsButton;
    private Button searchFriendsButton;
    private Button deleteFriendsButton;
    private static final int ID_FRIEND_LIST_ACTIVITY = 10;
    private static final int ID_FRIEND_REQUESTS_ACTIVITY = 11;
    private static final int ID_SEARCH_FRIENDS_ACTIVITY = 12;
    private static final int ID_DELETE_FRIENDS_ACTIVITY = 13;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_friends);
        setComponents();
        listFriendsActionListener();
        friendRequestsActionListener();
        searchFriendsActionListener();
        deleteFriendsActionListener();

    }

    private void deleteFriendsActionListener() {
        deleteFriendsButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create new activity for register view
                Intent intent = new Intent(FriendManagementActivity.this, FriendsActivity.class);
                startActivityForResult(intent,ID_FRIEND_LIST_ACTIVITY);
            }
        }));
    }

    private void searchFriendsActionListener() {
        searchFriendsButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create new activity for register view
                Intent intent = new Intent(FriendManagementActivity.this, SearchFriendsActivity.class);
                startActivityForResult(intent,ID_SEARCH_FRIENDS_ACTIVITY);
            }
        }));
    }

    private void friendRequestsActionListener() {
        friendRequestsButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create new activity for register view
                Intent intent = new Intent(FriendManagementActivity.this, FriendRequestsActivity.class);
                startActivityForResult(intent,ID_FRIEND_REQUESTS_ACTIVITY);
            }
        }));
    }

    private void listFriendsActionListener() {
        listFriendsButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create new activity for register view
                Intent intent = new Intent(FriendManagementActivity.this, DeleteFriendsActivity.class);
                startActivityForResult(intent,ID_DELETE_FRIENDS_ACTIVITY);
            }
        }));
    }



    private void setComponents() {
        this.listFriendsButton = findViewById(R.id.listFriends);
        this.friendRequestsButton = findViewById(R.id.friendRequests);
        this.searchFriendsButton = findViewById(R.id.searchNewFriend);
        this.deleteFriendsButton = findViewById(R.id.deleteFriends);
    }
}
