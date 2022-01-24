package com.example.google_ads_api.service;

import com.example.google_ads_api.consts.Deleted;
import com.example.google_ads_api.domain.master.Campaign;
import com.example.google_ads_api.dto.requests.RequestCampaign;
import com.example.google_ads_api.repository.master.CampaignRepository;
import com.example.google_ads_api.service.CampaignService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@SpringBootTest
@ExtendWith(SpringExtension.class)
public class CampaignServiceTests {

    @InjectMocks
    private CampaignService campaignService;

    @Mock
    private CampaignRepository campaignRepository;

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() throws Exception {
    }

    @Test
    public void getCampaign() {
        RequestCampaign requestCampaign = new RequestCampaign();
        requestCampaign.setAccountId(1L);
        requestCampaign.setCampaignId(1L);
        Campaign campaign = new Campaign();
        campaign.setAccountId(1L);
        campaign.setId(1L);
        when(campaignRepository.findByIdAndDeleted(campaign.getId(), Deleted.ACTIVE.getId())).thenReturn(campaign);

        Campaign actual = campaignService.get(requestCampaign);
        assertThat(actual.getAccountId()).isEqualTo(1L);
    }
}
