package com.example.google_ads_api.service;

import com.example.google_ads_api.consts.Deleted;
import com.example.google_ads_api.domain.master.Campaign;
import com.example.google_ads_api.dto.ResponseDto;
import com.example.google_ads_api.dto.requests.RequestCampaign;
import com.example.google_ads_api.repository.master.CampaignRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
public class CampaignServiceTest {

    private AutoCloseable closeable;

    @InjectMocks
    private CampaignService campaignService;

    @Mock
    private CampaignRepository campaignRepository;

    @Before
    public void setUp() {
//        closeable = MockitoAnnotations.openMocks(this);
    }

    @After
    public void tearDown() throws Exception {
//        closeable.close();
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
