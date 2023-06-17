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
    private String productUrl;
    private int priority;
    private boolean booked;

    public Gift(int id, int wishListId, String productUrl, int priority, boolean booked) {
        this.id = id;
        this.wishListId = wishListId;
        this.priority = priority;
        this.booked = booked;
    }

    public Gift(JSONObject object) {
        try {
            this.id = object.getInt("id");
            this.wishListId = object.getInt("wishlist_id");
            this.priority = object.getInt("priority");
            this.productUrl = object.getString("product_url");
            this.booked = (object.getInt("booked") == 0? false : true);
        } catch (JSONException ignored) {}
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

    public String getProductUrl() {
        return this.productUrl;
    }

    public void setProductUrl(String url) {
        this.productUrl = url;
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

    public int getProductId () {
        String[] parts = this.productUrl.split("/");
        return Integer.parseInt(parts[parts.length - 1]);
    }
}
