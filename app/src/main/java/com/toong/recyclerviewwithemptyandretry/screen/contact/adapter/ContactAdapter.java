package com.toong.recyclerviewwithemptyandretry.screen.contact.adapter;

import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
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
        if ((position == getItemCount() - 1) && isShowNetworkStateRow(networkState)) {
            return ViewType.NETWORK_STATE;
        }
        return ViewType.CONTACT;
    }

    @Override
    protected int getNetworkStatePosition() {
        return 0;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case ViewType.CONTACT:
                return ContactViewHolder.create(parent);
            case ViewType.NETWORK_STATE:
                return NetworkStateViewHolder.create(parent);
        }
        throw new RuntimeException("Invalid view type");
    }

    @Override
    protected boolean isShowNetworkStateRow(NetworkState networkState) {
        return networkState != null;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        if (holder instanceof ContactViewHolder) {
            holder.bind(getItem(position));
        }
        if (holder instanceof NetworkStateViewHolder) {
            ((NetworkStateViewHolder) holder).bind(networkState);
        }
    }

    static class ContactViewHolder extends BaseViewHolder<ContactItem> {

        private ContactViewHolder(View itemView) {
            super(itemView);
        }

        static ContactViewHolder create(ViewGroup parent) {
            return new ContactViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_network_state_user, parent, false));
        }

        @Override
        public void bind(ContactItem item) {
            super.bind(item);
        }
    }

    static class SectionViewHolder extends BaseViewHolder<SectionItem>{

        public SectionViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void bind(SectionItem item) {
            super.bind(item);
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
    }
}
