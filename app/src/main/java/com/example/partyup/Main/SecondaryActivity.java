package com.example.partyup.Main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.example.partyup.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SecondaryActivity extends AppCompatActivity {
    BottomNavigationView bnv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);
        setUpNavigation();
    }

    public void setUpNavigation(){
        bnv =findViewById(R.id.bottomNavigationView);
        NavHostFragment navHostFragment = (NavHostFragment)getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment2);
        NavigationUI.setupWithNavController(bnv,
                navHostFragment.getNavController());
    }
}