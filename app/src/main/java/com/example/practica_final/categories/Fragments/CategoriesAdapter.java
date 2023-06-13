package com.example.practica_final.categories.Fragments;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practica_final.R;
import com.example.practica_final.categories.Activities.SingleCategoryActivity;
import com.example.practica_final.categories.entities.Category;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoriesHolder> {

    ArrayList<Category> categories;

    AppCompatActivity activity;

    public CategoriesAdapter(ArrayList<Category> categories, AppCompatActivity activity) {
        this.categories = categories;
        this.activity = activity;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }

    @NonNull
    @Override
    public CategoriesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_layout, parent, false);
        return new CategoriesHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesHolder holder, int position) {
        Category category = categories.get(position);
        holder.name.setText(category.getName());
        Picasso.get().load(category.getPhoto()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class CategoriesHolder extends RecyclerView.ViewHolder {

        private ImageView image;
        private TextView name;
        private Button button;
        public CategoriesHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.categoryImage);
            name = itemView.findViewById(R.id.categoryName);
            button = itemView.findViewById(R.id.categoryButton);


            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity, SingleCategoryActivity.class);
                    intent.putExtra("index", getLayoutPosition());
                    activity.startActivity(intent);
                }
            });
        }
    }
}

