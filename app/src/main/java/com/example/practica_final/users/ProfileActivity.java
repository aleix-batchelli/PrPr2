package com.example.practica_final.users;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.example.practica_final.ProfileFragment;
import com.example.practica_final.SingleFragmentActivity;

import java.util.UUID;

public class ProfileActivity extends SingleFragmentActivity {

    protected Fragment createFragment() {
        UUID objectID = (UUID) getIntent().getSerializableExtra("UUID_USER");

        Bundle args = new Bundle();
        args.putSerializable("ARGUMENT_OBJECT_ID", objectID);

        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(args);

        return fragment;
        //return new Fragment();
    }
}
