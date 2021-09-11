package com.example.project.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.Activity.CountdownPaymentActivity;
import com.example.project.Adapter.CartListAdapter;
import com.example.project.Helper.ManagementCart;
import com.example.project.Interface.ChangeNumberItemsListener;
import com.example.project.R;

public class CartListFragment extends Fragment {
    private RecyclerView recyclerViewList;
    private ManagementCart managementCart;
    private TextView txtTotalFee, txtTax, txtDelivery, txtTotalPrice, txtEmptyCart;
    private LinearLayout llCartList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cart_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        managementCart = new ManagementCart(view.getContext());
        recyclerViewList = view.findViewById(R.id.recyclerview);
        txtTotalFee = view.findViewById(R.id.txtTotalFee);
        txtTax = view.findViewById(R.id.txtTax);
        txtDelivery = view.findViewById(R.id.txtDelivery);
        txtTotalPrice = view.findViewById(R.id.txtTotalPrice);
        txtEmptyCart = view.findViewById(R.id.txtEmptyCart);
        llCartList = view.findViewById(R.id.llCartList);
        Button btnCheckout = view.findViewById(R.id.btnCheckout);

        btnCheckout.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), CountdownPaymentActivity.class);
            v.getContext().startActivity(intent);
        });

        initList(view.getContext());
        calculateCard();
    }

    private void initList(Context context) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        CartListAdapter adapter = new CartListAdapter(managementCart.getListCard(), context, new ChangeNumberItemsListener() {
            @Override
            public void changed() {
                calculateCard();
            }
        });

        recyclerViewList.setAdapter(adapter);
        if (managementCart.getListCard().isEmpty()) {
            txtEmptyCart.setVisibility(View.VISIBLE);
            llCartList.setVisibility(View.GONE);
        } else {
            txtEmptyCart.setVisibility(View.GONE);
            llCartList.setVisibility(View.VISIBLE);
        }
    }

    private void calculateCard() {
        float percentTax = 0.1f;
        int delivery = 6000;

        int itemTotal = managementCart.getTotalPrice();
        int tax = (int) Math.ceil((itemTotal * percentTax));
        int total = itemTotal + tax + delivery;


        txtTotalFee.setText("Rp" + itemTotal);
        txtTax.setText("Rp" + tax);
        txtDelivery.setText("Rp" + delivery);
        txtTotalPrice.setText("Rp" + total);
    }
}