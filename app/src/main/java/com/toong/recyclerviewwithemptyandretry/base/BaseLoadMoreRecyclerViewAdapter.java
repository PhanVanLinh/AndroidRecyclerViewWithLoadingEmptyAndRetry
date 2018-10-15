package com.toong.recyclerviewwithemptyandretry.base;

import android.view.View;
import com.toong.recyclerviewwithemptyandretry.widget.NetworkStateLayout;

public abstract class BaseLoadMoreRecyclerViewAdapter<T> extends BaseRecyclerViewAdapter<T> {
    protected NetworkState networkState;

    protected abstract boolean hasNetworkStateRow(NetworkState networkState);

    @Override
    public int getItemCount() {
        return super.getItemCount() + (hasNetworkStateRow(networkState) ? 1 : 0);
    }

    public void setNetworkState(NetworkState newNetworkState) {
        boolean hadNetworkStateRow = hasNetworkStateRow(this.networkState);
        boolean hasNetworkStateRow = hasNetworkStateRow(newNetworkState);

        this.networkState = newNetworkState;
        if (hasNetworkStateRow && hadNetworkStateRow) {
            notifyItemChanged(getItemCount());
            return;
        }
        if (hadNetworkStateRow) {
            notifyItemRemoved(getItemCount());
            return;
        }
        if (hasNetworkStateRow) {
            notifyItemInserted(getItemCount());
        }
    }

    protected static abstract class BaseNetworkStateViewHolder
            extends BaseViewHolder<NetworkState> {
        protected NetworkStateLayout networkStateLayout;

        public BaseNetworkStateViewHolder(View itemView) {
            super(itemView);
            networkStateLayout = (NetworkStateLayout) itemView;
        }
    }
}