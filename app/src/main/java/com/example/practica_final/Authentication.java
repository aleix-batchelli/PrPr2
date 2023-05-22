package com.example.practica_final;

public class Authentication {
    private static String authentication = "";
    private static int userID = 0;

    public static String getInstance() {
        return authentication;
    }

    public static void setAuthentication(String authentication) {
        Authentication.authentication = authentication;
    }
    public static void setUserID(int userID) {
        Authentication.userID = userID;
    }

    public int getUserID() {
        return userID;
    }
}
