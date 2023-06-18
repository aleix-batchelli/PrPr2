package com.example.practica_final.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.practica_final.Authentication;
import com.example.practica_final.Message;
import com.example.practica_final.MessageAdapter;
import com.example.practica_final.R;
import com.example.practica_final.fragments.FriendManagementFragment;
import com.example.practica_final.users.UserInfoProvider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MessagesActivity extends AppCompatActivity {
    private ImageView friendIV;
    private TextView friendNameTV;
    private RecyclerView messagesRV;
    private EditText messageET;
    private Button sendButton;
    private int friendID;
    private String friendName;

    private JSONArray messagesArray;

    private MessageAdapter messageAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversation_layout);
        setComponents();
        messagesRV.setLayoutManager(new LinearLayoutManager(this));

        friendID = getIntent().getIntExtra("UUID_USER", 0);
        friendName = getIntent().getStringExtra("FRIEND_NAME");

        friendNameTV.setText(friendName);
        messageAdapter = new MessageAdapter(new Message[0], MessagesActivity.this);
        messagesRV.setAdapter(messageAdapter);

        getMessages();

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!messageET.getText().toString().equals("")) {
                    sendMessage(messageET.getText().toString());
                }
            }
        });
    }




    private void setComponents() {
        friendIV = findViewById(R.id.friendProfile);
        friendNameTV = findViewById(R.id.friendName);
        messagesRV = findViewById(R.id.messagesRV);
        messageET = findViewById(R.id.messageEditText);
        sendButton = findViewById(R.id.sendButton);


    }

    private void getMessages() {
        JSONArray jsonObject = new JSONArray();

        RequestQueue queue = Volley.newRequestQueue(MessagesActivity.this);
        String url = "https://balandrau.salle.url.edu/i3/socialgift/api/v1/messages/" + friendID;
        JsonArrayRequest stringRequest = new JsonArrayRequest(Request.Method.GET, url, jsonObject, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                System.out.println("RESPONSE: " + response.toString());
                try {
                    messagesArray = new JSONArray(response.toString());
                    Message[] messages = convertJSONArrayToMessages(messagesArray);


                    messageAdapter.setMessages(messages);
                    messageAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("ERROR RESPONSE");
                error.printStackTrace();
            }
        }) {
            public Map<String, String> getHeaders() {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","application/json");
                params.put("Authorization", "Bearer " + Authentication.getAuthentication());
                return params;
            }
        };
        queue.add(stringRequest);
    }

    private Message[] convertJSONArrayToMessages(JSONArray jsonArray) {
        int length = jsonArray.length();
        Message[] messages = new Message[length];
        try {
            for (int i = 0; i < length; i++) {
                JSONObject messageObject = jsonArray.getJSONObject(i);

                int ID = messageObject.getInt("id");
                String content = messageObject.getString("content");
                int user_id_send = messageObject.getInt("user_id_send");
                int user_id_received = messageObject.getInt("user_id_recived");
                String timestamp = messageObject.getString("timeStamp");

                Message message = new Message(ID, content, user_id_send, user_id_received, timestamp);
                messages[i] = message;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return messages;
    }
}
