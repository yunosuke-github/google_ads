package com.example.google_ads_api.service.master;

import com.example.google_ads_api.consts.Deleted;
import com.example.google_ads_api.consts.ErrorCode;
import com.example.google_ads_api.domain.master.Campaign;
import com.example.google_ads_api.dto.requests.RequestCampaign;
import com.example.google_ads_api.exception.InvalidParamException;
import com.example.google_ads_api.exception.NoIDSpecifiedException;
import com.example.google_ads_api.exception.NotFoundException;
import com.example.google_ads_api.repository.master.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
        Campaign campaign = campaignRepository.findByIdAndDeleted(request.getCampaignId(), Deleted.NO.getId());
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
            campaigns = campaignRepository.findByIdInAndDeleted(request.getCampaignIds(), Deleted.NO.getId());
        } else {
            if (request.getAccountId() == null) {
                throw new NoIDSpecifiedException(ErrorCode.E005.getMessage());
            }
            campaigns = campaignRepository.findByAccountIdAndDeleted(request.getAccountId(), Deleted.NO.getId());
        }
        return campaigns;
    }

    /**
     *
     * @param request
     */
    public Campaign save(RequestCampaign request) {
        if (request.getAccountId() == null) {
            throw new NoIDSpecifiedException(ErrorCode.E005.getMessage());
        }
        Campaign campaign;
        if (request.getCampaignId() == null) {
            campaign = Campaign.requestMapper(request);
        } else {
            campaign = campaignRepository.findByIdAndDeleted(request.getCampaignId(), Deleted.NO.getId());
            if (campaign == null) {
                throw new NotFoundException(ErrorCode.E004.getMessage());
            }
            campaign.setUpdatedAt(new Date());
        }
        campaign = campaignRepository.save(campaign);
        return campaign;
    }

    /**
     *
     * @param request
     */
    public void delete(RequestCampaign request) {
        if (request.getAccountId() == null || request.getCampaignId() == null) {
            throw new NoIDSpecifiedException(ErrorCode.E005.getMessage());
        }
        Campaign campaign = campaignRepository.findByIdAndDeleted(request.getCampaignId(), Deleted.NO.getId());
        if (campaign == null) {
            throw new NotFoundException(ErrorCode.E004.getMessage());
        }
        campaign.setDeleted(Deleted.YES.getId());
        campaign.setUpdatedAt(new Date());
        campaignRepository.save(campaign);
    }

    private void validate(RequestCampaign request) {
        // account_idが存在するか
        // campaign_id
    }

}
