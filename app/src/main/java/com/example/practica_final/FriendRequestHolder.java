package com.example.practica_final;

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
import com.example.practica_final.activities.LoginActivity;
import com.example.practica_final.activities.ProfileActivity;
import com.example.practica_final.activities.RegisterActivity;
import com.example.practica_final.activities.SingleFragmentActivity;
import com.example.practica_final.users.User;
import com.example.practica_final.users.UserInfoProvider;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FriendRequestHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private User user;
    private TextView tvNom;
    private ImageView profileImageView;
    private Button acceptButton;
    private Button declineButton;


    private Activity activity;



    public FriendRequestHolder(LayoutInflater inflater, ViewGroup parent, Activity activity) {
        super(inflater.inflate(R.layout.requestscontainer, parent, false));
        tvNom = (TextView) itemView.findViewById(R.id.nameFriend);
        profileImageView = (ImageView) itemView.findViewById(R.id.friendImage);
        acceptButton = (Button) itemView.findViewById(R.id.acceptButton);
        declineButton = (Button) itemView.findViewById(R.id.declineButton);

        acceptButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create new activity for register view
                acceptFriendRequest();
            }
        }));

        declineButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create new activity for register view
                declineFriendRequest();
            }
        }));

        this.activity = activity;
        itemView.setOnClickListener(this);
    }

    private void acceptFriendRequest() {
        System.out.println("auth: " + Authentication.getAuthentication());
            RequestQueue queue = Volley.newRequestQueue(activity);
            String url = "https://balandrau.salle.url.edu/i3/socialgift/api/v1/friends/" + user.getID();

            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("email", "marc@marc.com");
                jsonObject.put("password", "123456789");

            } catch (JSONException ignored) {

            }
            JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.PUT, url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    System.out.println("Accepted request");
                    System.out.println(response);


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("ERROR RESPONSE: ");
                    error.printStackTrace();
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

    private void declineFriendRequest() {
        System.out.println("auth: " + Authentication.getAuthentication());
        RequestQueue queue = Volley.newRequestQueue(activity);
        String url = "https://balandrau.salle.url.edu/i3/socialgift/api/v1/friends/" + user.getID();

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("email", "marc@marc.com");
            jsonObject.put("password", "123456789");

        } catch (JSONException ignored) {

        }
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.DELETE, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println("Declined request");
                System.out.println(response);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("ERROR RESPONSE: ");
                error.printStackTrace();
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

    public void onClick(View view) {
        Toast.makeText(activity, user.getName() + " apretat", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(activity, ProfileActivity.class);
        intent.putExtra("UUID_USER", user.getID());


        activity.startActivity(intent);

    }


}
