package com.example.practica_final.users;

import java.util.UUID;

public class User {
    private int ID;
    private String name;
    private String lastName;
    private String email;
    private String image;

    public User(int id, String name, String lastName, String email, String image) {
        this.ID = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getEmail() {
        return email;
    }

    public String getLastName() {
        return lastName;
    }

    public int getID() {
        return ID;
    }
}
