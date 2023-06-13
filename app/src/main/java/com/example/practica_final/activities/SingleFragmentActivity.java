package com.example.practica_final.activities;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.practica_final.ProfileFragment;
import com.example.practica_final.R;
import com.example.practica_final.fragments.BottomMenuFragment;
import com.example.practica_final.fragments.FriendManagementFragment;
import com.example.practica_final.fragments.HomeFragment;
import com.example.practica_final.fragments.MessagesListFragment;

import org.w3c.dom.Text;

public class SingleFragmentActivity extends AppCompatActivity {

    public FragmentManager fragmentManager;
    private TextView header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feed);
        setMenuComponents();
    }

    private void setMenuComponents() {
        fragmentManager = getSupportFragmentManager();
        header = findViewById(R.id.feed_header);

        //setting menu fragment
        Fragment fragment = fragmentManager.findFragmentById(R.id.bottom_menu);
        if (fragment == null) {
            fragment = new BottomMenuFragment(this, getIntent().getIntExtra("menu_state", 2));
            fragmentManager.beginTransaction().add(R.id.bottom_menu, fragment).commit();
        }


        //setting main fragment
        fragment = fragmentManager.findFragmentById(R.id.feedLayout);
        if (fragment == null) {
            fragment = new HomeFragment(this);
            fragmentManager.beginTransaction().add(R.id.feedLayout, fragment).commit();
        }
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = new HomeFragment(SingleFragmentActivity.this);
        fragmentManager.beginTransaction().add(R.id.feedLayout, fragment).commit();
    }

    public void setHeader(String header) {
        this.header.setText(header);
    }
}
