package com.example.practica_final.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.practica_final.R;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SettingsActivity extends AppCompatActivity {

    private Button accountButton;
    private Button authenticatesButton;
    private Button privacityButton;
    private Button helpButton;
    private Button deleteButton;
    private Button exitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        setComponents();
        //setProfileButtonSettings(this);

        setDeleteButtonListener();
        //setExitButtonListener();
        
    }

    private void setDeleteButtonListener() {

        deleteButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteUserAccount();
            }
        }));
    }

    private void deleteUserAccount() {


        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://balandrau.salle.url.edu/i3/socialgift/api/v1/users";

        JSONObject jsonObject = new JSONObject();

        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.DELETE, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println(response);
                Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
                startActivityForResult(intent,1);
                System.out.println("delete Completed");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(SettingsActivity.this, "delete not done", Toast.LENGTH_SHORT).show();
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
        this.accountButton = findViewById(R.id.account);
        this.authenticatesButton = findViewById(R.id.authenticates);
        this.privacityButton = findViewById(R.id.privacity);
        this.helpButton = findViewById(R.id.help);
        this.deleteButton = findViewById(R.id.delete);
        this.exitButton = findViewById(R.id.exit);
    }
}
