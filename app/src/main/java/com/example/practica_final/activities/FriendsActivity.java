package com.example.practica_final.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.practica_final.ProfileFragment;
import com.example.practica_final.R;
import com.example.practica_final.fragments.DeleteFriendsFragment;
import com.example.practica_final.fragments.FriendManagementFragment;
import com.example.practica_final.fragments.FriendRequestFragment;
import com.example.practica_final.fragments.HomeFragment;
import com.example.practica_final.fragments.ListFriendsFragment;
import com.example.practica_final.fragments.SearchFriendFragment;

public class FriendsActivity extends AppCompatActivity {

    protected ImageButton profileButton;
    protected ImageButton giftButton;
    protected ImageButton homeButton;
    protected ImageButton messagesButton;
    protected ImageButton friendsButton;
    private Button listFriendsButton;
    private Button friendRequestsButton;
    private Button searchFriendsButton;
    private Button deleteFriendsButton;

    public FragmentManager fragmentManager;

    private static final int ID_FRIEND_LIST_ACTIVITY = 10;
    private static final int ID_FRIEND_REQUESTS_ACTIVITY = 11;
    private static final int ID_SEARCH_FRIENDS_ACTIVITY = 12;
    private static final int ID_DELETE_FRIENDS_ACTIVITY = 13;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feed);

        setMenuComponents();
        setComponents();

        int selectedFragment = getIntent().getIntExtra("fragment", ID_SEARCH_FRIENDS_ACTIVITY);
        System.out.println("im on friends activity");
        fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.feedLayout);

        if (fragment == null) {
            fragment = getSelectedFragment(selectedFragment);
            fragmentManager.beginTransaction().add(R.id.feedLayout, fragment).commit();
        }


    }

    private Fragment getSelectedFragment(int selectedFragment) {
        System.out.println("seletedFragment: " + selectedFragment);
        switch (selectedFragment) {
            case ID_FRIEND_LIST_ACTIVITY: {
               return new ListFriendsFragment(this);
            }
            case ID_FRIEND_REQUESTS_ACTIVITY: {
                return new FriendRequestFragment(this);

            }
            case ID_SEARCH_FRIENDS_ACTIVITY: {
                return new SearchFriendFragment(this);
            }
            case ID_DELETE_FRIENDS_ACTIVITY: {
               return new DeleteFriendsFragment(this);
            }
        }
        return new FriendManagementFragment(this);
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
                Intent intent = new Intent(FriendsActivity.this, SingleFragmentActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);


                Fragment fragment = new HomeFragment(FriendsActivity.this);
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
                Fragment fragment = new FriendManagementFragment(FriendsActivity.this);
                fragmentManager.beginTransaction().add(R.id.feedLayout, fragment).commit();



            }
        }));
    }

    private void setProfileButtonSettings() {
        profileButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create new activity for register view
                System.out.println("BOTO APRETAT");

                Fragment fragment = new ProfileFragment(FriendsActivity.this);
                fragmentManager.beginTransaction().add(R.id.feedLayout, fragment).commit();
            }
        }));
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(FriendsActivity.this, SingleFragmentActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }




    private void setComponents() {
        this.listFriendsButton = findViewById(R.id.listFriends);
        this.friendRequestsButton = findViewById(R.id.friendRequests);
        this.searchFriendsButton = findViewById(R.id.searchNewFriend);
        this.deleteFriendsButton = findViewById(R.id.deleteFriends);
    }


}
