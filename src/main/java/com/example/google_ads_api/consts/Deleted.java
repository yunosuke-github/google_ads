package com.example.google_ads_api.consts;

public enum Deleted {
    ACTIVE(0),
    DELETED(1);

    private int id;

    private Deleted(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
