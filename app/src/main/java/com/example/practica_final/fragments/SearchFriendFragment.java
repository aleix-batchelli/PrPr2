package com.example.practica_final.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practica_final.FriendAdapter;
import com.example.practica_final.R;
import com.example.practica_final.users.User;
import com.example.practica_final.users.UserInfoProvider;

import java.util.List;

public class SearchFriendFragment extends Fragment {
    private EditText searchUserET;
    private Button searchUserButton;
    private RecyclerView listUsersRV;
    private View v;
    private Activity activity;

    private FriendAdapter friendAdapter;

    public SearchFriendFragment(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);





    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.v = inflater.inflate(R.layout.list_users, container, false);

        searchUserET = v.findViewById(R.id.searchUser);
        searchUserButton = v.findViewById(R.id.searchUserButton);
        listUsersRV = v.findViewById(R.id.searchUserRV);

        setComponents();
        updateUI();

        return v;
    }

    private void setComponents() {
        this.searchUserET = v.findViewById(R.id.listFriends);
        this.searchUserButton = v.findViewById(R.id.friendRequests);
        this.listUsersRV = v.findViewById(R.id.searchNewFriend);
    }

    private void updateUI() {
        User[] users = UserInfoProvider.getAllUsers(getActivity());

        if (friendAdapter == null) {
            friendAdapter = new FriendAdapter(users, getActivity());
            listUsersRV.setAdapter(friendAdapter);
        }// else {
         //   friendAdapter.notifyDataSetChanged();
        //}
    }
}
