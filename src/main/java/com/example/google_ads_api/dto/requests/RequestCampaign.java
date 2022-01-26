package com.example.google_ads_api.dto.requests;

import lombok.Data;

import java.util.List;

@Data
public class RequestCampaign {

    private Long accountId;

    private Long campaignId;

    private List<Long> campaignIds;

    private String name;

    private Integer type;

    private Long budget;

    private Integer status;

}
