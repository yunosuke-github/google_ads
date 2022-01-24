package com.example.google_ads_api.controller;

import com.example.google_ads_api.domain.master.Campaign;
import com.example.google_ads_api.dto.ResponseDto;
import com.example.google_ads_api.dto.requests.RequestCampaign;
import com.example.google_ads_api.service.master.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CampaignController {

    @Autowired
    CampaignService campaignService;

    @RequestMapping(value = "/campaign", method = RequestMethod.GET)
    public ResponseDto getCampaign(RequestCampaign request) {
        ResponseDto response = new ResponseDto();
        Campaign campaign = campaignService.get(request);
        response.setData(campaign);
        response.setCode(HttpStatus.OK.value());
        return response;
    }

    @RequestMapping(value = "/campaign/list", method = RequestMethod.GET)
    public ResponseDto getCampaignList(RequestCampaign request) {
        ResponseDto response = new ResponseDto();
        List<Campaign> campaigns = campaignService.getList(request);
        response.setData(campaigns);
        response.setCode(HttpStatus.OK.value());
        return response;
    }

    @RequestMapping(value = "/campaign", method = RequestMethod.POST)
    public ResponseDto addCampaign(RequestCampaign requestCampaign) {
        ResponseDto response = new ResponseDto();
        // TODO: Add Logic
        return response;
    }

}
