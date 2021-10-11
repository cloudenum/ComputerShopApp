package com.example.project.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.project.Domain.ItemDomain;
import com.example.project.Fragment.CartListFragment;
import com.example.project.Helper.ManagementCart;
import com.example.project.R;

public class ShowDetailActivity extends AppCompatActivity {
    private Button addToCartBtn;
    private TextView titleTxt, feeTxt, descriptionTxt, numberOrderTxt;
    private ImageView plusBtn, minusBtn, picFood;
    private ItemDomain object;
    private int numberOrder = 1;
    private ManagementCart managementCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        managementCart = new ManagementCart(this);

        initView();
        getBundle();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void getBundle() {
        object = (ItemDomain) getIntent().getSerializableExtra("object");

        int drawableResourceId = this.getResources().getIdentifier(object.getPic(), "drawable", this.getPackageName());

        Glide.with(this)
                .load(drawableResourceId)
                .into(picFood);

        titleTxt.setText(object.getTitle());
        feeTxt.setText("Rp" + object.getPrice());
        descriptionTxt.setText(object.getDescription());
        numberOrderTxt.setText(String.valueOf(numberOrder));

        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberOrder = numberOrder + 1;
                numberOrderTxt.setText(String.valueOf(numberOrder));
            }
        });

        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numberOrder > 1) {
                    numberOrder = numberOrder - 1;
                }
                numberOrderTxt.setText(String.valueOf(numberOrder));
            }
        });

        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                object.setNumberInCart(numberOrder);
                managementCart.insertFood(object);
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                intent.putExtra("showFragment", R.id.cart_list_fragment);
                v.getContext().startActivity(intent);
            }
        });
    }

    private void initView() {
        addToCartBtn = findViewById(R.id.addToCardBtn);
        titleTxt = findViewById(R.id.titleTxt);
        feeTxt = findViewById(R.id.priceTxt);
        descriptionTxt = findViewById(R.id.descriptionTxt);
        numberOrderTxt = findViewById(R.id.numberOrderTxt);
        plusBtn = findViewById(R.id.plusBtn);
        minusBtn = findViewById(R.id.minusBtn);
        picFood = findViewById(R.id.foodPic);
    }
}