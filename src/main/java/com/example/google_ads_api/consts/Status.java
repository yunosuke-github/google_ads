package com.example.google_ads_api.consts;

public enum Status {
    ENABLE(1),
    DISABLE(0);

    private int id;

    private Status(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
