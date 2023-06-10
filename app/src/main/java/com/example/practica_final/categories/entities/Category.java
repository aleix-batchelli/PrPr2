package com.example.practica_final.categories.entities;

import org.json.JSONException;
import org.json.JSONObject;

public class Category {
    private Integer id;
    private String name;
    private String description;
    private String photo;
    private Integer parentID;

    public Category(int id, String name, String description, String photo, int parentID) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.photo = photo;
        this.parentID = parentID;
    }

    public Category(JSONObject obj) {
        try {
            this.id = obj.getInt("id");
            this.name = obj.getString("name");
            this.description = obj.getString("description");
            this.photo = obj.getString("photo");
            this.parentID = (obj.getString("categoryParentId").equals("null")?null:obj.getInt("categoryParentId"));
        } catch (JSONException ignored) {

        }
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPhoto() {
        return photo;
    }

    public Integer getParentID() {
        return parentID;
    }
}
