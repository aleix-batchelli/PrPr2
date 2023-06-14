package com.example.practica_final.wishList.Activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.practica_final.R;

public class SingleWishListActivity extends AppCompatActivity {

    private TextView header;
    private TextView name;
    private TextView description;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setComponents();
        updateUI(getIntent().getIntExtra("index", 0));
    }

    public void setComponents () {
        header = findViewById(R.id.header);
        name = findViewById(R.id.name);
        description = findViewById(R.id.description);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void updateUI (int index) {
        //todo acabar recycleView
    }

    public void setGifts () {
        //todo acabar recycleView
    }

}
