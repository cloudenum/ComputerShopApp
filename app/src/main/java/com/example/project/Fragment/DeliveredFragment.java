package com.example.project.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.project.Adapter.DeliveringAdapter;
import com.example.project.Helper.DeliverManagement;
import com.example.project.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DeliveredFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DeliveredFragment extends Fragment {

    private RecyclerView recyclerViewList1;
    private TextView txtEmptyCart1;
    private RelativeLayout rlCartList1;

    private DeliverManagement deliverManagement1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_delivered, container, false);

        recyclerViewList1 = view.findViewById(R.id.recyclerview1);
        txtEmptyCart1 = view.findViewById(R.id.txtEmptyCart2);
        rlCartList1 = view.findViewById(R.id.rlCartList2);

        deliverManagement1 = new DeliverManagement(view.getContext());

        initList(view.getContext());

        Log.d("TAG", "onCreateView: " + deliverManagement1.getDeliveredList().size());

        return view;
    }

    private void initList(Context context) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerViewList1.setLayoutManager(linearLayoutManager);
        DeliveringAdapter adapter = new DeliveringAdapter(deliverManagement1.getDeliveredList(), context);

        recyclerViewList1.setAdapter(adapter);
        if (deliverManagement1.getDeliveredList().isEmpty()) {
            txtEmptyCart1.setVisibility(View.VISIBLE);
            rlCartList1.setVisibility(View.GONE);
        } else {
            txtEmptyCart1.setVisibility(View.GONE);
            rlCartList1.setVisibility(View.VISIBLE);
        }
    }
}