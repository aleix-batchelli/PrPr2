package com.example.practica_final.fragments;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;


import androidx.recyclerview.widget.RecyclerView;


import com.example.practica_final.users.User;

import org.json.JSONArray;

public class AddFriendAdapter extends RecyclerView.Adapter<AddFriendHolder> {

        private User[] users;
        private Activity activity;

        public AddFriendAdapter(User[] users, Activity activity) {
            this.users = users;
            this.activity = activity;
        }

        @Override
        public AddFriendHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(activity);

            return new AddFriendHolder(layoutInflater, parent, activity);

        }

        @Override
        public int getItemCount() {
            return users.length;
        }

        @Override
        public void onBindViewHolder(AddFriendHolder holder, int position) {
            User user = users[position];
            holder.bind(user);
        }

        public void setUsers(User[] users) {
            this.users = users;
        }

}
