package com.example.practica_final.categories.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practica_final.R;
import com.example.practica_final.categories.Activities.CategoriesActivity;
import com.example.practica_final.categories.manager.entities.Category;
import com.example.practica_final.categories.manager.CategoriesManager;


import java.util.ArrayList;

public class CategoryListFragment extends Fragment {

    private RecyclerView recyclerView;
    private CategoriesAdapter adapter;
    private CategoriesActivity activity;

    public CategoryListFragment(CategoriesActivity activity) {
        this.activity = activity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.category_list, container, false);
        recyclerView = view.findViewById(R.id.categoriesList);
        CategoriesManager.getCategories(this, activity);
        return view;
    }

    public void updateUI (ArrayList<Category> categories) {
        adapter = new CategoriesAdapter(categories);
        recyclerView.setAdapter(adapter);
    }
}