package com.example.practica_final;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.practica_final.activities.ProfileActivity;
import com.example.practica_final.users.User;

public class FriendHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private User user;
    private TextView tvNom;
    private ImageView profileImageView;

    private Activity activity;



    public FriendHolder(LayoutInflater inflater, ViewGroup parent, Activity activity) {
        super(inflater.inflate(R.layout.friendcontainer, parent, false));
        tvNom = (TextView) itemView.findViewById(R.id.nameFriend);
        profileImageView = (ImageView) itemView.findViewById(R.id.friendImage);

        this.activity = activity;
        itemView.setOnClickListener(this);
    }

    public void bind(User user) {
        this.user = user;
        tvNom.setText(user.getName());
        profileImageView.setVisibility(View.VISIBLE);
    }

    public void onClick(View view) {
        Toast.makeText(activity, user.getName() + " apretat", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(activity, ProfileActivity.class);
        intent.putExtra("UUID_USER", user.getID());


        activity.startActivity(intent);

    }
}
