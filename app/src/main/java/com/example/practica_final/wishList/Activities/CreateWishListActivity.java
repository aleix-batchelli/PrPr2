package com.example.practica_final.wishList.Activities;

import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class CreateWishListActivity extends AppCompatActivity {

    private TextView header;
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
        setListeners();
    }

    public void createWishList() {

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "https://balandrau.salle.url.edu/i3/socialgift/api/v1/wishlists";
        JSONObject requestBody = new JSONObject();
        try {
            // Add parameters to the request body
            requestBody.put("name", name.getEditText().getText());
            requestBody.put("description", description.getEditText().getText());
            JSONArray array = new JSONArray();
            ArrayList<Gift> gifts1 = new ArrayList<>(gifts.values());
            for (int i = 0; i < gifts1.size(); i++) {
                array.put(gifts1.get(i).toJSONObject());
            }
            } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, requestBody, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    if (!response.optString("title").equals("ERROR")) {
                        Toast.makeText(getApplicationContext(), "WishList created", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Toast.makeText(getApplicationContext(), "WishList couldn't be created", Toast.LENGTH_SHORT).show();
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



    public void setComponents() {
        gifts = new Hashtable<>();

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

        if (fragment == null) {
            bottomMenu = new BottomMenuFragment(this, getIntent().getIntExtra("menu_state", 2));
            fragmentManager.beginTransaction().add(R.id.bottom_menu, bottomMenu).commit();
        }
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
        ArrayList<Gift> gifts1 = new ArrayList<>(gifts.values());
        ArrayList<Product> products = new ArrayList<>();
        for (int i = 0; i < gifts1.size(); i++) {
            products.add(gifts1.get(i).getProduct());
        }
        recyclerView.setAdapter(new ProductAdapter(products, this));
    }

    public void addGift (Gift gift) {
        gifts.put(gift.getId(), gift);
    }
    public void removeGift (int id) {
        gifts.remove(id);
    }
}