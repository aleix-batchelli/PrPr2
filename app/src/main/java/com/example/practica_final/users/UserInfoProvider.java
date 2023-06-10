package com.example.practica_final.users;

import android.app.Activity;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.practica_final.Authentication;
import com.example.practica_final.FriendAdapter;
import com.example.practica_final.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class UserInfoProvider {
    private static User[] foundUsers;
    private static int userID;
    private static RecyclerView recyclerView;
    private static FriendAdapter friendAdapter;


    public static int getUserID(String token) throws JSONException {
        String[] parts = token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();

        String header = new String(decoder.decode(parts[0]));
        String payload = new String(decoder.decode(parts[1]));
        System.out.println("header: " + header);
        System.out.println("payload: " + payload);


        JSONObject jsonObject = new JSONObject(payload);
        return jsonObject.getInt("id");

    }

    public static User[] parseUsers(JSONObject response) {
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
                params.put("Authorization","Bearer " + Authentication.getAuthentication());
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
                System.out.println("error response FFFFFFFFF");
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

    public static User[] getAllUsers(Activity activity, FriendAdapter friendAdapterAUX, RecyclerView recyclerViewAUX) {
        JSONObject jsonObject = new JSONObject();
        friendAdapter = friendAdapterAUX;
        recyclerView = recyclerViewAUX;

        RequestQueue queue = Volley.newRequestQueue(activity);
        System.out.println("getting all users!!" + Authentication.getAuthentication());
        String url = "https://balandrau.salle.url.edu/i3/socialgift/api/v1/users";
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println("onresponse in");
                //System.out.println(response);

                    User[] users = parseUsers(response);
                    foundUsers = Arrays.copyOf(users, users.length);
                    friendAdapter = new FriendAdapter(users, activity);
                    recyclerView.setAdapter(friendAdapter);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("ERRROR REAL BROTHER");
                error.printStackTrace();
                foundUsers = new User[0];
                Toast.makeText(activity, R.string.incorrectCredentials, Toast.LENGTH_SHORT).show();

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

        System.out.println("before return");
        return foundUsers;
    }


}
