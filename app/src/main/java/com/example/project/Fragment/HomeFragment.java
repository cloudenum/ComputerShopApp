package com.example.project.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.project.Activity.BannerDetailsActivity;
import com.example.project.Adapter.CategoryAdapter;
import com.example.project.Adapter.PopularAdapter;
import com.example.project.Domain.CategoryDomain;
import com.example.project.Domain.ItemDomain;
import com.example.project.Domain.ProfileDomain;
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
        txtGreeting.setText("Hi Angel Monica!");

        final Observer<String> nameObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String name) {
                if (name != null) {
                    if (!name.equals("")) {
                        txtGreeting.setText("Hi " + name + "!");
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
        profile.getImageURI().observe(getViewLifecycleOwner(), imgProfileObserver);

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

    private void recyclerViewCategory(View view) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerViewCategoryList = view.findViewById(R.id.rvCategories);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryDomain> categoryList = new ArrayList<>();
        categoryList.add(new CategoryDomain("Aksesoris", "cat_acc_icon", "category_background"));
        categoryList.add(new CategoryDomain("CCTV", "cat_cctv_icon", "category_background"));
        categoryList.add(new CategoryDomain("Networking", "cat_networking_icon", "category_background"));

        recyclerViewCategoryList.setAdapter(new CategoryAdapter(categoryList));
    }

    private void recyclerViewPopular(View view) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerViewPopularList = view.findViewById(R.id.rvPopular);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);

        ArrayList<ItemDomain> items = new ArrayList<>();
        items.add(new ItemDomain("Mouse Rexus X5 6d", "rexus_x5_6d", "MOUSE GAMING : Professional 2.4GHz Optical Wireless GAMING MOUSE Compatible USB Button Gaming Mouse Gaming Mouse Computer Mouse For PC Laptop with mini US Produk direct import dari pabrik OS Mac, Vista, Windows 7-10 Warna : MERAH, ABU2, BIRU, GOLD, HITAM Mohon no complaint Kualitas - Baik bgt. Compatible Windows 7-10, Vista, Mac Desktop PC, Laptop,Notebook 5 million clicks", 31000));
        items.add(new ItemDomain("Mouse NYK G07", "nyk_g07", "NYK G-07 Mouse adalah mouse gaming yang memiliki 4 level DPI (800/1200/1600/2400). Mouse ini juga dilengkapi fitur Rapid Fire Button. Rapid Fire Button berfungsi untuk melakukan double click hanya dengan satu klik. Mouse ini juga memiliki desain yang ergonomis sehingga sangat nyaman saat digenggam.", 75000));
        items.add(new ItemDomain("Kamera CCTV 5MP", "cctv_5mp", "Camera outdoor spc 5mp Body plastik berkualitas Garansi Resmi 1 tahun", 195000));

        recyclerViewPopularList.setAdapter(new PopularAdapter(items));
    }
}
