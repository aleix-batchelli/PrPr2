package com.example.practica_final.fragments;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.practica_final.Authentication;
import com.example.practica_final.R;
import com.example.practica_final.activities.LoginActivity;
import com.example.practica_final.activities.ProfileActivity;
import com.example.practica_final.activities.SingleFragmentActivity;
import com.example.practica_final.users.User;
import com.example.practica_final.users.UserInfoProvider;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AddFriendHolder extends RecyclerView.ViewHolder {

    private User user;
    private TextView tvNom;
    private ImageView profileImageView;
    private Button sendRequestButton;

    private Activity activity;


    public AddFriendHolder(LayoutInflater inflater, ViewGroup parent, Activity activity) {
        super(inflater.inflate(R.layout.send_friend_request_container, parent, false));
        tvNom = (TextView) itemView.findViewById(R.id.nameFriend);
        profileImageView = (ImageView) itemView.findViewById(R.id.friendImage);
        sendRequestButton = (Button) itemView.findViewById(R.id.sendRequestButton);

        sendRequestButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeRequest();
            }
        }));


        this.activity = activity;
    }

    private void makeRequest() {
        RequestQueue queue = Volley.newRequestQueue(activity);
        String url = "https://balandrau.salle.url.edu/i3/socialgift/api/v1/friends/" + user.getID();

        JSONObject jsonObject = new JSONObject();
        try {
            //jsonObject.put("email", emailET.getText().toString());
            //jsonObject.put("email", "marc@marc.com");
            //jsonObject.put("password", "123456789");
            jsonObject.put("id", user.getID());

        } catch (JSONException e) {

        }
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println(response);
                String accessToken = null;
                accessToken = Authentication.getAuthentication();
                System.out.println("accesstoken: " + accessToken);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
               // Toast.makeText(activity, R.string.incorrectCredentials, Toast.LENGTH_SHORT).show;
            }
        }) {
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","application/json");
                params.put("Authorization", "Bearer " + Authentication.getAuthentication());
                return params;
            }
        };
        queue.add(stringRequest);
    }

    public void bind(User user) {
        this.user = user;
        tvNom.setText(user.getName());
        profileImageView.setVisibility(View.VISIBLE);
    }



}

