package com.example.practica_final;

public class Authentication {
    private static String authentication = "";

    public static String getInstance() {
        return authentication;
    }

    public static void setAuthentication(String authentication) {
        Authentication.authentication = authentication;
    }
}
