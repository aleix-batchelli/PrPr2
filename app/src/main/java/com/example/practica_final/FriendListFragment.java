package com.example.practica_final;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practica_final.users.User;
import com.example.practica_final.users.UserInfoProvider;
import com.example.practica_final.users.UserManager;

import java.util.ArrayList;

public class FriendListFragment extends Fragment {

    RecyclerView friendsRecyclerView;
    private FriendAdapter friendAdapter;
    private User[] users;
    private int userID = 10;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String userEmail = ""; // get from intent
        userID = UserInfoProvider.getUserID(getActivity(), userEmail);
        users = UserInfoProvider.getFriends(getActivity(), userID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.list_friends, container, false);
        friendsRecyclerView = (RecyclerView) v.findViewById(R.id.friends_recycler_view);
        friendsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        updateUI();

        return v;
    }

    private void updateUI() {
        if (users.length == 0) {
            users = UserInfoProvider.getFriends(getActivity(), userID);
        }

        if (friendAdapter == null) {
            friendAdapter = new FriendAdapter(users, getActivity());
            friendsRecyclerView.setAdapter(friendAdapter);
        } else {
            friendAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

}
