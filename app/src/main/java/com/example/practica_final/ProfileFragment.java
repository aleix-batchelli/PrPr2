package com.example.practica_final;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.practica_final.users.User;

import java.util.UUID;

public class ProfileFragment extends Fragment {

    private User user;
    private TextView nameTV;
    private TextView lastNameTV;
    private TextView emailTV;
    private ImageView profileIV;
    private Button chatButton;

    public ProfileFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UUID object = (UUID)
                getArguments().getSerializable("ARGUMENT_OBJECT_ID");
        //user = UserList.getPokemonByID(object);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.user_profile, container, false);

        nameTV = v.findViewById(R.id.nameTV);
        lastNameTV = v.findViewById(R.id.lastNameTv);
        emailTV = v.findViewById(R.id.emailTV);
        profileIV = v.findViewById(R.id.profileImage);
        chatButton = v.findViewById(R.id.chatButton);

        nameTV.setText(user.getName());
        lastNameTV.setText(user.getLastName());
        emailTV.setText(user.getEmail());
        //profileIV.setImageResource(1);

        return v;
    }

}

