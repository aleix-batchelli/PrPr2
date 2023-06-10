package com.example.practica_final.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.practica_final.Authentication;
import com.example.practica_final.R;
import com.example.practica_final.users.UserInfoProvider;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private Button loginButton;
    private Button registerButton;
    private EditText emailET;
    private EditText passwordET;
    private final int ID_REGISTER_ACTIVITY = 2;
    private final int ID_FEED_ACTIVITY = 3;
    private boolean correctCredentials;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        setComponents();

        registerButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create new activity for register view
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivityForResult(intent,ID_REGISTER_ACTIVITY);
            }
        }));

        loginButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get data from email edit text and password edit text
                String email = emailET.getText().toString();
                String password = passwordET.getText().toString();

                // check if the credentials got are correct
                checkCredentials(email, password);
                // create new activity for feed view

            }
        }));

    }


    private void checkCredentials(String email, String password) {
        makePost();
       //Intent intent = new Intent(LoginActivity.this, SingleFragmentActivity.class);

        //intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        //startActivity(intent);
        //int userID = UserInfoProvider.getUserID(accessToken);

        return;
    }

    private void makePost() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://balandrau.salle.url.edu/i3/socialgift/api/v1/users/login";

        JSONObject jsonObject = new JSONObject();
        try {
            //jsonObject.put("email", emailET.getText().toString());
            jsonObject.put("email", "marc@marc.com");
            jsonObject.put("password", "123456789");
            //jsonObject.put("password", passwordET.getText().toString());

        } catch (JSONException e) {

        }
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println(response);
                String accessToken = null;
                try {
                    accessToken = response.getString("accessToken");
                    System.out.println("accesstoken: " + accessToken);

                    Intent intent = new Intent(LoginActivity.this, SingleFragmentActivity.class);
                    int userID = UserInfoProvider.getUserID(accessToken);

                    // save access token and user ID
                    Authentication.setAuthentication(accessToken);
                    Authentication.setUserID(userID);
                    startActivityForResult(intent,ID_FEED_ACTIVITY);

                    System.out.println("Login Completed");
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(LoginActivity.this, R.string.incorrectCredentials, Toast.LENGTH_SHORT).show();
            }
        }) {
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","application/json");
                return params;
            }
        };
        queue.add(stringRequest);
    }

    private void setComponents() {
        this.loginButton = findViewById(R.id.loginButton);
        this.registerButton = findViewById(R.id.registerButton);
        this.emailET = findViewById(R.id.emailEditText);
        this.passwordET = findViewById(R.id.passwordEditText);
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode!= Activity.RESULT_OK){
            return;
        }

        if(requestCode==ID_REGISTER_ACTIVITY){
            return;
        }
    }
}
