package com.example.practica_final.products;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Product {

    private int id;
    private String name;
    private String description;
    private String link;
    private String photo;
    private double price;
    private int[] categoryIds;

    public Product(int id, String name, String description, String link, String photo, double price, int[] categoryIds) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.link = link;
        this.photo = photo;
        this.price = price;
        this.categoryIds = categoryIds;
    }

    public Product(JSONObject object) {
        try {
            this.id = object.getInt("id");
            this.name = object.getString("name");
            this.description = object.getString("description");
            this.link = object.getString("link");
            this.photo = object.getString("photo");
            this.price = object.getDouble("price");

            JSONArray categoryIdsArray = object.getJSONArray("categoryIds");
            this.categoryIds = new int[categoryIdsArray.length()];
            for (int i = 0; i < categoryIdsArray.length(); i++) {
                this.categoryIds[i] = categoryIdsArray.getInt(i);
            }
        } catch (JSONException ignored) {
            // Handle the exception if needed
        }
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int[] getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(int[] categoryIds) {
        this.categoryIds = categoryIds;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
