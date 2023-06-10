package com.example.practica_final.categories.manager;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.practica_final.categories.Activities.CategoriesActivity;
import com.example.practica_final.categories.Fragments.CategoryListFragment;
import com.example.practica_final.categories.entities.Category;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CategoriesManager {

    public static void getCategories(CategoryListFragment categoriesFragment, CategoriesActivity activity) {
        ArrayList<Category> ret = new ArrayList<>();

        RequestQueue queue = Volley.newRequestQueue(activity);
        String url ="https://balandrau.salle.url.edu/i3/mercadoexpress/api/v1/categories";

        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject obj = response.getJSONObject(i);
                                ret.add(new Category(obj));
                            } catch (JSONException ignored) {
                            }
                        }
                        categoriesFragment.updateUI(ret);
                        Log.e("resposta", "La resposta es: "+ response.toString());
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("resposta", "Hi ha hagut un error:" + error);
                    }
                });

        queue.add(jsonObjectRequest);
    }
}
