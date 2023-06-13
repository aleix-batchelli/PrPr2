package com.example.practica_final.products.Adapter;

import android.annotation.SuppressLint;
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
import com.example.practica_final.products.Activities.SingleProductActivity;
import com.example.practica_final.products.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductsHolder> {
     ArrayList<Product> products;
     AppCompatActivity activity;

     public ProductAdapter(ArrayList<Product> products, AppCompatActivity activity) {
         this.products = products;
         this.activity = activity;
     }

     public void setProducts(ArrayList<Product> products) {
         this.products = products;
     }

    @NonNull
    @Override
    public ProductsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_holder, parent, false);
        return new ProductAdapter.ProductsHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ProductsHolder holder, int position) {
        Product product = products.get(position);
        holder.name.setText(product.getName());
        holder.price.setText(product.getPrice().toString() + "€");
        Picasso.get().load(product.getPhoto()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }


    public class ProductsHolder extends RecyclerView.ViewHolder {
         private ImageView image;
         private TextView name;
         private TextView price;

         private Button button;
         public ProductsHolder(@NonNull View itemView) {
             super(itemView);
             image = itemView.findViewById(R.id.productImage);
             name = itemView.findViewById(R.id.productName);
             price = itemView.findViewById(R.id.productPrice);
             button = itemView.findViewById(R.id.productHolderButton);

             button.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     Intent intent = new Intent(activity, SingleProductActivity.class);
                     intent.putExtra("index", getLayoutPosition());
                     activity.startActivity(intent);
                 }
             });
         }
    }
}
