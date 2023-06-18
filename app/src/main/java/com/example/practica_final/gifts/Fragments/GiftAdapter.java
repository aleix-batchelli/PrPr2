package com.example.practica_final.gifts.Fragments;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.practica_final.Authentication;
import com.example.practica_final.R;
import com.example.practica_final.gifts.Gift;
import com.example.practica_final.products.Product;
import com.example.practica_final.wishList.Activities.CreateWishListActivity;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class GiftAdapter extends RecyclerView.Adapter<GiftAdapter.GiftHolder>{

    private ArrayList<Gift> gifts;
    private final CreateWishListActivity activity;

    public GiftAdapter(ArrayList<Gift> gifts, CreateWishListActivity activity) {
        this.activity = activity;
        this.gifts = gifts;
    }

    public void setGifts(ArrayList<Gift> gifts) {
        this.gifts = gifts;
    }


    @NonNull
    @Override
    public GiftAdapter.GiftHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gift_holder, parent, false);
        return new GiftAdapter.GiftHolder(view);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull GiftAdapter.GiftHolder holder, int position) {
        Product product = gifts.get(position).getProduct();
        if (product != null) {
            holder.setPosition(position);
            Picasso.get().load(product.getPhoto()).into(holder.image);
            holder.name.setText(product.getName());
            holder.price.setText(product.getPrice() + "€");
        }
    }


    @Override
    public int getItemCount() {
        return gifts.size();
    }

    public class GiftHolder extends RecyclerView.ViewHolder {

        @SuppressLint("UseSwitchCompatOrMaterialCode")
        public Switch aSwitch;

        public TextView name;

        public TextView price;

        public ImageView image;

        public int position;
        public GiftHolder(@NonNull View itemView) {
            super(itemView);
            setComponents();
            setListeners();
        }

        public void setListeners () {
            aSwitch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (aSwitch.isChecked()) {
                        activity.addGift(gifts.get(position));
                    } else {
                        activity.removeGift(gifts.get(position).getId());
                    }
                }
            });
        }

        public void setPosition(int position) {
            this.position = position;
        }

        private void setComponents() {
            aSwitch = itemView.findViewById(R.id.switch1);
            name = itemView.findViewById(R.id.giftName);
            price = itemView.findViewById(R.id.price);
            image = itemView.findViewById(R.id.giftImage);
        }
    }
}
