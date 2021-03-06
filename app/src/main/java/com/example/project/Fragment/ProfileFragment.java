package com.example.project.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;

import com.example.project.Activity.DeliverActivity;
import com.example.project.Activity.EditProfileActivity;
import com.example.project.Activity.HelpActivity;
import com.example.project.Activity.IntroActivity;
import com.example.project.Domain.ProfileDomain;
import com.example.project.Helper.TinyDB;
import com.example.project.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment {
    CircleImageView imgProfile;
    TextView txtName, txtAddress;

    FragmentTransaction ft;

    TinyDB tinyDB;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        tinyDB = new TinyDB(getContext());

        txtName = view.findViewById(R.id.tvName_About);
        txtAddress = view.findViewById(R.id.tvAddress_About);
        imgProfile = view.findViewById(R.id.imgProfile_About);
        txtName.setText("Angel Monica");

        final Observer<String> nameObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String name) {
                if (name != null) {
                    if (!name.equals("")) {
                        txtName.setText(name);
                    }
                }
            }
        };

        final Observer<String> addressObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String address) {
                if (address != null) {
                    if (!address.equals("")) {
                        txtAddress.setText(address);
                    }
                }
            }
        };

        final Observer<String> imgProfileObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String imageURI) {
                if (imageURI != null) {
                    if (!imageURI.equals("")) {
                        imgProfile.setImageURI(Uri.parse(imageURI));
                    }
                }
            }
        };

        ProfileDomain profile = ProfileDomain.getInstance(getContext());
        profile.getName().observe(getViewLifecycleOwner(), nameObserver);
        profile.getAddress().observe(getViewLifecycleOwner(), addressObserver);
        profile.getImageURI().observe(getViewLifecycleOwner(), imgProfileObserver);

        ImageButton btnEditProfile = view.findViewById(R.id.btnEditProfile),
                btnMyFavorites = view.findViewById(R.id.btnMyFavorites),
                btnSentPackages = view.findViewById(R.id.btnSentPackages),
                btnPaymentHistory = view.findViewById(R.id.btnPaymentHistory);

        Button btnAboutUs = view.findViewById(R.id.btnAboutUs),
                btnHelp = view.findViewById(R.id.btnHelp),
                btnLogout = view.findViewById(R.id.btnLogout);

        FragmentManager fm = getParentFragmentManager();
        ft = fm.beginTransaction();

        btnEditProfile.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), EditProfileActivity.class);
            startActivity(intent);
        });

        btnSentPackages.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), DeliverActivity.class);
            intent.putExtra("id1", 1);
            startActivity(intent);
        });

        btnPaymentHistory.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), DeliverActivity.class);
            intent.putExtra("id1", 2);
            startActivity(intent);
        });

        btnAboutUs.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), HelpActivity.class);
            intent.putExtra("id", 2);
            startActivity(intent);
        });

        btnHelp.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), HelpActivity.class);
            intent.putExtra("id", 1);
            startActivity(intent);
        });

        btnLogout.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), IntroActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });

        return view;
    }
}
