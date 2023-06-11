package com.example.practica_final.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practica_final.R;

public class MessagesActivity extends AppCompatActivity {
    private ImageView friendIV;
    private TextView friendNameTV;
    private RecyclerView messagesRV;
    private EditText messageET;
    private Button sendButton;
    private int friendID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversation_layout);
        setComponents();
        friendID = getIntent().getIntExtra("UUID_USER", 0);
        getMessatges();

    }

    private void getMessatges() {

    }

    private void setComponents() {
        friendIV = findViewById(R.id.friendProfile);
        friendNameTV = findViewById(R.id.friendName);
        messagesRV = findViewById(R.id.messagesRV);
        messageET = findViewById(R.id.messageEditText);
        sendButton = findViewById(R.id.sendButton);
    }
}
