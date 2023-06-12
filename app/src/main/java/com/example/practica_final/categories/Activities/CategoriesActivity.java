package com.example.practica_final.categories.Activities;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.practica_final.R;
import com.example.practica_final.categories.Fragments.CategoriesAdapter;
import com.example.practica_final.categories.entities.Category;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class CategoriesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private CategoriesAdapter adapter;

    public CategoriesActivity() {
    }

    public void setComponents () {
        recyclerView = findViewById(R.id.categoriesList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories);
        setComponents();
        getCategories();
    }

    public void getCategories () {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://balandrau.salle.url.edu/i3/mercadoexpress/api/v1/categories";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        ArrayList<Category> categories = new ArrayList<>();
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                categories.add(new Category(response.getJSONObject(i)));
                            }
                        } catch (JSONException ignored) {

                        }
                        updateUI(categories);
                        Log.e("resposta", "La resposta es: "+ response.toString());
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("resposta", "Hi ha hagut un error:" + error);
                    }
                });

        queue.add(jsonArrayRequest);
    }

    private void updateUI (ArrayList<Category> categories) {
        if (adapter == null) {
            adapter = new CategoriesAdapter(categories, this);
            recyclerView.setAdapter(adapter);
        } else {
            adapter.setCategories(categories);
            adapter.notifyDataSetChanged();
        }
    }





}
