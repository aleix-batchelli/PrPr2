package com.example.practica_final.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.practica_final.Authentication;
import com.example.practica_final.FriendAdapter;
import com.example.practica_final.R;
import com.example.practica_final.users.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FriendRequestFragment extends Fragment {
    private EditText searchUserET;
    private Button searchUserButton;
    private RecyclerView listUsersRV;
    private View v;
    private Activity activity;

    private FriendAdapter friendAdapter;
    private User[] foundUsers;

    private JSONArray users;
    public FriendRequestFragment(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.v = inflater.inflate(R.layout.list_friend_requests, container, false);

        System.out.println("ACCESS: " + Authentication.getAuthentication());

        searchUserET = v.findViewById(R.id.searchUser);
        searchUserButton = v.findViewById(R.id.searchButton);
        listUsersRV = (RecyclerView) v.findViewById(R.id.searchRequestsRV);
        listUsersRV.setLayoutManager(new LinearLayoutManager(getActivity()));

        User[] aux = new User[1];
        aux[0] = new User(1, "", "", "", "");

        friendAdapter = new FriendAdapter(aux, activity); // Inicializar el adaptador con una lista vacía
        listUsersRV.setAdapter(friendAdapter); // Establecer el adaptador en el RecyclerView

        updateUI();

        return v;
    }

    private void updateUI() {
        System.out.println("update Ui");

        getAllFriendRequests(getActivity());

    }

    private void getAllFriendRequests(Activity activity) {
        JSONArray jsonObject = new JSONArray();

        RequestQueue queue = Volley.newRequestQueue(activity);
        String url = "https://balandrau.salle.url.edu/i3/socialgift/api/v1/friends/requests";
        JsonArrayRequest stringRequest = new JsonArrayRequest(Request.Method.GET, url, jsonObject, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                System.out.println("AAAAA RESPONSE: " + response);
                try {
                    users = new JSONArray(response.toString());
                    User[] allUsers = convertJSONArrayToUsers(users);

                    System.out.println(Arrays.toString(allUsers));
                    friendAdapter.setUsers(allUsers);
                    friendAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("ERROR RESPONSE");
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
    }

    private User[] convertJSONArrayToUsers(JSONArray jsonArray) {
        int length = jsonArray.length();
        User[] users = new User[length];
        try {
            for (int i = 0; i < length; i++) {
                JSONObject userObject = jsonArray.getJSONObject(i);

                int ID = userObject.getInt("id");
                String username = userObject.getString("name");
                String lastname = userObject.getString("last_name");
                String email = userObject.getString("email");
                String image = userObject.getString("image");
                User user = new User(ID, username, lastname, email, image);
                users[i] = user;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return users;
    }

}
