package com.toong.recyclerviewwithemptyandretry.screen.contact.adapter;

import com.toong.recyclerviewwithemptyandretry.model.RecyclerViewItem;

public class ContactItem implements RecyclerViewItem {
    private String name;

    public ContactItem(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
