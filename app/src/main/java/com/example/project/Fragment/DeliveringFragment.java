package com.example.project.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.project.Adapter.DeliveringAdapter;
import com.example.project.Domain.ItemDomain;
import com.example.project.Helper.DeliverManagement;
import com.example.project.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DeliveringFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DeliveringFragment extends Fragment {
    private RecyclerView recyclerViewList;
    private TextView txtTotalPrice, txtEmptyCart;
    private RelativeLayout rlCartList;
    Button btnReceived;

    private DeliverManagement deliverManagement;
    FragmentTransaction ft;

    ArrayList<ItemDomain> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_delivering, container, false);

        FragmentManager fm = getParentFragmentManager();
        ft = fm.beginTransaction();

       recyclerViewList = view.findViewById(R.id.recyclerview1);
       txtEmptyCart = view.findViewById(R.id.txtEmptyCart1);
       txtTotalPrice = view.findViewById(R.id.txtTotalPrice1);
       rlCartList = view.findViewById(R.id.rlCartList);
       btnReceived = view.findViewById(R.id.btnDiterima);

       deliverManagement = new DeliverManagement(view.getContext());

       initList(view.getContext());
       calculateCard();

        deliverManagement.insertToDelivered(list);
        btnReceived.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<ItemDomain> temp = new ArrayList<>();
                list = deliverManagement.getDeliveredList();
                temp = deliverManagement.getDeliveringList();
                list.addAll(temp);

                deliverManagement.insertToDelivered(list);

                Log.d("TAG", "onClick: " + list.size());

                deliverManagement.RemoveList();

                DeliveredFragment deliveredFragment = new DeliveredFragment();

                ft.replace(R.id.con, deliveredFragment, DeliveredFragment.class.getSimpleName()).commit();
            }
        });

       return view;
    }

    private void initList(Context context) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        DeliveringAdapter adapter = new DeliveringAdapter(deliverManagement.getDeliveringList(), context);

        recyclerViewList.setAdapter(adapter);
        if (deliverManagement.getDeliveringList().isEmpty()) {
            txtEmptyCart.setVisibility(View.VISIBLE);
            rlCartList.setVisibility(View.GONE);
        } else {
            txtEmptyCart.setVisibility(View.GONE);
            rlCartList.setVisibility(View.VISIBLE);
        }
    }

    private void calculateCard() {
        int total = deliverManagement.getTotalPrice();

        txtTotalPrice.setText("Rp" + total);
    }
}