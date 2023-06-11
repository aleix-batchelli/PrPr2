package com.example.practica_final;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.practica_final.users.User;

import org.json.JSONArray;

import java.util.ArrayList;

public class FriendChatAdapter extends RecyclerView.Adapter<FriendChatHolder> {

    private User[] users;
    private Activity activity;
    private JSONArray usersJsonArray;

    public FriendChatAdapter(User[] users, Activity activity) {
        this.users = users;
        this.activity = activity;
    }

    @Override
    public FriendChatHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(activity);

        return new FriendChatHolder(layoutInflater, parent, activity);

    }

    @Override
    public int getItemCount() {
        return users.length;
    }

    @Override
    public void onBindViewHolder(FriendChatHolder holder, int position) {
        User user = users[position];
        holder.bind(user);
    }

    public void setUsers(User[] users) {
        this.users = users;
    }
}
