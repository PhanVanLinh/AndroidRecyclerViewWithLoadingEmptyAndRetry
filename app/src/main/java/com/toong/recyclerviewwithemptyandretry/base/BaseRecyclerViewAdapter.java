package com.toong.recyclerviewwithemptyandretry.base;

import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseRecyclerViewAdapter<T>
        extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    protected List<T> items;

    protected BaseRecyclerViewAdapter() {
        items = new ArrayList<>();
    }

    public void add(List<T> items) {
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    public void set(List<T> dataList) {
        List<T> clone = new ArrayList<>(dataList);
        items.clear();
        items.addAll(clone);
        notifyDataSetChanged();
    }

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    public T getItem(int position) {
        return items.get(position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
