package com.toong.recyclerviewwithemptyandretry.model;

public class UserItem implements RecyclerViewItem {
    private String name;

    public UserItem(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
