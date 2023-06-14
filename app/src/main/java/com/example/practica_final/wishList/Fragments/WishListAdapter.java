package com.example.practica_final.wishList.Fragments;

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
import com.example.practica_final.wishList.Activities.SingleWishListActivity;
import com.example.practica_final.wishList.WishList;

import java.util.ArrayList;

public class WishListAdapter extends RecyclerView.Adapter<WishListAdapter.WishListHolder>{
    ArrayList<WishList> wishLists;

    AppCompatActivity activity;

    public WishListAdapter(ArrayList<WishList> wishList, AppCompatActivity activity) {
        this.wishLists = wishList;
        this.activity = activity;
    }

    public void setWishLists(ArrayList<WishList> wishLists) {
        this.wishLists = wishLists;
    }

    @NonNull
    @Override
    public WishListAdapter.WishListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_layout, parent, false);
        return new WishListAdapter.WishListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WishListAdapter.WishListHolder holder, int position) {
        WishList wishList = wishLists.get(position);
        holder.name.setText(wishList.getName());
    }

    @Override
    public int getItemCount() {
        return this.wishLists.size();
    }

    public class WishListHolder extends RecyclerView.ViewHolder {

        private ImageView image;
        private TextView name;
        private Button button;
        public WishListHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.categoryImage);
            image.setVisibility(View.INVISIBLE);

            name = itemView.findViewById(R.id.categoryName);
            button = itemView.findViewById(R.id.categoryButton);


            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity, SingleWishListActivity.class);
                    intent.putExtra("index", getLayoutPosition());
                    activity.startActivity(intent);
                }
            });
        }
    }
}
