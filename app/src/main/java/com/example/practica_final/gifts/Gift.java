package com.example.practica_final.gifts;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.practica_final.products.Product;

import org.json.JSONException;
import org.json.JSONObject;

public class Gift {

    private int id;
    private int wishListId;
    private Product product;
    private int priority;
    private boolean booked;

    public Gift(int id, int wishListId, String productUrl, int priority, boolean booked) {
        this.id = id;
        this.wishListId = wishListId;
        this.priority = priority;
        this.booked = booked;
        getProduct(productUrl);
    }

    public Gift(JSONObject object) {
        try {
            this.id = object.getInt("id");
            this.wishListId = object.getInt("wishlist_id");
            this.priority = object.getInt("priority");
            this.booked = object.getBoolean("booked");
            getProduct(object.getString("product_url"));
        } catch (JSONException ignored) {}
    }

    public void getProduct (String productUrl) {
        RequestQueue queue = Volley.newRequestQueue(new AppCompatActivity());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, productUrl, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        product = new Product(response);
                        Log.e("resposta", "La resposta es: "+ response.toString());
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("resposta", "Hi ha hagut un error:" + error);
                    }
                });

        queue.add(jsonObjectRequest);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWishListId() {
        return wishListId;
    }

    public void setWishListId(int wishListId) {
        this.wishListId = wishListId;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }
}
