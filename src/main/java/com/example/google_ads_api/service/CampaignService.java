package com.example.google_ads_api.service;

import com.example.google_ads_api.consts.Deleted;
import com.example.google_ads_api.domain.master.Campaign;
import com.example.google_ads_api.dto.requests.RequestCampaign;
import com.example.google_ads_api.repository.master.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampaignService {

    @Autowired
    private CampaignRepository campaignRepository;

    /**
     *
     * @param campaignId
     * @return
     */
    public Campaign get(Long campaignId) {
        Campaign campaign = campaignRepository.findByIdAndDeleted(campaignId, Deleted.ACTIVE.getId());
        return campaign;
    }

    /**
     *
     * @param accountId
     * @param campaignIds
     * @return
     */
    public List<Campaign> getList(Long accountId, List<Long> campaignIds) {
        List<Campaign> campaigns;
        if (campaignIds != null) {
            campaigns = campaignRepository.findByIdInAndDeleted(campaignIds, Deleted.ACTIVE.getId());
        } else {
            campaigns = campaignRepository.findByAccountIdAndDeleted(accountId, Deleted.ACTIVE.getId());
        }
        return campaigns;
    }

    private void validate(RequestCampaign request) {
        // account_idが存在するか
        // campaign_id
    }

}
