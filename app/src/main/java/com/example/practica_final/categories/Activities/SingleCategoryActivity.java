package com.example.practica_final.categories.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
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
import com.example.practica_final.categories.Fragments.CategoriesAdapter;
import com.example.practica_final.categories.entities.Category;
import com.example.practica_final.products.Adapter.ProductAdapter;
import com.example.practica_final.products.Product;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.JsonParseException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SingleCategoryActivity extends AppCompatActivity {

    //todo afegir boto delete per les categories

    private TextView header;
    private TextInputEditText catName;
    private Button button;
    private RecyclerView recyclerView;
    private Category category;
    private ProductAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_category_layout);
        Intent intent = getIntent();
        setComponents();
        setListeners();
        getCategory(getIntent().getIntExtra("index", 0));
        getProducts(getIntent().getIntExtra("index", 0));
        //todo al layout afegir mes info de les categories
    }

    private void getCategory (int id) {

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://balandrau.salle.url.edu/i3/mercadoexpress/api/v1/categories/" + id;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        updateUI(new Category(response));
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

    private void editCategory () {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://balandrau.salle.url.edu/i3/mercadoexpress/api/v1/categories/" + category.getId();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, url, category.castToJSONObject(), new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        getCategory(category.getId());
                        successfullEditMessage();
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

    public void deleteCategory () {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://balandrau.salle.url.edu/i3/mercadoexpress/api/v1/categories/" + category.getId();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.DELETE, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        successfullDeleteMessage();
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

    private void successfullEditMessage () {
        Toast.makeText(this, "Changes saved successfully!", Toast.LENGTH_SHORT).show();
    }

    private void successfullDeleteMessage () {
        Toast.makeText(this, "Category was successfully deleted!", Toast.LENGTH_SHORT).show();
    }

    private void setComponents() {
        header = findViewById(R.id.singleCategoryHeader);
        catName = findViewById(R.id.catName);
        button = findViewById(R.id.setNameButton);
        recyclerView = findViewById(R.id.categoryProductsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setListeners() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editCategory();
            }
        });

        //todo afegir el listener de delete category.
    }

    public void updateUI(Category category) {
        this.category = category;
        header.setText(category.getName());
        catName.setText(category.getName());
        //todo recyclerView
    }

    public void getProducts(int id) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://balandrau.salle.url.edu/i3/mercadoexpress/api/v1/products/search?s=" + id;

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

                            } catch (JSONException ignored) {}
                        }

                        updateProducts(products);
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

    private void updateProducts(ArrayList<Product> products) {
        if (adapter == null) {
            adapter = new ProductAdapter(products, this);
            recyclerView.setAdapter(adapter);
        } else {
            adapter.setProducts(products);
            adapter.notifyDataSetChanged();
        }
    }
}
