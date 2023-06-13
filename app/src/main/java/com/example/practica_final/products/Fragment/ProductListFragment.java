package com.example.practica_final.products.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.practica_final.R;
import com.example.practica_final.products.Activities.SingleProductActivity;
import com.example.practica_final.products.Adapter.ProductAdapter;
import com.example.practica_final.products.Product;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ProductListFragment extends Fragment {

    private AppCompatActivity activity;
    private RecyclerView recyclerView;
    private TextInputLayout search;
    private Button searchButton;
    private ProductAdapter adapter;

    public ProductListFragment(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.product_list_fragment, container, false);
        setComponents(view);
        setListeners();
        return view;
    }


    public void setComponents(View view) {
        recyclerView = view.findViewById(R.id.productList);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        search = view.findViewById(R.id.productSearch);
        //search.getEditText().setText("Search");
        searchButton = view.findViewById(R.id.productSearchButton);
    }

    public void setListeners () {
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getProducts(search.getEditText().getText().toString());
            }
        });
    }

    public void getProducts(String search) {
        RequestQueue queue = Volley.newRequestQueue(activity);
        String url ="https://balandrau.salle.url.edu/i3/mercadoexpress/api/v1/products/search?s=" + search;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                (Request.Method.GET), url, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                ArrayList<Product> products = new ArrayList<>();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        int[] ids = new int[response.getJSONObject(i).getJSONArray("categoryIds").length()];
                        for (int j = 0; j < ids.length; j++) {
                            ids[j] = response.getJSONObject(i).getJSONArray("categoryIds").getInt(j);
                        }

                        products.add(new Product(
                                response.getJSONObject(i).getInt("id"),
                                response.getJSONObject(i).getString("name"),
                                response.getJSONObject(i).getString("description"),
                                response.getJSONObject(i).getString("link"),
                                response.getJSONObject(i).getString("photo"),
                                response.getJSONObject(i).getDouble("price"),
                                ids
                        ));

                        updateProducts(products);
                    } catch (JSONException ignored) {}
                }


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

    public void updateProducts (ArrayList<Product> products) {
        if (adapter == null) {
            adapter = new ProductAdapter(products, activity);
            recyclerView.setAdapter(adapter);
        } else {
            adapter.setProducts(products);
            adapter.notifyDataSetChanged();
        }
    }
}
