package com.example.google_ads_api.consts;

public enum Status {
    DISABLE(0),
    REVIEW(1),
    ENABLE(2)
    ;

    private int id;

    private Status(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
