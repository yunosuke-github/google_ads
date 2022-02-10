package com.example.google_ads_api.domain.master;

import com.example.google_ads_api.consts.Deleted;
import com.example.google_ads_api.consts.Status;
import com.example.google_ads_api.dto.requests.RequestCampaign;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "campaigns")
public class Campaign implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private Long accountId;

    private String name;

    private Integer type;

    private Long budget;

    private Integer status;

    private Integer deleted;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updatedAt;

    /**
     * Request内容をエンティティにマッピング
     *
     * @param request
     * @return
     */
    public static Campaign requestMapper(RequestCampaign request) {
        Campaign campaign = new Campaign();
        campaign.setId(request.getCampaignId());
        campaign.setAccountId(request.getAccountId());
        campaign.setName(request.getName());
        campaign.setBudget(request.getBudget());
        campaign.setType(request.getType());
        if (campaign.getId() == null) {
            campaign.setDeleted(Deleted.NO.getId());
            campaign.setCreatedAt(new Date());
            campaign.setStatus(Status.REVIEW.getId());
        } else {
            campaign.setUpdatedAt(new Date());
        }
        return campaign;
    }

}
