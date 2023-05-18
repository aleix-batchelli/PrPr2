package com.example.practica_final;

import androidx.fragment.app.Fragment;

public class FriendsActivity extends SingleFragmentActivity{

    @Override
    protected Fragment createFragment() {
        return new FriendListFragment();
    }
}
