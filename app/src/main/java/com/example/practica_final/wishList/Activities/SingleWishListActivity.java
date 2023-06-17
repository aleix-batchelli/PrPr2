package com.example.practica_final.wishList.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
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
import com.example.practica_final.gifts.Gift;
import com.example.practica_final.products.Adapter.ProductAdapter;
import com.example.practica_final.products.Product;
import com.example.practica_final.wishList.WishList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SingleWishListActivity extends AppCompatActivity {

    private TextView header;
    private TextView name;
    private TextView description;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_wishlist_layout);
        setComponents();
        updateUI(getIntent().getIntExtra("index", 0));
    }

    public void setComponents () {
        header = findViewById(R.id.header);
        name = findViewById(R.id.name);
        description = findViewById(R.id.description);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        updateUI(getIntent().getIntExtra("index", 0));
    }

    public void updateUI(int index) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://balandrau.salle.url.edu/i3/socialgift/api/v1/wishlists/" + index;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        setGifts(response);
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



    public void setGifts (JSONObject object) {
        try {
            WishList wishList = new WishList(object);
            header.setText(object.getString("name"));
            name.setText("Name: " + object.getString("name"));
            description.setText("Description: " +object.getString("description"));

            ArrayList<Gift> gifts = new ArrayList<>();
            JSONArray array = object.getJSONArray("gifts");
            if (array.length() == 0) {
                String text = description.getText().toString();
                description.setText(text + "\n" + "No gifts");

            }
            for (int i = 0; i < array.length(); i++) {
                gifts.add(new Gift(array.getJSONObject(i)));
            }
            ArrayList<Product> products = new ArrayList<>();
            for (int i = 0; i < gifts.size(); i++) {
                //products.add(gifts.get(i).getProduct());
            }
            ProductAdapter productAdapter = new ProductAdapter(products, this);
            recyclerView.setAdapter(productAdapter);
            Log.e("resposta", "La resposta es: " + object.toString());
        } catch (JSONException ignored) {}
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, WishListsActivity.class);
        startActivity(intent);
    }
}
