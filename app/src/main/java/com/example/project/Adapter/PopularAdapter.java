package com.example.project.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.project.Activity.ShowDetailActivity;
import com.example.project.Domain.ItemDomain;
import com.example.project.R;

import java.util.ArrayList;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder> {
    ArrayList<ItemDomain> itemDomains;

    public PopularAdapter(ArrayList<ItemDomain> itemDomains) {
        this.itemDomains = itemDomains;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_popular, parent, false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(itemDomains.get(holder.getAdapterPosition()).getTitle());
        holder.fee.setText(String.valueOf(itemDomains.get(holder.getAdapterPosition()).getPrice()));

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(itemDomains.get(holder.getAdapterPosition()).getPic(), "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);

        holder.addBtn.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), ShowDetailActivity.class);
            intent.putExtra("object", itemDomains.get(holder.getAdapterPosition()));
            holder.itemView.getContext().startActivity(intent);
        });

    }


    @Override
    public int getItemCount() {
        return itemDomains.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, fee;
        ImageView pic;
        TextView addBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            fee = itemView.findViewById(R.id.fee);
            pic = itemView.findViewById(R.id.pic);
            addBtn = itemView.findViewById(R.id.addBtn);
        }
    }
}
