package com.example.practica_final;

public class Authentication {

    public static final String AUTHORIZATION = "Authorization";
    private static String authentication = "";
    private static int userID = 0;

    public static String getInstance() {
        return authentication;
    }

    public static void setAuthentication(String authenticationString) {
        authentication = authenticationString;
    }
    public static void setUserID(int userID) {
        Authentication.userID = userID;
    }

    public static int getUserID() {
        return userID;
    }

    public static String getAuthentication() {
        return authentication;
    }
}
