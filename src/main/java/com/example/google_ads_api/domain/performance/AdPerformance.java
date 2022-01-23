package com.example.google_ads_api.domain.performance;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "ad_performances")
public class AdPerformance implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private Long accountId;
    private Long campaignId;
    private Long adGroupId;
    private Long adId;
    private Long impressions;
    private Long clicks;
    private Long conversions;
    private Long cost;

}
