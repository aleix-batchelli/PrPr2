package com.example.practica_final.users;

import android.app.Activity;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.practica_final.Authentication;
import com.example.practica_final.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class UserInfoProvider {
    private static User[] foundUsers;
    private static int userID;


    public static int getUserID(Activity activity, String userEmail) {
        userID = 0;

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
                        userID = usuari.getID();
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
                params.put("Authorization", "Bearer " + Authentication.getInstance());
                return params;
            }
        };
        queue.add(stringRequest);
        return userID;
    }

    private static User[] parseUsers(JSONObject response) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.fromJson(String.valueOf(response), User[].class);
    }

    public static User[] getFriends(Activity activity, int userID) {
        foundUsers = null;

        JSONObject jsonObject = new JSONObject();

        RequestQueue queue = Volley.newRequestQueue(activity);
        String url = "https://balandrau.salle.url.edu/i3/socialgift/api/v1/users/" + userID + "/friends";
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println(response);
                User[] users = parseUsers(response);

                foundUsers = Arrays.copyOf(users, users.length);
                System.out.println("Register Completed");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                foundUsers = null;
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

        return foundUsers;
    }

    public static User[] getFriendRequests(Activity activity) {
        foundUsers = null;

        JSONObject jsonObject = new JSONObject();

        RequestQueue queue = Volley.newRequestQueue(activity);
        String url = "https://balandrau.salle.url.edu/i3/socialgift/api/v1/users/friends/requests";
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println(response);
                User[] users = parseUsers(response);

                foundUsers = Arrays.copyOf(users, users.length);
                System.out.println("Friend request list Completed");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                foundUsers = null;
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

        return foundUsers;
    }

    public static User[] getAllUsers(Activity activity) {
        foundUsers = null;

        JSONObject jsonObject = new JSONObject();

        RequestQueue queue = Volley.newRequestQueue(activity);
        String url = "https://balandrau.salle.url.edu/i3/socialgift/api/v1/users";
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println(response);
                User[] users = parseUsers(response);

                foundUsers = Arrays.copyOf(users, users.length);
                System.out.println("All users loaded correctly");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                foundUsers = null;
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

        return foundUsers;
    }

}
