package com.toong.recyclerviewwithemptyandretry.widget;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import com.toong.recyclerviewwithemptyandretry.base.NetworkState;

public abstract class EndlessRecyclerViewScrollListener extends RecyclerView.OnScrollListener {
    private NetworkState networkState;
    private int visibleThreshold = 5;

    private RecyclerView.LayoutManager layoutManager;

    public EndlessRecyclerViewScrollListener(RecyclerView.LayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    @Override
    public void onScrolled(RecyclerView view, int dx, int dy) {
        if (!(networkState instanceof NetworkState.Success)) {
            return;
        }
        int lastVisibleItemPosition = 0;
        int totalItemCount = layoutManager.getItemCount();
        if (layoutManager instanceof StaggeredGridLayoutManager) {
            int[] lastVisibleItemPositions =
                    ((StaggeredGridLayoutManager) layoutManager).findLastVisibleItemPositions(null);
            lastVisibleItemPosition = getLastVisibleItemInStaggeredGrid(lastVisibleItemPositions);
        } else if (layoutManager instanceof GridLayoutManager) {
            lastVisibleItemPosition =
                    ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
        } else if (layoutManager instanceof LinearLayoutManager) {
            lastVisibleItemPosition =
                    ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
        }

        if (lastVisibleItemPosition + visibleThreshold > totalItemCount) {
            onLoadMore();
        }
    }

    private int getLastVisibleItemInStaggeredGrid(int[] lastVisibleItemPositions) {
        int maxSize = 0;
        for (int i = 0; i < lastVisibleItemPositions.length; i++) {
            if (i == 0) {
                maxSize = lastVisibleItemPositions[i];
            } else if (lastVisibleItemPositions[i] > maxSize) {
                maxSize = lastVisibleItemPositions[i];
            }
        }
        return maxSize;
    }

    public void setNetworkState(NetworkState networkState) {
        this.networkState = networkState;
    }

    public abstract void onLoadMore();
}