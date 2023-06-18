package com.example.practica_final.wishList.Activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
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
import com.example.practica_final.R;
import com.example.practica_final.categories.entities.Category;
import com.example.practica_final.fragments.BottomMenuFragment;
import com.example.practica_final.wishList.Fragments.WishListAdapter;
import com.example.practica_final.wishList.WishList;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WishListsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FragmentManager fragmentManager;
    private WishListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wishlist_list_layout);
        setComponents();
    }

    public void setComponents () {
        recyclerView = findViewById(R.id.wishListList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fragmentManager = getSupportFragmentManager();

        Fragment fragment = fragmentManager.findFragmentById(R.id.bottom_menu);

        if (fragment == null) {
            fragment = new BottomMenuFragment(this, getIntent().getIntExtra("menu_state", 2));
            fragmentManager.beginTransaction().add(R.id.bottom_menu, fragment).commit();
        }
        getWishLists();

    }

    public void getFriends (ArrayList<WishList> wishLists) {

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://balandrau.salle.url.edu/i3/socialgift/api/v1/friends";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            ArrayList<Integer> ids = new ArrayList<>();
                            for (int i = 0; i < response.length(); i++) {
                                ids.add(response.getJSONObject(i).optInt("id"));
                            }
                            setWishLists(wishLists, ids);
                        } catch (JSONException ignored) {}
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle the error
                        System.out.println();
                    }
                }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + Authentication.getAuthentication()); // Replace YOUR_TOKEN_HERE with your actual token
                return headers;
            }
        };

        queue.add(jsonArrayRequest);
    }

    public void getWishLists () {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://balandrau.salle.url.edu/i3/socialgift/api/v1/wishlists";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            ArrayList<WishList> wishLists = new ArrayList<>();
                            for (int i = 0; i < response.length(); i++) {
                                wishLists.add(new WishList(response.getJSONObject(i)));
                            }
                            getFriends(wishLists);
                        } catch (JSONException ignored) {}
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle the error
                        System.out.println();
                    }
                }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + Authentication.getAuthentication()); // Replace YOUR_TOKEN_HERE with your actual token
                return headers;
            }
        };

        queue.add(jsonArrayRequest);
    }

    private ArrayList<WishList> filterWishLists (ArrayList<WishList> wishLists, ArrayList<Integer> ids) {
        ArrayList<WishList> ret = new ArrayList<>();
        for (int i = 0; i < wishLists.size(); i++) {
            for (int j = 0; j < ids.size(); j++) {
                if (wishLists.get(i).getUserId() == ids.get(j)) {
                    ret.add(wishLists.get(i));
                    break;
                }
            }
            if (wishLists.get(i).getUserId() == Authentication.getUserID()) {
                ret.add(wishLists.get(i));
                break;
            }
        }
        return ret;
    }

    public void setWishLists (ArrayList<WishList> wishLists, ArrayList<Integer> ids) {
        ArrayList<WishList> filteredList = filterWishLists(wishLists, ids);
        if (adapter == null) {
            adapter = new WishListAdapter(filteredList, this);
            recyclerView.setAdapter(adapter);
        } else {
            adapter.setWishLists(filteredList);
            adapter.notifyDataSetChanged();
        }

    }
}
