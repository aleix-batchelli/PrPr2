package com.example.practica_final.fragments;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.practica_final.ProfileFragment;
import com.example.practica_final.R;
import com.example.practica_final.activities.SingleFragmentActivity;
import com.example.practica_final.products.Fragment.ProductListFragment;

public class BottomMenuFragment extends Fragment {

    private AppCompatImageButton[] buttons;

    private boolean[] activeButtons;
    private AppCompatActivity activity;

    public FragmentManager fragmentManager;

    public int state;

    private static final int PROFILE = 0;
    private static final int GIFT = 1;
    private static final int HOME = 2;
    private static final int MESSAGE = 3;
    private static final int FRIENDS = 4;

    public BottomMenuFragment(AppCompatActivity activity, int state) {
        this.activity = activity;
        this.state = state;
        activeButtons = new boolean[] {false, false, true, false, false};
        fragmentManager = activity.getSupportFragmentManager();
    }

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
        Fragment fragment;
        if (activity.getClass() == SingleFragmentActivity.class) {
            switch (state) {
                case 0:
                    animateButtons(PROFILE);
                    fragment = new ProfileFragment();
                    fragmentManager.beginTransaction().add(R.id.feedLayout, fragment).commit();
                    ((SingleFragmentActivity) activity).setHeader("Profile");
                    break;
                case 1:
                    animateButtons(GIFT);
                    fragment = new ProductListFragment(activity);
                    fragmentManager.beginTransaction().add(R.id.feedLayout, fragment).commit();
                    ((SingleFragmentActivity) activity).setHeader("Gift");
                    break;
                case 3:
                    animateButtons(MESSAGE);
                    // create new activity for register view
                    fragment = new MessagesListFragment(activity);
                    fragmentManager.beginTransaction().add(R.id.feedLayout, fragment).commit();
                    ((SingleFragmentActivity) activity).setHeader("Mail");
                    break;
                case 4:
                    animateButtons(FRIENDS);
                    fragment = new FriendManagementFragment(activity);
                    fragmentManager.beginTransaction().add(R.id.feedLayout, fragment).commit();
                    ((SingleFragmentActivity) activity).setHeader("Friends");
                    break;
                default:
                    animateButtons(HOME);
                    // create new activity for register view
                    fragment = new HomeFragment(activity);
                    fragmentManager.beginTransaction().add(R.id.feedLayout, fragment).commit();
                    ((SingleFragmentActivity) activity).setHeader("Home");
            }
        }
        return v;

    }

    void setComponents (View v) {
        buttons = new AppCompatImageButton[] {
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
                // create new activity for register view
                System.out.println("BOTO APRETAT");
                if (activity.getClass() != SingleFragmentActivity.class) {
                    Intent intent = new Intent(activity, SingleFragmentActivity.class);
                    intent.putExtra("menu_state", PROFILE);
                    activity.startActivity(intent);
                }
                animateButtons(PROFILE);
                Fragment fragment = new ProfileFragment();
                fragmentManager.beginTransaction().add(R.id.feedLayout, fragment).commit();
                ((SingleFragmentActivity) activity).setHeader("Profile");
            }
        });

        buttons[GIFT].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (activity.getClass() != SingleFragmentActivity.class) {
                    Intent intent = new Intent(activity, SingleFragmentActivity.class);
                    intent.putExtra("menu_state", GIFT);
                    activity.startActivity(intent);
                }
                animateButtons(GIFT);
                Fragment fragment = new ProductListFragment(activity);
                fragmentManager.beginTransaction().add(R.id.feedLayout, fragment).commit();
                ((SingleFragmentActivity) activity).setHeader("Gift");
            }
        });

        buttons[HOME].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (activity.getClass() != SingleFragmentActivity.class) {
                    Intent intent = new Intent(activity, SingleFragmentActivity.class);
                    intent.putExtra("menu_state", HOME);
                    activity.startActivity(intent);
                }
                animateButtons(HOME);
                // create new activity for register view
                Fragment fragment = new HomeFragment(activity);
                fragmentManager.beginTransaction().add(R.id.feedLayout, fragment).commit();
                ((SingleFragmentActivity) activity).setHeader("Home");
            }
        });

        buttons[MESSAGE].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (activity.getClass() != SingleFragmentActivity.class) {
                    Intent intent = new Intent(activity, SingleFragmentActivity.class);
                    intent.putExtra("menu_state", MESSAGE);
                    activity.startActivity(intent);
                }
                animateButtons(MESSAGE);
                // create new activity for register view
                Fragment fragment = new MessagesListFragment(activity);
                fragmentManager.beginTransaction().add(R.id.feedLayout, fragment).commit();
                ((SingleFragmentActivity) activity).setHeader("Message");
            }
        });

        buttons[FRIENDS].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("BOTO FRIENDS APRETAT");
                if (activity.getClass() != SingleFragmentActivity.class) {
                    Intent intent = new Intent(activity, SingleFragmentActivity.class);
                    intent.putExtra("menu_state", FRIENDS);
                    activity.startActivity(intent);
                }
                animateButtons(FRIENDS);
                Fragment fragment = new FriendManagementFragment(activity);
                fragmentManager.beginTransaction().add(R.id.feedLayout, fragment).commit();
                ((SingleFragmentActivity) activity).setHeader("Friends");

                //Intent intent = new Intent(SingleFragmentActivity.this, FriendsActivity.class);
                //intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                //startActivity(intent);
            }
        });
    }

    private void animateButtons (int button) {
        ImageView view = activity.findViewById(R.id.selector);
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

        Animation animation = AnimationUtils.loadAnimation(activity.getApplicationContext(), R.anim.menu_button_selected);
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
                Animation deanimation = AnimationUtils.loadAnimation(activity.getApplicationContext(), R.anim.menu_button_disselected);
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
}
