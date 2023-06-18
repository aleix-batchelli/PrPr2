package com.example.practica_final.wishList;

import org.json.JSONException;
import org.json.JSONObject;

public class WishList {

    private int id;
    private String name;
    private String description;
    private int userId;
    private String creationDate;
    private String endDate;


    public WishList(JSONObject object) {
        try {
            this.id = object.getInt("id");
            this.name = object.getString("name");
            this.description = object.getString("description");
            this.userId = object.getInt("user_id");
            this.creationDate = object.getString("creation_date");
            this.endDate = object.getString("end_date");
        } catch (JSONException ignored) {}
    }

    public WishList(int id, String name, String description, int userId, String creationDate, String endDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.userId = userId;
        this.creationDate = creationDate;
        this.endDate = endDate;
    }

    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", id);
            jsonObject.put("name", name);
            jsonObject.put("description", description);
            jsonObject.put("user_id", userId);
            jsonObject.put("creation_date", creationDate);
            jsonObject.put("end_date", endDate);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public JSONObject toJsonEdit() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", id);
            jsonObject.put("user_id", userId);
            jsonObject.put("creation_date", creationDate);
            jsonObject.put("end_date", endDate);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
