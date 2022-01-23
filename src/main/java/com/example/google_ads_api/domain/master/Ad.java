package com.example.google_ads_api.domain.master;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "ads")
public class Ad implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private Long accountId;
    private Long campaignId;
    private Long adGroupId;
    private String name;
    private String type;
    private Integer status;
    private String finalLinkUrl;
    private String displayUrl;
    private String headLine;
    private String description;
    private Integer deleted;
    private Date createdAt;
    private Date updatedAt;

}
