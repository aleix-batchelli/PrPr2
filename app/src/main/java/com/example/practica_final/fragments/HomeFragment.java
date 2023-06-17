package com.example.practica_final.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.practica_final.R;
import com.example.practica_final.categories.Activities.CategoriesActivity;
import com.example.practica_final.wishList.Activities.CreateWishListActivity;
import com.example.practica_final.wishList.Activities.WishListsActivity;

public class HomeFragment extends Fragment {

    Button[] buttons;
    AppCompatActivity activity;

    public HomeFragment(AppCompatActivity activity) {
        this.activity = activity;
    }

    private static final int CREATEWISHLIST = 0;
    private static final int SELECTWISHLIST = 1;
    private static final int MANAGEFRIENDS = 2;
    private static final int CATEGORIES = 3;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home_layout, container, false);
        inicializeComponents(v);
        setListeners();


        return v;
    }

    void inicializeComponents (View view) {
        buttons = new Button[] {
                view.findViewById(R.id.createWishList),
                view.findViewById(R.id.selectWishList),
                view.findViewById(R.id.manageFriends),
                view.findViewById(R.id.categories)
        };
    }

    void setListeners() {
        buttons[CREATEWISHLIST].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, CreateWishListActivity.class);
                activity.startActivity(intent);
            }
        });

        buttons[SELECTWISHLIST].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, WishListsActivity.class);
                activity.startActivity(intent);
            }
        });

        buttons[MANAGEFRIENDS].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        buttons[CATEGORIES].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, CategoriesActivity.class);
                activity.startActivity(intent);
            }
        });
    }



}
