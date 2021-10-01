package com.example.project.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.project.Fragment.AboutUsFragment;
import com.example.project.Fragment.HelpFragment;
import com.example.project.R;

public class HelpActivity extends AppCompatActivity {

    FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        FragmentManager fm = getSupportFragmentManager();
        ft = fm.beginTransaction();

        int id = getIntent().getExtras().getInt("id", 0);

        if(id == 1){
            HelpFragment helpFragment = new HelpFragment();
            ft.replace(R.id.con1, helpFragment).commit();
        }else if(id == 2){
            AboutUsFragment aboutUsFragment = new AboutUsFragment();
            ft.replace(R.id.con1, aboutUsFragment).commit();
        }
    }
}