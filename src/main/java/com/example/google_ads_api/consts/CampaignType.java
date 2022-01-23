package com.example.google_ads_api.consts;

public enum CampaignType {
    SALES(1),
    LEADS(2),
    WEBSITE_TRAFFIC(3),
    BRAND_AWARENESS_AND_REACH(4),
    APP_DOWNLOADS(5),
    LOCAL_STORE_VISITS(6);

    private int id;

    private CampaignType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
