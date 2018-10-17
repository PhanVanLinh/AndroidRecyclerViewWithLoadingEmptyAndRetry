package com.toong.recyclerviewwithemptyandretry.screen.book.adapter;

import com.toong.recyclerviewwithemptyandretry.model.RecyclerViewItem;

public class BookItem implements RecyclerViewItem {
    private String name;

    public BookItem(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
