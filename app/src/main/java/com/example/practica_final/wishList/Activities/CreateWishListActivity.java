package com.example.practica_final.wishList.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.practica_final.Authentication;
import com.example.practica_final.R;
import com.example.practica_final.activities.SingleFragmentActivity;
import com.example.practica_final.fragments.BottomMenuFragment;
import com.example.practica_final.gifts.Fragments.AddGiftFragment;
import com.example.practica_final.gifts.Fragments.GiftAdapter;
import com.example.practica_final.gifts.Gift;
import com.example.practica_final.products.Adapter.ProductAdapter;
import com.example.practica_final.products.Product;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class CreateWishListActivity extends AppCompatActivity {

    private TextView header;

    private Button delete;

    private TextInputLayout name;
    private TextInputLayout description;
    private RecyclerView recyclerView;
    private FragmentManager fragmentManager;
    private Fragment fragment;
    private Fragment bottomMenu;
    private Hashtable<Integer, Gift> gifts;

    private Button add;

    private Button create;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_wishlist_layout);
        setComponents();
        if (getIntent().getStringExtra("edit") != null) {
            if (getIntent().getStringExtra("edit").equals("true")) {
                editSetListeners();
            }
            else {
                setListeners();
            }
        }
        else  {
            setListeners();
        }
    }

    public void editSetListeners () {
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.fragment_container).setVisibility(View.VISIBLE);
                add.setVisibility(View.INVISIBLE);
                create.setVisibility(View.INVISIBLE);
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               editWishList();
               Intent intent = new Intent(getApplicationContext(), SingleFragmentActivity.class);
               startActivity(intent);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteWishList();
                Intent intent = new Intent(getApplicationContext(), SingleFragmentActivity.class);
                startActivity(intent);
            }
        });
    }

    public void deleteWishList () {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "https://balandrau.salle.url.edu/i3/socialgift/api/v1/wishlists/" + getIntent().getIntExtra("id", 0);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.DELETE, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(getApplicationContext(), "WishList edited", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "WishList not edited", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + Authentication.getAuthentication());
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };


        requestQueue.add(jsonObjectRequest);
    }

    public void editWishList () {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "https://balandrau.salle.url.edu/i3/socialgift/api/v1/wishlists" + getIntent().getIntExtra("id", 0);
        JSONObject requestBody = new JSONObject();
        try {
            // Add parameters to the request body
            requestBody.put("id", getIntent().getStringExtra("id"));
            requestBody.put("name", name.getEditText().getText());
            requestBody.put("description", description.getEditText().getText());
            requestBody.put("end_date", getIntent().getStringExtra("end_date"));
            requestBody.put("creation_date", getIntent().getStringExtra("creation_date"));
            ArrayList<Gift> gifts1 = new ArrayList<>(gifts.values());
            JSONArray jsonArray = new JSONArray();
            for (int i = 0; i < gifts1.size(); i++) {
                jsonArray.put(gifts1.get(i).toJSONObject());
            }
            requestBody.put("gifts", jsonArray);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, requestBody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(getApplicationContext(), "WishList edited", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "WishList not edited", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + Authentication.getAuthentication());
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };


        requestQueue.add(jsonObjectRequest);
    }

    public void createWishList() {

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "https://balandrau.salle.url.edu/i3/socialgift/api/v1/wishlists";
        JSONObject requestBody = new JSONObject();
        try {
            // Add parameters to the request body
            requestBody.put("name", name.getEditText().getText());
            requestBody.put("description", description.getEditText().getText());
            requestBody.put("end_date", LocalDate.now());
            } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, requestBody, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    createGifts(response.optInt("id"));
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println();
                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {

                    Map<String, String> headers = new HashMap<>();
                    headers.put("Authorization", "Bearer " + Authentication.getAuthentication());
                    headers.put("Content-Type", "application/json");
                    return headers;
                }
        };


        requestQueue.add(jsonObjectRequest);
    }

    public void createGifts (int id) {

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "https://balandrau.salle.url.edu/i3/socialgift/api/v1/gifts";
        ArrayList<Gift> gifts1 = new ArrayList<>(gifts.values());
        for (int i = 0; i < gifts1.size(); i++) {
            JSONObject requestBody = new JSONObject();
            try {
                // Add parameters to the request body
                requestBody.put("wishList_id", id);
                requestBody.put("product_url", gifts1.get(i).getProductUrl());
                requestBody.put("priority", 33);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, requestBody, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    System.out.println();
                    //if (!response.optString("title").equals("ERROR")) {
                     //   Toast.makeText(getApplicationContext(), "WishList created", Toast.LENGTH_SHORT).show();
                       // return;
                    //}
                    //Toast.makeText(getApplicationContext(), "WishList couldn't be created", Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println();
                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {

                    Map<String, String> headers = new HashMap<>();
                    headers.put("Authorization", "Bearer " + Authentication.getAuthentication());
                    headers.put("Content-Type", "application/json");
                    return headers;
                }
            };


            requestQueue.add(jsonObjectRequest);
        }
    }



    public void setComponents() {
        gifts = new Hashtable<>();
        delete = findViewById(R.id.delete);
        delete.setVisibility(View.INVISIBLE);
        findViewById(R.id.fragment_container).setVisibility(View.INVISIBLE);

        header = findViewById(R.id.header);
        name = findViewById(R.id.name);
        description = findViewById(R.id.description);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        fragmentManager = getSupportFragmentManager();
        add = findViewById(R.id.add);
        create = findViewById(R.id.create);

        fragment = fragmentManager.findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            fragment = new AddGiftFragment(this);
            fragmentManager.beginTransaction().add(R.id.fragment_container, fragment).commit();
        }


        bottomMenu = fragmentManager.findFragmentById(R.id.bottom_menu);

        if (bottomMenu == null) {
            bottomMenu = new BottomMenuFragment(this, getIntent().getIntExtra("menu_state", 2));
            fragmentManager.beginTransaction().add(R.id.bottom_menu, bottomMenu).commit();
        }

        if (getIntent().getStringExtra("edit") != null) {
            if (getIntent().getStringExtra("edit").equals("true")) {
                header.setText("Edit WishList");
                delete.setVisibility(View.VISIBLE);
                create.setText("Edit");
                name.getEditText().setText(getIntent().getStringExtra("name"));
                description.getEditText().setText(getIntent().getStringExtra("description"));
                updateUI(getIntent().getIntExtra("index", 0));
            }
        }
    }

    public void updateUI (int id) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://balandrau.salle.url.edu/i3/socialgift/api/v1/wishlists/" + id;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            ArrayList<Gift> gifts1 = new ArrayList<>();
                            JSONArray array = response.optJSONArray("gifts");
                            for (int i = 0; i < array.length(); i++) {
                                try {
                                    gifts1.add(new Gift(array.getJSONObject(i)));
                                } catch (JSONException ignored) {}
                            }
                            gifts = new Hashtable<>();

                            for (int i = 0; i < gifts1.size(); i++) {
                                gifts.put(gifts1.get(i).getId(), gifts1.get(i));
                            }
                        } catch (NullPointerException ignored) {}
                        updateGifts();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("resposta", "Hi ha hagut un error:" + error);
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put(Authentication.AUTHORIZATION, "Bearer " + Authentication.getAuthentication());
                return headers;
            }
        };

        queue.add(jsonObjectRequest);

    }

    public void setListeners() {
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.fragment_container).setVisibility(View.VISIBLE);
                add.setVisibility(View.INVISIBLE);
                create.setVisibility(View.INVISIBLE);

            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createWishList();
                Intent intent = new Intent(getApplicationContext(), SingleFragmentActivity.class);
                startActivity(intent);
            }
        });
    }

    public void setGifts (Hashtable<Integer, Gift> newGifts) {
        gifts = newGifts;
        updateGifts();
    }

    public void done() {
        findViewById(R.id.fragment_container).setVisibility(View.INVISIBLE);
        add.setVisibility(View.VISIBLE);
        create.setVisibility(View.VISIBLE);
        updateGifts();
    }

    public void updateGifts () {
        if (gifts != null) {
            ArrayList<Gift> gifts1 = new ArrayList<>(gifts.values());
            ArrayList<Product> products = new ArrayList<>();
            for (int i = 0; i < gifts1.size(); i++) {
                products.add(gifts1.get(i).getProduct());
            }
            recyclerView.setAdapter(new ProductAdapter(products, this));
        }
    }

    public void addGift (Gift gift) {
        gifts.put(gift.getId(), gift);
    }
    public void removeGift (int id) {
        gifts.remove(id);
    }
}