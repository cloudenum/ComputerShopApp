package com.example.project.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.widget.TextView;

import com.example.project.Fragment.CartListFragment;
import com.example.project.Fragment.HomeFragment;
import com.example.project.Fragment.ProfileFragment;
import com.example.project.Fragment.SettingsFragment;
import com.example.project.Fragment.SupportFragment;
import com.example.project.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.lang.reflect.Type;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigation();

        int showFragment = getIntent().getIntExtra("showFragment", -1);
        if (showFragment != -1) {
            Fragment fragment;
            if (showFragment == R.id.cart_list_fragment)
                fragment = new CartListFragment();
            else if (showFragment == R.id.profile_fragment)
                fragment = new ProfileFragment();
            else if (showFragment == R.id.settings_fragment)
                fragment = new SettingsFragment();
            else if (showFragment == R.id.support_fragment)
                fragment = new SupportFragment();
            else
                fragment = new HomeFragment();

            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setReorderingAllowed(true);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.replace(R.id.nav_host_fragment, fragment).commit();
        }
    }

    private void bottomNavigation() {
        TextView homeBtn = findViewById(R.id.homeBtn);
        homeBtn.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
            navController.navigate(R.id.home_fragment);
        });

        TextView profileBtn = findViewById(R.id.profileBtn);
        profileBtn.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
            navController.navigate(R.id.profile_fragment);
        });

        TextView supportBtn = findViewById(R.id.supportBtn);
        supportBtn.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
            navController.navigate(R.id.support_fragment);
        });

        TextView settingsBtn = findViewById(R.id.settingsBtn);
        settingsBtn.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
            navController.navigate(R.id.settings_fragment);
        });

        FloatingActionButton addToCartFab = findViewById(R.id.card_btn);
        addToCartFab.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
            navController.navigate(R.id.cart_list_fragment);
        });
    }
}