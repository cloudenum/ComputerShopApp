package com.example.project.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.project.Domain.CityModel;
import com.example.project.Fragment.DeliveredFragment;
import com.example.project.Fragment.DeliveringFragment;
import com.example.project.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.*;

public class DeliverActivity extends AppCompatActivity {

    FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliver);

        FragmentManager fragmentManager = getSupportFragmentManager();
        ft = fragmentManager.beginTransaction();

        int id = getIntent().getExtras().getInt("id1", 0);

        if(id == 1){
            DeliveringFragment deliveringFragment = new DeliveringFragment();
            ft.replace(R.id.con, deliveringFragment).commit();
        }else if(id == 2){
            DeliveredFragment deliveredFragment = new DeliveredFragment();
            ft.replace(R.id.con, deliveredFragment).commit();
        }
    }
}