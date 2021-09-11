package com.example.project.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.Domain.ChatDomain;
import com.example.project.R;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // The items to display in your RecyclerView
    private final List<ChatDomain> items;
    private Context mContext;

    private final int DATE = 0, YOU = 1, ME = 2;

    // Provide a suitable constructor (depends on the kind of dataset)
    public ChatAdapter(Context context, List<ChatDomain> items) {
        this.mContext = context;
        this.items = items;
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return this.items.size();
    }

    @Override
    public int getItemViewType(int position) {
        //More to come
        switch (items.get(position).getType()) {
            case "0":
                return DATE;
            case "1":
                return YOU;
            case "2":
                return ME;
        }
        return -1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case DATE:
                View v1 = inflater.inflate(R.layout.viewholder_chat_date, parent, false);
                viewHolder = new HolderDate(v1);
                break;
            case YOU:
                View v2 = inflater.inflate(R.layout.viewholder_chat_you, parent, false);
                viewHolder = new HolderYou(v2);
                break;
            default:
                View v = inflater.inflate(R.layout.viewholder_chat_me, parent, false);
                viewHolder = new HolderMe(v);
                break;
        }
        return viewHolder;
    }
    public void addItem(List<ChatDomain> item) {
        items.addAll(item);
        notifyDataSetChanged();
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        switch (viewHolder.getItemViewType()) {
            case DATE:
                HolderDate vh1 = (HolderDate) viewHolder;
                configureViewHolder1(vh1, position);
                break;
            case YOU:
                HolderYou vh2 = (HolderYou) viewHolder;
                configureViewHolder2(vh2, position);
                break;
            default:
                HolderMe vh = (HolderMe) viewHolder;
                configureViewHolder3(vh, position);
                break;
        }
    }

    private void configureViewHolder3(HolderMe vh1, int position) {
        vh1.getTime().setText(items.get(position).getTime());
        vh1.getChatText().setText(items.get(position).getText());
    }

    private void configureViewHolder2(HolderYou vh1, int position) {
        vh1.getTime().setText(items.get(position).getTime());
        vh1.getChatText().setText(items.get(position).getText());
    }
    private void configureViewHolder1(HolderDate vh1, int position) {
        vh1.getDate().setText(items.get(position).getText());
    }

    private static class HolderMe extends RecyclerView.ViewHolder {

        private TextView time, chatText;

        public HolderMe(View v) {
            super(v);
            time = v.findViewById(R.id.tv_time);
            chatText = v.findViewById(R.id.tv_chat_text);
        }

        public TextView getTime() {
            return time;
        }

        public void setTime(TextView time) {
            this.time = time;
        }

        public TextView getChatText() {
            return chatText;
        }

        public void setChatText(TextView chatText) {
            this.chatText = chatText;
        }
    }

    private static class HolderDate extends RecyclerView.ViewHolder {

        private TextView date;

        public HolderDate(View v) {
            super(v);
            date = v.findViewById(R.id.tv_date);
        }

        public TextView getDate() {
            return date;
        }

        public void setDate(TextView date) {
            this.date = date;
        }
    }

    private static class HolderYou extends RecyclerView.ViewHolder {

        private TextView time, chatText;

        public HolderYou(View v) {
            super(v);
            time = v.findViewById(R.id.tv_time);
            chatText = v.findViewById(R.id.tv_chat_text);
        }

        public TextView getTime() {
            return time;
        }

        public void setTime(TextView time) {
            this.time = time;
        }

        public TextView getChatText() {
            return chatText;
        }

        public void setChatText(TextView chatText) {
            this.chatText = chatText;
        }
    }
}
