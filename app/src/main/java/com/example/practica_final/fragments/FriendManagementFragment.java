package com.example.practica_final.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.practica_final.R;
import com.example.practica_final.activities.DeleteFriendsActivity;
import com.example.practica_final.activities.FriendRequestsActivity;
import com.example.practica_final.activities.FriendsActivity;
import com.example.practica_final.activities.SearchFriendsActivity;

public class FriendManagementFragment extends Fragment {
    private Button listFriendsButton;
    private Button friendRequestsButton;
    private Button searchFriendsButton;
    private Button deleteFriendsButton;
    private static final int ID_FRIEND_LIST_ACTIVITY = 10;
    private static final int ID_FRIEND_REQUESTS_ACTIVITY = 11;
    private static final int ID_SEARCH_FRIENDS_ACTIVITY = 12;
    private static final int ID_DELETE_FRIENDS_ACTIVITY = 13;

    private View v;
    private Activity activity;

    public FriendManagementFragment(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.manage_friends, container, false);
        setComponents();
        listFriendsActionListener();
        searchFriendsActionListener();
        deleteFriendsActionListener();
        friendRequestsActionListener();


        return v;
    }

    private void deleteFriendsActionListener() {
        deleteFriendsButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create new activity for register view
                System.out.println("DELETE APRETAT");
                Intent intent = new Intent(activity, FriendsActivity.class);
                intent.putExtra("fragment", ID_DELETE_FRIENDS_ACTIVITY);
                startActivityForResult(intent,ID_DELETE_FRIENDS_ACTIVITY);
            }
        }));
    }

    private void searchFriendsActionListener() {
        searchFriendsButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create new activity for register view
                System.out.println("SEARCH FRIENDS APRETAT");
                Intent intent = new Intent(activity, FriendsActivity.class);
                intent.putExtra("fragment", ID_SEARCH_FRIENDS_ACTIVITY);
                startActivity(intent);
            }
        }));

    }

    private void friendRequestsActionListener() {
        friendRequestsButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create new activity for register view
                System.out.println("FRIENDS REQUESTS APRETAT");
                Intent intent = new Intent(activity, FriendsActivity.class);
                intent.putExtra("fragment", ID_FRIEND_REQUESTS_ACTIVITY);
                startActivityForResult(intent,ID_FRIEND_REQUESTS_ACTIVITY);
            }
        }));
    }

    private void listFriendsActionListener() {
        listFriendsButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create new activity for register view
               Intent intent = new Intent(activity, FriendsActivity.class);
               intent.putExtra("fragment", ID_FRIEND_LIST_ACTIVITY);
               startActivityForResult(intent,ID_FRIEND_LIST_ACTIVITY);
            }
        }));
    }



    private void setComponents() {
        this.listFriendsButton = v.findViewById(R.id.listFriends);
        this.friendRequestsButton = v.findViewById(R.id.friendRequests);
        this.searchFriendsButton = v.findViewById(R.id.searchNewFriend);
        this.deleteFriendsButton = v.findViewById(R.id.deleteFriends);
    }
}
