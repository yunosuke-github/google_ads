package com.example.google_ads_api.service;

import com.example.google_ads_api.consts.Deleted;
import com.example.google_ads_api.domain.master.Campaign;
import com.example.google_ads_api.dto.RequestDto;
import com.example.google_ads_api.dto.ResponseDto;
import com.example.google_ads_api.dto.requests.RequestCampaign;
import com.example.google_ads_api.repository.master.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

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
        ResponseDto responseDto = new ResponseDto();
        Campaign campaign = campaignRepository.findByIdAndDeleted(
                request.getCampaignId(), Deleted.ACTIVE.getId());
        return campaign;
    }

    private void validate(RequestCampaign request) {
        // account_idが存在するか
        // campaign_id
    }

}
