package com.example.practica_final;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

public class RegisterActivity extends AppCompatActivity {

    private Button backButton;
    private Button registerButton;

    private EditText nameET;
    private EditText lastNameET;
    private EditText emailET;
    private EditText passwordET;
    private EditText confirmPasswordET;
    private final int ID_LOGIN_ACTIVITY = 1;

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
                boolean correctInformation = checkCredentials(name, lastName, email, password, confirmPassword);
                // create new activity for feed view
                if (correctInformation) {
                    //try {
                        // register new user
                    registerUser(email, password);
                    //} catch () {//IOException e) {
                        // error on user registration
                    //}

                    Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                    startActivityForResult(intent,ID_LOGIN_ACTIVITY);
                } else {
                    Toast.makeText(RegisterActivity.this, R.string.incorrectCredentials, Toast.LENGTH_SHORT);
                }


            }
        }));


    }

    private void registerUser(String email, String password) {

    }

    private boolean checkCredentials(String name, String lastName, String email, String password, String confirmPassword) {


        return password.equals(confirmPassword);
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

}
