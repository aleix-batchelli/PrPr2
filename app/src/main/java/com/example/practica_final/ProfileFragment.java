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
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.practica_final.activities.MainActivity;
import com.example.practica_final.users.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
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

        getUserByID(Authentication.getUserID());


        //profileIV.setImageResource(1);

        return v;
    }

    private void getUserByID(int userID) {

        JSONObject jsonObject = new JSONObject();

        RequestQueue queue = Volley.newRequestQueue(activity);
        String url = "https://balandrau.salle.url.edu/i3/socialgift/api/v1/users/" + userID;
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, url, jsonObject, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    int id = response.getInt("id");
                    String name = response.getString("name");
                    String last_name = response.getString("last_name");
                    String email = response.getString("email");
                    String image = response.getString("image");



                    user = new User(id, name, last_name, email, image);

                    nameTV.setText(user.getName());
                    lastNameTV.setText(user.getLastName());
                    emailTV.setText(user.getEmail());
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("ERROR RESPONSE");
                error.printStackTrace();
                user = null;

            }
        }) {
            public Map<String, String> getHeaders() {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","application/json");
                params.put("Authorization", "Bearer " + Authentication.getAuthentication());
                return params;
            }
        };
        queue.add(stringRequest);


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


