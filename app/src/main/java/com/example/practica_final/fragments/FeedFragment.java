package com.example.practica_final.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.practica_final.R;

public class FeedFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_main, container, false);
        //pokedexRecycleView = (RecyclerView) v.findViewById(R.id.pokedex_recycler_view);
        //pokedexRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));


    //    updateUI();

        return v;
    }

}
