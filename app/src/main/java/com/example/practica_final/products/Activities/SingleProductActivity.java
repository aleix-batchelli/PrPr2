package com.example.practica_final.products.Activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.practica_final.R;
import com.example.practica_final.products.Product;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SingleProductActivity extends AppCompatActivity {

    private Button addToWishList;
    private ImageView image;
    private TextView name;
    private TextView price;

    private TextView link;

    private TextView description;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_product_layout);
        setComponents();
        int a = getIntent().getIntExtra("index", 0);
        getProduct(getIntent().getIntExtra("index", 0));
    }

    public void setComponents () {
        addToWishList = findViewById(R.id.addToWishList);
        image = findViewById(R.id.productImage);
        name = findViewById(R.id.productName);
        price = findViewById(R.id.productPrice);
        description = findViewById(R.id.productDescription);
        link = findViewById(R.id.productLink);
    }

    public void getProduct (int id) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://balandrau.salle.url.edu/i3/mercadoexpress/api/v1/products/" + id;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                (Request.Method.GET), url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    int[] ids = new int[response.getJSONArray("categoryIds").length()];
                    for (int i = 0; i < ids.length; i++) {
                        ids[i] = response.getJSONArray("categoryIds").getInt(i);
                    }


                    Product product = new Product(
                        response.getInt("id"),
                        response.getString("name"),
                        response.getString("description"),
                        response.getString("link"),
                        response.getString("photo"),
                        response.getDouble("price"),
                        ids
                    );

                    setProduct(product);
                    Log.e("resposta", "La resposta es: "+ response.toString());
                } catch (JSONException ignored) {}
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("resposta", "Hi ha hagut un error:" + error);
            }
        });

        queue.add(jsonObjectRequest);
    }


    public void setProduct (Product product) {
        Picasso.get().load(product.getPhoto()).into(image);
        name.setText("Name: " + product.getName());
        price.setText("Price: " + product.getPrice());
        description.setText("Description: " + product.getDescription());
        link.setText(product.getLink());

    }

    public void addProductToWishList() {

    }
}
