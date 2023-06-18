package com.example.practica_final;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.practica_final.activities.MainActivity;
import com.example.practica_final.users.User;

import java.util.UUID;

public class ProfileFragment extends Fragment {

    private User user;
    private TextView nameTV;
    private TextView lastNameTV;
    private TextView emailTV;
    private TextView PasswordTV;
    private ImageView profileIV;
    private Button logoutButton;
    private Button ActualizarButton;
    private Activity activity;

    public ProfileFragment(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.profile, container, false);

        nameTV = v.findViewById(R.id.Nombre);
        lastNameTV = v.findViewById(R.id.Apellido);
        emailTV = v.findViewById(R.id.Email);
        PasswordTV = v.findViewById(R.id.Password);
        logoutButton = v.findViewById(R.id.Logout);
        ActualizarButton = v.findViewById(R.id.Actualizar);
        setExitButtonListener();



        nameTV.setText(user.getName());
        lastNameTV.setText(user.getLastName());
        emailTV.setText(user.getEmail());
        //profileIV.setImageResource(1);

        return v;
    }
    private void cerrarSesion() {
        Authentication.setAuthentication(null);
        Authentication.setUserID(-1);
        Intent intent = new Intent(activity, MainActivity.class);
        startActivityForResult(intent,1);
    }

    private void setExitButtonListener() {
        logoutButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cerrarSesion();
            }
        }));
    }

    //Dos botones uno de logout y otro de cambiar informacion del usuario
    //El de cambiar informacion del usuario te lleva a otro fragmento donde puedes cambiar la informacion
    //El de logout te lleva a la pantalla de login




}


