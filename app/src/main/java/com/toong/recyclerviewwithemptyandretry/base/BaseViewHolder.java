package com.toong.recyclerviewwithemptyandretry.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {
    protected T item;

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public void bind(T item) {
        this.item = item;
    }
}
