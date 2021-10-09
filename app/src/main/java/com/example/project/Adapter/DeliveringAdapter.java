package com.example.project.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.project.Domain.ItemDomain;
import com.example.project.Helper.DeliverManagement;
import com.example.project.R;

import java.util.ArrayList;

public class DeliveringAdapter extends RecyclerView.Adapter<DeliveringAdapter.ViewHolder> {

    private ArrayList<ItemDomain> itemDomains;
    private DeliverManagement deliverManagement;

    public DeliveringAdapter(ArrayList<ItemDomain> deliveringList, Context context) {
        this.itemDomains = deliveringList;
        deliverManagement = new DeliverManagement(context);
    }

    @NonNull
    @Override
    public DeliveringAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_card, parent, false);

        return new DeliveringAdapter.ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        position = holder.getAdapterPosition();
        holder.title.setText(itemDomains.get(position).getTitle());
        holder.feeEachItem.setText(String.valueOf(itemDomains.get(position).getPrice()));
        holder.totalEachItem.setText(String.valueOf(itemDomains.get(position).getNumberInCart() * itemDomains.get(position).getPrice()));
        holder.num.setText("x"+String.valueOf(itemDomains.get(position).getNumberInCart()));

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(itemDomains.get(position).getPic(), "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);

        holder.minusItem.setVisibility(View.GONE);
        holder.plusItem.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return itemDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, feeEachItem;
        ImageView pic, plusItem, minusItem;
        TextView totalEachItem, num;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title2Txt);
            feeEachItem = itemView.findViewById(R.id.feeEachItem);
            pic = itemView.findViewById(R.id.picCard);
            totalEachItem = itemView.findViewById(R.id.totalEachItem);
            num = itemView.findViewById(R.id.numberItemTxt);
            plusItem = itemView.findViewById(R.id.plusCardBtn);
            minusItem = itemView.findViewById(R.id.minusCardBtn);
        }
    }

}
