package com.example.practica_final.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.practica_final.R;

public class RegisterActivity extends AppCompatActivity {

    private Button registerButton;

    private EditText nameET;
    private EditText lastNameET;
    private EditText emailET;
    private EditText passwordET;
    private EditText confirmPasswordET;
    private final int ID_LOGIN_ACTIVITY = 1;
    private final String imageProfile = "https://www.shareicon.net/data/128x128/2016/09/15/829453_user_512x512.png";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        setComponents();
        registerButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create new activity for register view
                // get data from email edit text and password edit text
                String name = nameET.getText().toString();
                String lastName = lastNameET.getText().toString();
                String email = emailET.getText().toString();
                String password = passwordET.getText().toString();
                String confirmPassword = confirmPasswordET.getText().toString();

                // check if the credentials got are correct
                checkCredentials(name, lastName, email, password, confirmPassword);
                // create new activity for feed view


            }
        }));


    }

    private void registerUser(String email, String password) {

    }

    private boolean checkCredentials(String name, String lastName, String email, String password, String confirmPassword) {
        if (password.equals(confirmPassword)) {
            makePost();
        }

        return true;
    }

    private void setComponents() {
        this.nameET = findViewById(R.id.nameEditText);
        this.lastNameET = findViewById(R.id.lastNameEditText);
        this.emailET = findViewById(R.id.emailEditText);
        this.passwordET = findViewById(R.id.passwordEditText);
        this.confirmPasswordET = findViewById(R.id.confirmPasswordEditText);
        this.registerButton = findViewById(R.id.registerButton);
    }

    public void makeRequest() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://balandrau.salle.url.edu/i3/socialgift/api-docs/v1/users";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("resposta", "La resposta es: " + response.toString());
                        try {
                            String accessToken = response.getString("accessToken").toString();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("resposta", "Hi ha hagut un error:" + error);
                    }
                }
                );

        queue.add(jsonObjectRequest);


    }

    private void makePost() {
        System.out.println(nameET.getText().toString());
        System.out.println(lastNameET.getText().toString());
        System.out.println(emailET.getText().toString());
        System.out.println(passwordET.getText().toString());

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name", nameET.getText().toString());
            jsonObject.put("last_name", lastNameET.getText().toString());
            jsonObject.put("email", emailET.getText().toString());
            jsonObject.put("password", passwordET.getText().toString());
            jsonObject.put("image", imageProfile);
        } catch (JSONException e) {

        }


        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://balandrau.salle.url.edu/i3/socialgift/api/v1/users";
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println(response);
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivityForResult(intent,ID_LOGIN_ACTIVITY);
                System.out.println("Register Completed");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(RegisterActivity.this, R.string.incorrectCredentials, Toast.LENGTH_SHORT).show();
            }
        }) {
            public Map<String, String> getHeaders() {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","application/json");
                return params;
            }
        };
        queue.add(stringRequest);
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode!= Activity.RESULT_OK){
            return;
        }

        if(requestCode==ID_LOGIN_ACTIVITY){
            return;
        }
    }
}
