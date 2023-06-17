package com.example.practica_final.wishList.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practica_final.R;
import com.example.practica_final.fragments.BottomMenuFragment;
import com.example.practica_final.gifts.Fragments.AddGiftFragment;
import com.example.practica_final.gifts.Gift;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Hashtable;

public class CreateWishListActivity extends AppCompatActivity {

    private TextView header;
    private TextInputLayout name;
    private TextInputLayout description;
    private RecyclerView recyclerView;
    private FragmentManager fragmentManager;
    private Fragment fragment;
    private Fragment bottomMenu;
    private Hashtable<Integer, Gift> gifts;

    private Button add;

    private Button create;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_wishlist_layout);
        setComponents();
        setListeners();
    }

    public void createWishList() {

    }

    public void setComponents() {
        gifts = new Hashtable<>();

        findViewById(R.id.fragment_container).setVisibility(View.INVISIBLE);

        header = findViewById(R.id.header);
        name = findViewById(R.id.name);
        description = findViewById(R.id.description);
        recyclerView = findViewById(R.id.recyclerView);
        fragmentManager = getSupportFragmentManager();
        add = findViewById(R.id.add);
        create = findViewById(R.id.create);

        fragment = fragmentManager.findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            fragment = new AddGiftFragment(this);
            fragmentManager.beginTransaction().add(R.id.fragment_container, fragment).commit();
        }


        bottomMenu = fragmentManager.findFragmentById(R.id.bottom_menu);

        if (fragment == null) {
            bottomMenu = new BottomMenuFragment(this, getIntent().getIntExtra("menu_state", 2));
            fragmentManager.beginTransaction().add(R.id.bottom_menu, bottomMenu).commit();
        }
    }

    public void setListeners() {
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.fragment_container).setVisibility(View.VISIBLE);
                add.setVisibility(View.INVISIBLE);
                create.setVisibility(View.INVISIBLE);

            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createWishList();
            }
        });
    }

    public void setGifts (Hashtable<Integer, Gift> newGifts) {
        gifts = newGifts;
        updateGifts();
    }

    public void done() {
        findViewById(R.id.fragment_container).setVisibility(View.INVISIBLE);
        add.setVisibility(View.VISIBLE);
        create.setVisibility(View.VISIBLE);
    }

    public void updateGifts () {

    }

    public void addGift (Gift gift) {
        gifts.put(gift.getId(), gift);
    }
    public void removeGift (int id) {
        gifts.remove(id);
    }
}