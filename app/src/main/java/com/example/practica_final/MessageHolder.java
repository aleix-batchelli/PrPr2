package com.example.practica_final;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


public class MessageHolder extends RecyclerView.ViewHolder {

    private Message message;
    private TextView tvContent;
    private Activity activity;



    public MessageHolder(LayoutInflater inflater, ViewGroup parent, Activity activity) {
        super(inflater.inflate(R.layout.message_container, parent, false));
        tvContent = (TextView) itemView.findViewById(R.id.content);
        this.activity = activity;


    }

    public void bind(Message message) {
        this.message = message;
        tvContent.setText(message.getContent());
        tvContent.setTypeface(Typeface.DEFAULT_BOLD);

        if (message.getMessageID() == Authentication.getUserID()) {
            tvContent.setBackgroundResource(R.color.orange);
            tvContent.setGravity(Gravity.END);
            return;
        }
        tvContent.setBackgroundResource(R.color.white);
        tvContent.setGravity(Gravity.START);
    }
}

