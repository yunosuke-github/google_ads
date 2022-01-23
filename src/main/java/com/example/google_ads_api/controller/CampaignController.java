package com.example.google_ads_api.controller;

import com.example.google_ads_api.domain.master.Campaign;
import com.example.google_ads_api.dto.ResponseDto;
import com.example.google_ads_api.dto.requests.RequestCampaign;
import com.example.google_ads_api.service.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class CampaignController {

    @Autowired
    CampaignService campaignService;

    @RequestMapping(value = "/campaign", method = RequestMethod.POST)
    public ResponseDto getCampaign(@RequestBody RequestCampaign requestCampaign) {
        ResponseDto response = new ResponseDto();
        Campaign campaign = campaignService.get(requestCampaign);
        response.setData(campaign);
        response.setCode(HttpStatus.OK.value());
        return response;
    }

}
