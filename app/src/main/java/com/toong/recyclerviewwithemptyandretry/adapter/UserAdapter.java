package com.toong.recyclerviewwithemptyandretry.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.toong.recyclerviewwithemptyandretry.R;
import com.toong.recyclerviewwithemptyandretry.base.BaseRecyclerViewAdapter;
import com.toong.recyclerviewwithemptyandretry.model.UserItem;

public class UserAdapter extends BaseRecyclerViewAdapter<UserItem> {

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ItemViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            UserItem item = getItem(position);
            ((ItemViewHolder) holder).tvName.setText(item.getName());
        }
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvName;

        static ItemViewHolder create(ViewGroup parent) {
            return new ItemViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_user, parent, false));
        }

        ItemViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.text_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}