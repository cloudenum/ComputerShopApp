package com.example.project.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.project.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HelpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HelpFragment extends Fragment {
    FragmentTransaction ft;

   ImageButton btnTransaksi, btnTentangProduk, btnKesempatanBisnis, btnGaransi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_help, container, false);

        FragmentManager fm = getActivity().getSupportFragmentManager();
        ft = fm.beginTransaction();

        btnTransaksi = view.findViewById(R.id.btnTransaksi);
        btnTentangProduk = view.findViewById(R.id.btnAboutProduct);
        btnKesempatanBisnis = view.findViewById(R.id.btnKesempatanBisnis);
        btnGaransi = view.findViewById(R.id.btnGaransi);

        btnGaransi.setOnClickListener(AboutDetail);
        btnKesempatanBisnis.setOnClickListener(AboutDetail);
        btnTransaksi.setOnClickListener(AboutDetail);
        btnTentangProduk.setOnClickListener(AboutDetail);

        return view;
    }

    View.OnClickListener AboutDetail = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            AboutDetailFragment aboutDetailFragment = new AboutDetailFragment();
            Bundle bundle = new Bundle();

            switch (view.getId()){
                case R.id.btnTransaksi:
                    bundle.putInt("aboutdetail", 1);
                    aboutDetailFragment.setArguments(bundle);

                    ft.replace(R.id.con1, aboutDetailFragment, AboutDetailFragment.class.getSimpleName()).commit();
                    break;

                case R.id.btnAboutProduct:
                    bundle.putInt("aboutdetail", 2);
                    aboutDetailFragment.setArguments(bundle);

                    ft.replace(R.id.con1, aboutDetailFragment, AboutDetailFragment.class.getSimpleName()).commit();
                    break;

                case R.id.btnKesempatanBisnis:
                    bundle.putInt("aboutdetail", 3);
                    aboutDetailFragment.setArguments(bundle);

                    ft.replace(R.id.con1, aboutDetailFragment, AboutDetailFragment.class.getSimpleName()).commit();
                    break;

                case R.id.btnGaransi:
                    bundle.putInt("aboutdetail", 4);
                    aboutDetailFragment.setArguments(bundle);

                    ft.replace(R.id.con1, aboutDetailFragment, AboutDetailFragment.class.getSimpleName()).commit();
                    break;
            }
        }
    };
}