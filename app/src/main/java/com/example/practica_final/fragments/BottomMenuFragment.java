package com.example.practica_final.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.practica_final.R;

public class BottomMenuFragment extends Fragment {

    Button[] buttons;

    private static final int PROFILE = 0;
    private static final int GIFTS = 1;
    private static final int HOME = 2;
    private static final int MAIL = 3;
    private static final int FRIENDS = 4;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_menu_fragment, container, false);
        setComponents(v);
        setListeners();
        return v;

    }

    void setComponents (View v) {
        buttons = new Button[] {
                v.findViewById(R.id.profile),
                v.findViewById(R.id.gifts),
                v.findViewById(R.id.home),
                v.findViewById(R.id.mail),
                v.findViewById(R.id.friends)
        };
    }

    void setListeners () {
        buttons[PROFILE].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        buttons[GIFTS].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        buttons[HOME].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        buttons[MAIL].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        buttons[FRIENDS].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
