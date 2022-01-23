package com.example.google_ads_api.consts;

public enum AdType {
    IMAGE_AD(1),
    TEXT_AD(2),
    GMAIL_AD(3);

    private int id;

    private AdType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
