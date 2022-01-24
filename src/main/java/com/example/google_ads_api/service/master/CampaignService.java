package com.example.google_ads_api.service.master;

import com.example.google_ads_api.consts.Deleted;
import com.example.google_ads_api.consts.ErrorCode;
import com.example.google_ads_api.domain.master.Campaign;
import com.example.google_ads_api.dto.requests.RequestCampaign;
import com.example.google_ads_api.exception.InvalidParamException;
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
     * @param request
     * @return
     */
    public Campaign get(RequestCampaign request) {
        Campaign campaign = campaignRepository.findByIdAndDeleted(request.getCampaignId(), Deleted.ACTIVE.getId());
        return campaign;
    }

    /**
     *
     * @param request
     * @return
     */
    public List<Campaign> getList(RequestCampaign request) {
        List<Campaign> campaigns;
        if (request.getCampaignIds() != null) {
            campaigns = campaignRepository.findByIdInAndDeleted(request.getCampaignIds(), Deleted.ACTIVE.getId());
        } else {
            if (request.getAccountId() == null) {
                throw new InvalidParamException(ErrorCode.E003.getMessage());
            }
            campaigns = campaignRepository.findByAccountIdAndDeleted(request.getAccountId(), Deleted.ACTIVE.getId());
        }
        return campaigns;
    }

    private void validate(RequestCampaign request) {
        // account_idが存在するか
        // campaign_id
    }

}
