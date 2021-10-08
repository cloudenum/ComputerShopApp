package com.example.project.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.project.Activity.BannerDetailsActivity;
import com.example.project.Activity.CategoryItemActivity;
import com.example.project.Adapter.CategoryAdapter;
import com.example.project.Adapter.PopularAdapter;
import com.example.project.Domain.CategoryDomain;
import com.example.project.Domain.FoodDomain;
import com.example.project.Helper.TinyDB;
import com.example.project.R;
import com.synnapps.carouselview.CarouselView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeFragment extends Fragment {
    CircleImageView imgProfile;

    TinyDB tinyDB;

    private final int[] sampleImages = {
            R.drawable.banner_example1,
            R.drawable.banner_example1,
            R.drawable.banner_example1
    };

/*
    private String[] sampleImages = {
            "https://raw.githubusercontent.com/sayyam/carouselview/master/sample/src/main/res/drawable/image_3.jpg",
            "https://raw.githubusercontent.com/sayyam/carouselview/master/sample/src/main/res/drawable/image_1.jpg",
            "https://raw.githubusercontent.com/sayyam/carouselview/master/sample/src/main/res/drawable/image_2.jpg"
    };
*/

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_home, container, false);
        tinyDB = new TinyDB(getContext());

        TextView txtGreeting = fragmentView.findViewById(R.id.txtGreeting);
        imgProfile = fragmentView.findViewById(R.id.imgProfile);

        if(tinyDB.getString("name").equals("")){
            txtGreeting.setText("Richard");
        }else{
            txtGreeting.setText(tinyDB.getString("name"));
        }

        String imageURI = tinyDB.getString("profileuri");
        if(imageURI.equals("")){
            imgProfile.setImageResource(R.drawable.profile_2);
        }else{
            imgProfile.setImageURI(Uri.parse(imageURI));
        }

        CarouselView carouselView = fragmentView.findViewById(R.id.carouselBanner);
        carouselView.setClipToOutline(true);
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener((position, imageView) -> {
                Glide.with(imageView.getContext())
                        .load(sampleImages[position])
                        .into(imageView);
            }
        );

        carouselView.setImageClickListener(position -> {
                Toast.makeText(getContext(), "Slide " + position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), BannerDetailsActivity.class);
                intent.putExtra("intBannerSrc", sampleImages[position]);
                startActivity(intent);
            }
        );

        ImageView imgProfile = fragmentView.findViewById(R.id.imgProfile);
        imgProfile.setOnClickListener(v -> {
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.nav_host_fragment, new ProfileFragment())
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .addToBackStack(null)
                    .commit();
        });

        recyclerViewPopular(fragmentView);
        recyclerViewCategory(fragmentView);
        return fragmentView;
    }

    private void recyclerViewPopular(View view) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerViewPopularList = view.findViewById(R.id.rvPopular);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);

        ArrayList<FoodDomain> foodList = new ArrayList<>();
        foodList.add(new FoodDomain("Pepperoni pizza", "pizza1", "slices pepperoni ,mozzarella cheese, fresh oregano,  ground black pepper, pizza sauce", 16000));
        foodList.add(new FoodDomain("Cheese Burger", "burger", "beef, Gouda Cheese, Special sauce, Lettuce, tomato ", 13000));
        foodList.add(new FoodDomain("Vegetable pizza", "pizza2", " olive oil, Vegetable oil, pitted Kalamata, cherry tomatoes, fresh oregano, basil", 12000));

        recyclerViewPopularList.setAdapter(new PopularAdapter(foodList));

    }

    private void recyclerViewCategory(View view) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerViewCategoryList = view.findViewById(R.id.rvCategories);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryDomain> categoryList = new ArrayList<>();
        categoryList.add(new CategoryDomain("Pizza", "cat_1", "category_background1"));
        categoryList.add(new CategoryDomain("Burger", "cat_2", "category_background2"));
        categoryList.add(new CategoryDomain("Hotdog", "cat_3", "category_background3"));
//        categoryList.add(new CategoryDomain("Drink", "cat_4", "category_background4"));
//        categoryList.add(new CategoryDomain("Donut", "cat_5", "category_background5"));

        recyclerViewCategoryList.setAdapter(new CategoryAdapter(categoryList));
    }
}
