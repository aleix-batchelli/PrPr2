package com.example.practica_final;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class ApiHelper {

    private final AppCompatActivity activity;
    public String response;

    public ApiHelper(AppCompatActivity activity) {
        this.activity = activity;
    }

    public String postToUrl(String url) {
        RequestQueue queue = Volley.newRequestQueue(activity);
        //String url ="https://balandrau.salle.url.edu/i3/socialgift/api-docs/v1/users";

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
       // return accessToken;
        return null;
    }

}
