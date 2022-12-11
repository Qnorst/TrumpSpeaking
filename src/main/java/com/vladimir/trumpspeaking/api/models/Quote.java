package com.vladimir.trumpspeaking.api.models;

import com.google.gson.annotations.SerializedName;

public class Quote {
    private String title;
    @SerializedName("message")
    private String text;

    public Quote() {
    }

    public Quote(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
