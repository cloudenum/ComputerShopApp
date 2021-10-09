package com.example.project.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.project.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HelpDetailFragment #newInstance} factory method to
 * create an instance of this fragment.
 */
public class HelpDetailFragment extends Fragment {
    TextView txtAbout;

    String transaksi = "Untuk Pembelian Langsung bisa mendatangi toko kami di toko pusat maupun cabang.";
    String tentang_produk = "Harga produk di Andalas Computer berubah setiap waktunya mengikuti perubahan harga pasar.";
    String kesempatan_bisnis = "Seluruh lowongan pekerjaan yang sedang tersedia dapat dilihat di halaman awal.";
    String garansi = "Garansi Produk bisa diklaim ke toko langsung atau melampirkan video sebagai tanda bukti garansi.";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_help_detail, container, false);

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