package com.example.practica_final.gifts.Fragments;

import android.media.MediaCodec;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.practica_final.Authentication;
import com.example.practica_final.R;
import com.example.practica_final.gifts.Gift;
import com.example.practica_final.products.Adapter.ProductAdapter;
import com.example.practica_final.products.Product;
import com.example.practica_final.wishList.Activities.CreateWishListActivity;
import com.example.practica_final.wishList.Fragments.WishListAdapter;
import com.example.practica_final.wishList.WishList;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddGiftFragment extends Fragment {

    private CreateWishListActivity activity;
    private RecyclerView recyclerView;

    private GiftAdapter adapter;

    private Button done;

    public AddGiftFragment(CreateWishListActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_gift_fragment, container, false);
        setComponents(view);
        setListeners();
        getGifts();
        return  view;
    }

    public void setComponents(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        done = view.findViewById(R.id.done);
    }

    public void setListeners() {
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.done();
            }
        });
    }

    public void getGifts() {
        RequestQueue queue = Volley.newRequestQueue(activity);
        String url = "https://balandrau.salle.url.edu/i3/socialgift/api/v1/gifts";

        JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                ArrayList<Gift> gifts = new ArrayList<>();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        Gift gift = new Gift(response.getJSONObject(i));
                        gifts.add(new Gift(response.getJSONObject(i)));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                getProducts(gifts);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + Authentication.getAuthentication());
                return headers;
            }
        };
        queue.add(request);
    }

    public void getProducts(ArrayList<Gift> gifts) {
        RequestQueue queue = Volley.newRequestQueue(activity);
        String url = "https://balandrau.salle.url.edu/i3/mercadoexpress/api/v1/products";

        JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                ArrayList<Product> products = new ArrayList<>();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        products.add(new Product(response.getJSONObject(i)));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                setAdapter(products, gifts);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + Authentication.getAuthentication());
                return headers;
            }
        };
        queue.add(request);
    }

    public void setAdapter (ArrayList<Product> products, ArrayList<Gift> gifts) {
        if (adapter == null) {
            adapter = new GiftAdapter(gifts, products, activity);
            recyclerView.setAdapter(adapter);
        } else {
            adapter.setGifts(gifts);
            adapter.notifyDataSetChanged();
        }
    }

}
