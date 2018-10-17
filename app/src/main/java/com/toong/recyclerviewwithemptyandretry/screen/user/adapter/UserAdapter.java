package com.toong.recyclerviewwithemptyandretry.screen.user.adapter;

import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.toong.recyclerviewwithemptyandretry.R;
import com.toong.recyclerviewwithemptyandretry.base.BaseLoadMoreRecyclerViewAdapter;
import com.toong.recyclerviewwithemptyandretry.base.BaseViewHolder;
import com.toong.recyclerviewwithemptyandretry.base.NetworkState;
import com.toong.recyclerviewwithemptyandretry.model.UserItem;
import com.toong.recyclerviewwithemptyandretry.screen.contact.adapter.ContactAdapter;

public class UserAdapter extends BaseLoadMoreRecyclerViewAdapter<UserItem> {
    @IntDef({ ViewType.USER, ViewType.NETWORK_STATE })
    public @interface ViewType {
        int USER = 1;
        int NETWORK_STATE = 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (isNetworkStateViewType(position)) {
            return ContactAdapter.ViewType.NETWORK_STATE;
        }
        return ContactAdapter.ViewType.CONTACT;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case ViewType.USER:
                return UserViewHolder.create(parent);
            case ViewType.NETWORK_STATE:
                return NetworkStateViewHolder.create(parent);
        }
        throw new RuntimeException("Invalid view type");
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        if (holder instanceof UserViewHolder) {
            ((UserViewHolder) holder).bind(getItem(position));
        }
        if (holder instanceof NetworkStateViewHolder) {
            ((NetworkStateViewHolder) holder).bind(networkState);
        }
    }

    static class UserViewHolder extends BaseViewHolder<UserItem> implements View.OnClickListener {
        private TextView tvName;

        static UserViewHolder create(ViewGroup parent) {
            return new UserViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_user, parent, false));
        }

        UserViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.text_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void bind(UserItem item) {
            super.bind(item);
            tvName.setText(item.getName());
        }

        @Override
        public void onClick(View v) {

        }
    }

    static class NetworkStateViewHolder extends BaseNetworkStateViewHolder {
        NetworkStateViewHolder(View itemView) {
            super(itemView);
        }

        static NetworkStateViewHolder create(ViewGroup parent) {
            return new NetworkStateViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_network_state_user, parent, false));
        }

        @Override
        public void bind(NetworkState item) {
            super.bind(item);
        }
    }
}