package com.example.practica_final;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.practica_final.users.User;

import org.json.JSONArray;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<MessageHolder> {

    private Message[] messages;
    private Activity activity;

    public MessageAdapter(Message[] messages, Activity activity) {
        this.messages = messages;
        this.activity = activity;
    }

    @Override
    public MessageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(activity);

        return new MessageHolder(layoutInflater, parent, activity);

    }

    @Override
    public int getItemCount() {
        return messages.length;
    }

    @Override
    public void onBindViewHolder(MessageHolder holder, int position) {
        Message message = messages[position];
        holder.bind(message);
    }

    public void setMessages(Message[] messages) {
        this.messages = messages;
    }
}

