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
import java.util.HashMap;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
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
    private final String imageProfile = "data:image/png;base64,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";

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
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://balandrau.salle.url.edu/i3/socialgift/api/v1/users";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println(response);
                Intent intent = new Intent(RegisterActivity.this,FeedActivity.class);
               // startActivityForResult(intent,ID_FEED_ACTIVITY);
                System.out.println("HOLA");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(RegisterActivity.this, R.string.incorrectCredentials, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", nameET.getText().toString());
                params.put("last_name", lastNameET.getText().toString());
                params.put("email", emailET.getText().toString());
                params.put("password", passwordET.getText().toString());
                params.put("image", imageProfile);
                return params;
            }
            public Map<String, String> getHeaders() {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","application/json");
                return params;
            }
        };
        queue.add(stringRequest);
    }
}
