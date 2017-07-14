package com.toong.recyclerviewwithemptyandretry.model;

/**
 * Created by framgia on 04/07/2017.
 */

public class Item {
    private String title;
    private String message;

    public Item(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
