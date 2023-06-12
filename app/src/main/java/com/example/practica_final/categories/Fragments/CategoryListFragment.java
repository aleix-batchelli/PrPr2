package com.example.practica_final.categories.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.practica_final.R;
import com.example.practica_final.categories.entities.Category;


import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class CategoryListFragment extends Fragment {

    private RecyclerView recyclerView;
    private CategoriesAdapter adapter;
    private AppCompatActivity activity;

    public CategoryListFragment(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.category_list, container, false);
        recyclerView = view.findViewById(R.id.categoriesList);
        getCategories();
        return view;
    }

    public void getCategories () {
        RequestQueue queue = Volley.newRequestQueue(activity);
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
            adapter = new CategoriesAdapter(categories);
            recyclerView.setAdapter(adapter);
        } else {
            adapter.setCategories(categories);
            adapter.notifyDataSetChanged();
        }
    }
}