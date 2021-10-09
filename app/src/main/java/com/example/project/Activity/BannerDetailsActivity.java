package com.example.project.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.project.R;

import io.noties.markwon.Markwon;

public class BannerDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_details);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        findViewById(R.id.containerImgBanner).setClipToOutline(true);
        ImageView imgBanner = findViewById(R.id.imgBanner);
        Glide.with(this)
                .load(getIntent().getIntExtra("intBannerSrc", R.drawable.banner_example1))
                .into(imgBanner);

        TextView txtBannerDetails = findViewById(R.id.txtBannerDetails);

        Markwon markwon = Markwon.builder(this).build();
        markwon.setMarkdown(txtBannerDetails, getResources().getString(R.string.large_text));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}