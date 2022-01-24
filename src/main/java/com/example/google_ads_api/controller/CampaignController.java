package com.example.google_ads_api.controller;

import com.example.google_ads_api.domain.master.Campaign;
import com.example.google_ads_api.dto.ResponseDto;
import com.example.google_ads_api.dto.requests.RequestCampaign;
import com.example.google_ads_api.service.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CampaignController {

    @Autowired
    CampaignService campaignService;

    @RequestMapping(value = "/campaign", method = RequestMethod.GET)
    public ResponseDto getCampaign(@RequestParam Long campaignId) {
        ResponseDto response = new ResponseDto();
        Campaign campaign = campaignService.get(campaignId);
        response.setData(campaign);
        response.setCode(HttpStatus.OK.value());
        return response;
    }

    @RequestMapping(value = "/campaign/list", method = RequestMethod.GET)
    public ResponseDto getCampaign(@RequestParam Long accountId, @RequestParam List<Long> campaignIds) {
        ResponseDto response = new ResponseDto();
        List<Campaign> campaigns = campaignService.getList(accountId, campaignIds);
        response.setData(campaigns);
        response.setCode(HttpStatus.OK.value());
        return response;
    }

    @RequestMapping(value = "/campaign/add", method = RequestMethod.POST)
    public ResponseDto addCampaign(@RequestBody RequestCampaign requestCampaign) {
        ResponseDto response = new ResponseDto();
        return response;
    }

}
