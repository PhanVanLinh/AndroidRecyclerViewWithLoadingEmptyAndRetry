package com.toong.recyclerviewwithemptyandretry.adapter;

import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.toong.recyclerviewwithemptyandretry.R;
import com.toong.recyclerviewwithemptyandretry.base.BaseLoadMoreRecyclerViewAdapter;
import com.toong.recyclerviewwithemptyandretry.base.BaseViewHolder;
import com.toong.recyclerviewwithemptyandretry.base.NetworkState;
import com.toong.recyclerviewwithemptyandretry.model.UserItem;

public class ContactAdapter extends BaseLoadMoreRecyclerViewAdapter<UserItem> {

    @IntDef({ ViewType.CONTACT, ViewType.NETWORK_STATE })
    public @interface ViewType {
        int CONTACT = 1;
        int NETWORK_STATE = 2;
    }

    @Override
    public int getItemViewType(int position) {
        if ((position == getItemCount() - 1) && hasNetworkStateRow(networkState)) {
            return ViewType.NETWORK_STATE;
        }
        return ViewType.CONTACT;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case ViewType.CONTACT:
                return ContactViewHolder.create(parent);
            case ViewType.NETWORK_STATE:
                return NetworkStateViewHolder.create(parent);
        }
        throw new RuntimeException("Invalid view type");
    }

    @Override
    protected boolean hasNetworkStateRow(NetworkState networkState) {
        return networkState != null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    static class ContactViewHolder extends BaseViewHolder {

        private ContactViewHolder(View itemView) {
            super(itemView);
        }

        static ContactViewHolder create(ViewGroup parent) {
            return new ContactViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_network_state, parent, false));
        }

        @Override
        public void bind(Object item) {

        }
    }

    static class NetworkStateViewHolder extends BaseNetworkStateViewHolder {

        NetworkStateViewHolder(View itemView) {
            super(itemView);
        }

        static NetworkStateViewHolder create(ViewGroup parent) {
            return new NetworkStateViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_network_state, parent, false));
        }

        @Override
        public void bind(NetworkState item) {
            if (item instanceof NetworkState.Loading) {
                networkStateLayout.showLoadingView();
            }
            if (item instanceof NetworkState.Failed) {
                networkStateLayout.showFailView();
            }
            if (item instanceof NetworkState.Complete) {
                networkStateLayout.showCompleteView();
            }
        }
    }
}
