package com.example.project.Fragment;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.project.Activity.HelpActivity;
import com.example.project.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AboutDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AboutDetailFragment extends Fragment {
    TextView txtAbout;

    String transaksi = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer eget sodales ligula. Mauris vitae fringilla dui, non pellentesque ex.";
    String tentang_produk = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer eget sodales ligula. Mauris vitae fringilla dui, non pellentesque ex.";
    String kesempatan_bisnis = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer eget sodales ligula. Mauris vitae fringilla dui, non pellentesque ex.";
    String garansi = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer eget sodales ligula. Mauris vitae fringilla dui, non pellentesque ex.";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about_detail, container, false);

        txtAbout = view.findViewById(R.id.txtAboutDetail);

        Bundle bundle = this.getArguments();
        int id = bundle.getInt("aboutdetail", 0);

        if(id == 1){
            txtAbout.setText(transaksi);
        }else if(id == 2){
            txtAbout.setText(tentang_produk);
        }
        else if(id == 3){
            txtAbout.setText(kesempatan_bisnis);
        }
        else if(id == 4){
            txtAbout.setText(garansi);
        }

        return view;
    }
}