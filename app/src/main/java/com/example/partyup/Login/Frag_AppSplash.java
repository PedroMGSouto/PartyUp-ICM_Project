package com.example.partyup.Login;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.partyup.R;

public class Frag_AppSplash extends Fragment {


    public Frag_AppSplash() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_frag__app_splash, container, false);

        CardView logbut = (CardView) v.findViewById(R.id.loginButton);
        CardView regbut = (CardView) v.findViewById(R.id.registerButton);

        logbut.setOnClickListener(v1 -> {
            Navigation.findNavController(v1).navigate(R.id.action_frag_AppSplash_to_frag_Login2);
        });
        regbut.setOnClickListener(v1 -> {
            Navigation.findNavController(v1).navigate(R.id.action_frag_AppSplash_to_frag_Register);
        });


        return v;
    }
}