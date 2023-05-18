package com.example.practica_final.users;

import android.app.Activity;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.practica_final.R;
import com.example.practica_final.users.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class UserManager {
    private static String accessToken;
    private static User user;
    private String userEmail;
    private User[] friends;

    public UserManager(String userEmail) {
        this.userEmail = userEmail;
    }




    public void makeRequest(Activity activity) {

        JSONObject jsonObject = new JSONObject();


        RequestQueue queue = Volley.newRequestQueue(activity);
        String url = "https://balandrau.salle.url.edu/i3/socialgift/api/v1/users";
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println(response);
                User[] users = parseUsers(response);
                for (User usuari: users) {
                    if (usuari.getEmail().equals(userEmail)) {
                        user = usuari;
                    }
                }


                System.out.println("Register Completed");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(activity, R.string.incorrectCredentials, Toast.LENGTH_SHORT).show();
            }
        }) {
            public Map<String, String> getHeaders() {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","application/json");
                return params;
            }
        };
        queue.add(stringRequest);
    }

    private User[] parseUsers(JSONObject response) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.fromJson(String.valueOf(response), User[].class);
    }


}
