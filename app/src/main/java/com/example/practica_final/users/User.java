package com.example.practica_final.users;

import java.util.UUID;

public class User {
    private int ID;
    private String name;
    private String lastName;
    private String email;
    private String image;


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
