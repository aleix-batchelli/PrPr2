package com.example.practica_final.activities;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.practica_final.ProfileFragment;
import com.example.practica_final.R;
import com.example.practica_final.fragments.FeedFragment;
import com.example.practica_final.fragments.FriendManagementFragment;

public class SingleFragmentActivity extends AppCompatActivity {


    private ImageButton[] buttons;
    private static final int PROFILE = 0;
    private static final int GIFT = 1;
    private static final int HOME = 2;
    private static final int MESSAGE = 3;
    private static final int FRIENDS = 4;

    private boolean[] activeButtons;
    public FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feed);



        activeButtons = new boolean[] {false, false, true, false, false};
        setMenuComponents();
        setProfileButtonSettings();
        setFriendsButtonSettings();
        setGiftButton();
        setHomeButton();
        setMessagesButton();

        fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.feedLayout);

        if (fragment == null) {
            fragment = new FeedFragment();
            fragmentManager.beginTransaction().add(R.id.feedLayout, fragment).commit();
        }

    }

    private void setMenuComponents() {
        buttons = new ImageButton[] {
                findViewById(R.id.profile),
                findViewById(R.id.gifts),
                findViewById(R.id.home),
                findViewById(R.id.mail),
                findViewById(R.id.friends)
        };
    }
    private void setMessagesButton() {
        buttons[MESSAGE].setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                animateButtons(MESSAGE);
                // create new activity for register view


            }
        }));
    }

    private void setHomeButton() {
        buttons[HOME].setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                animateButtons(HOME);
                // create new activity for register view
                Fragment fragment = new FeedFragment();
                fragmentManager.beginTransaction().add(R.id.feedLayout, fragment).commit();

            }
        }));
    }

    private void setGiftButton() {
        buttons[GIFT].setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                animateButtons(GIFT);
                //Fragment fragment = new FeedFragment();
                //fragmentManager.beginTransaction().add(R.id.feedLayout, fragment).commit();
            }
        }));
    }


    private void setFriendsButtonSettings() {
        buttons[FRIENDS].setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create new activity for register view
                System.out.println("BOTO FRIENDS APRETAT");

                animateButtons(FRIENDS);
                Fragment fragment = new FriendManagementFragment(SingleFragmentActivity.this);
                fragmentManager.beginTransaction().add(R.id.feedLayout, fragment).commit();

                //Intent intent = new Intent(SingleFragmentActivity.this, FriendsActivity.class);
                //intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                //startActivity(intent);

            }
        }));
    }

    private void setProfileButtonSettings() {
        buttons[PROFILE].setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create new activity for register view
                System.out.println("BOTO APRETAT");

                animateButtons(PROFILE);

                Fragment fragment = new ProfileFragment();
                fragmentManager.beginTransaction().add(R.id.feedLayout, fragment).commit();
            }
        }));


    }


    private void animateButtons (int button) {
        ImageView view = findViewById(R.id.selector);
        int target = 0;
        switch (button) {
            case PROFILE:
                target = -400;
                break;
            case GIFT:
                target = -205;
                break;
            case HOME:
                target = 0;
                break;
            case MESSAGE:
                target = 205;
                break;
            case FRIENDS:
                target = 400;
                break;
        }
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "translationX", target);
        objectAnimator.setDuration(400);
        objectAnimator.start();

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.menu_button_selected);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                int top = buttons[button].getTop();
                int left = buttons[button].getLeft();
                int width = buttons[button].getWidth();
                int height = buttons[button].getHeight();

                int newPosition = top - (int) (height * 0.25);
                buttons[button].layout(left, newPosition, left + width, newPosition + height);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        for (int i = 0; i < 5; i++) {
            if (activeButtons[i]) {
                if (i == button) {
                    return;
                }
                int finalI = i;
                Animation deanimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.menu_button_disselected);
                deanimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        int top = buttons[finalI].getTop();
                        int left = buttons[finalI].getLeft();
                        int width = buttons[finalI].getWidth();
                        int height = buttons[finalI].getHeight();

                        int newPosition = top + (int) (height * 0.25);
                        buttons[finalI].layout(left, newPosition, left + width, newPosition + height);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                buttons[finalI].startAnimation(deanimation);
                activeButtons[i] = false;
            }
        }

        activeButtons[button] = true;
        buttons[button].startAnimation(animation);
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = new FeedFragment();
        fragmentManager.beginTransaction().add(R.id.feedLayout, fragment).commit();
    }
}
