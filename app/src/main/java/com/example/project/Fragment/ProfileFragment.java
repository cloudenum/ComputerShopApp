package com.example.project.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.project.Activity.EditProfileActivity;
import com.example.project.Activity.IntroActivity;
import com.example.project.R;

public class ProfileFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        ImageButton btnEditProfile = view.findViewById(R.id.btnEditProfile),
                btnMyFavorites = view.findViewById(R.id.btnMyFavorites),
                btnSentPackages = view.findViewById(R.id.btnSentPackages),
                btnPaymentHistory = view.findViewById(R.id.btnPaymentHistory);

        Button btnAboutUs = view.findViewById(R.id.btnAboutUs),
                btnHelp = view.findViewById(R.id.btnHelp),
                btnLogout = view.findViewById(R.id.btnLogout);

        btnEditProfile.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), EditProfileActivity.class);
            startActivity(intent);
        });

        btnMyFavorites.setOnClickListener(v -> {
//            Intent intent = new Intent(v.getContext(), MyFavoritesActivity.class);
//            startActivity(intent);
        });

        btnSentPackages.setOnClickListener(v -> {
//            Intent intent = new Intent(v.getContext(), SentPackagesActivity.class);
//            startActivity(intent);
        });

        btnPaymentHistory.setOnClickListener(v -> {
//            Intent intent = new Intent(v.getContext(), PaymentHistoryActivity.class);
//            startActivity(intent);
        });

        btnAboutUs.setOnClickListener(v -> {
//            Intent intent = new Intent(v.getContext(), AboutUsActivity.class);
//            startActivity(intent);
        });

        btnHelp.setOnClickListener(v -> {
//            Intent intent = new Intent(v.getContext(), HelpActivity.class);
//            startActivity(intent);
        });

        btnLogout.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), IntroActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });

        return view;
    }
}
