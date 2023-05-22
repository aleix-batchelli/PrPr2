package com.example.practica_final.activities;

import androidx.fragment.app.Fragment;

import com.example.practica_final.FriendListFragment;
import com.example.practica_final.activities.SingleFragmentActivity;

public class FriendsActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new FriendListFragment();
    }
}
