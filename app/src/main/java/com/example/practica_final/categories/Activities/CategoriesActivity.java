package com.example.practica_final.categories.Activities;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.practica_final.R;
import com.example.practica_final.categories.Fragments.CategoryListFragment;

public class CategoriesActivity extends AppCompatActivity {
    FrameLayout fragment;
    FragmentManager fragmentManager;

    CategoryListFragment categoriesFragment;

    public CategoriesActivity() {
        //this.categoriesFragment = new CategoryListFragment(this);
    }

    public void setComponents () {
        fragment = findViewById(R.id.categoriesLayout);
        fragmentManager = getSupportFragmentManager();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories);
        setComponents();

        fragmentManager = getSupportFragmentManager();
        Fragment fragment = new CategoryListFragment(this);
        fragmentManager.beginTransaction().add(R.id.categoriesLayout, fragment).commit();
    }



}
