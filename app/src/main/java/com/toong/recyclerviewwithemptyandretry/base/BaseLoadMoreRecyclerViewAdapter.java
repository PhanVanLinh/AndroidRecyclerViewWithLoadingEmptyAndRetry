package com.toong.recyclerviewwithemptyandretry.base;

import android.view.View;
import com.toong.recyclerviewwithemptyandretry.widget.NetworkStateLayout;

public abstract class BaseLoadMoreRecyclerViewAdapter<T> extends BaseRecyclerViewAdapter<T> {
    protected NetworkState networkState = null;

    @Override
    public int getItemCount() {
        return super.getItemCount() + (isShowNetworkStateRow(networkState) ? 1 : 0);
    }

    protected boolean isNetworkStateViewType(int position) {
        return position == getNetworkStatePosition() && isShowNetworkStateRow(networkState);
    }

    protected boolean isShowNetworkStateRow(NetworkState networkState) {
        return networkState != null;
    }

    protected int getNetworkStatePosition() {
        return getItemCount() - 1;
    }

    public void setNetworkState(NetworkState newNetworkState) {
        boolean hadNetworkStateRow = isShowNetworkStateRow(this.networkState);
        boolean hasNetworkStateRow = isShowNetworkStateRow(newNetworkState);

        this.networkState = newNetworkState;
        if (hasNetworkStateRow && hadNetworkStateRow) {
            notifyItemChanged(getNetworkStatePosition());
            return;
        }
        if (hadNetworkStateRow) {
            notifyItemRemoved(getNetworkStatePosition());
            return;
        }
        if (hasNetworkStateRow) {
            notifyItemInserted(getNetworkStatePosition());
        }
    }

    protected static abstract class BaseNetworkStateViewHolder
            extends BaseViewHolder<NetworkState> {
        protected NetworkStateLayout networkStateLayout;

        public BaseNetworkStateViewHolder(View itemView) {
            super(itemView);
            networkStateLayout = (NetworkStateLayout) itemView;
        }

        @Override
        public void bind(NetworkState item) {
            super.bind(item);
            networkStateLayout.setNetworkState(item);
        }
    }
}