package com.toong.recyclerviewwithemptyandretry.screen.contact.adapter;

import com.toong.recyclerviewwithemptyandretry.model.RecyclerViewItem;

public class SectionItem implements RecyclerViewItem {
    private String title;

    public SectionItem(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
