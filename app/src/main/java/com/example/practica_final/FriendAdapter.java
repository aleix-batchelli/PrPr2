package com.example.practica_final;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.practica_final.users.User;

import java.util.ArrayList;

public class FriendAdapter extends RecyclerView.Adapter<FriendHolder> {

    private User[] users;
    private Activity activity;

    public FriendAdapter(User[] users, Activity activity) {
        this.users = users;
        this.activity = activity;
    }

    @Override
    public FriendHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        return new FriendHolder(layoutInflater, parent, activity);
    }

    @Override
    public int getItemCount() {
        return users.length;
    }

    @Override
    public void onBindViewHolder(FriendHolder holder, int position) {
        User user = users[position];
        holder.bind(user);
    }
}
