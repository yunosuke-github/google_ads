package com.example.google_ads_api.service;

import com.example.google_ads_api.consts.CampaignType;
import com.example.google_ads_api.consts.Deleted;
import com.example.google_ads_api.consts.ErrorCode;
import com.example.google_ads_api.consts.Status;
import com.example.google_ads_api.domain.master.Campaign;
import com.example.google_ads_api.dto.requests.RequestCampaign;
import com.example.google_ads_api.exception.InvalidParamException;
import com.example.google_ads_api.exception.NoIDSpecifiedException;
import com.example.google_ads_api.exception.NotFoundException;
import com.example.google_ads_api.repository.master.CampaignRepository;
import com.example.google_ads_api.service.master.CampaignService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
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
    @DisplayName("[正常系] キャンペーン取得")
    public void get() {
        RequestCampaign requestCampaign = new RequestCampaign();
        requestCampaign.setAccountId(1L);
        requestCampaign.setCampaignId(1L);
        Campaign campaign = new Campaign();
        campaign.setAccountId(1L);
        campaign.setId(1L);
        when(campaignRepository.findByIdAndDeleted(requestCampaign.getCampaignId(), Deleted.NO.getId())).thenReturn(campaign);

        Campaign actual = campaignService.get(requestCampaign);
        assertThat(actual.getAccountId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("[正常系] キャンペーン一覧取得（キャンペーンIDリスト）")
    public void getListByCampaignIds() {
        RequestCampaign requestCampaign = new RequestCampaign();
        requestCampaign.setAccountId(1L);
        requestCampaign.setCampaignIds(new ArrayList<Long>(Arrays.asList(1L, 2L)));
        Campaign campaign1 = new Campaign();
        campaign1.setAccountId(1L);
        campaign1.setId(1L);
        Campaign campaign2 = new Campaign();
        campaign2.setAccountId(1L);
        campaign2.setId(1L);
        List<Campaign> campaigns = new ArrayList<Campaign>(Arrays.asList(campaign1, campaign2));
        when(campaignRepository.findByIdInAndDeleted(requestCampaign.getCampaignIds(), Deleted.NO.getId())).thenReturn(campaigns);

        List<Campaign> actual = campaignService.getList(requestCampaign);
        assertThat(actual.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("[正常系] キャンペーン一覧取得（アカウントIDのみ）")
    public void getListByAccountId() {
        RequestCampaign requestCampaign = new RequestCampaign();
        requestCampaign.setAccountId(1L);
        Campaign campaign1 = new Campaign();
        campaign1.setAccountId(1L);
        campaign1.setId(1L);
        Campaign campaign2 = new Campaign();
        campaign2.setAccountId(1L);
        campaign2.setId(1L);
        List<Campaign> campaigns = new ArrayList<Campaign>(Arrays.asList(campaign1, campaign2));
        when(campaignRepository.findByAccountIdAndDeleted(requestCampaign.getAccountId(), Deleted.NO.getId())).thenReturn(campaigns);

        List<Campaign> actual = campaignService.getList(requestCampaign);
        assertThat(actual.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("[異常系] キャンペーン一覧取得（キャンペーンIDリスト指定なし）")
    public void getListInvalidException() {
        RequestCampaign requestCampaign = new RequestCampaign();
        requestCampaign.setAccountId(1L);
        try {
            campaignService.getList(requestCampaign);
        } catch(NoIDSpecifiedException e) {
            assertThat(e.getMessage()).isEqualTo(ErrorCode.E005.getMessage());
        }
    }

    @Test
    @DisplayName("[異常系] キャンペーン一覧取得（アカウントIDなし）")
    public void getListNoIdSpecifiedException() {
        RequestCampaign requestCampaign = new RequestCampaign();
        try {
            campaignService.getList(requestCampaign);
        } catch(NoIDSpecifiedException e) {
            assertThat(e.getMessage()).isEqualTo(ErrorCode.E005.getMessage());
        }
    }

    @Test
    @DisplayName("[正常系] キャンペーンの追加")
    public void save() {
        RequestCampaign requestCampaign = new RequestCampaign();
        requestCampaign.setAccountId(1L);
        requestCampaign.setBudget(1000000L);
        requestCampaign.setName("テストキャンペーン");
        requestCampaign.setStatus(Status.ENABLE.getId());
        requestCampaign.setType(CampaignType.SALES.getId());

        Campaign campaign = new Campaign();
        campaign.setId(1L);
        campaign.setAccountId(1L);
        campaign.setBudget(1000000L);
        campaign.setName("テストキャンペーン");
        campaign.setDeleted(Deleted.NO.getId());
        campaign.setStatus(Status.ENABLE.getId());
        campaign.setType(CampaignType.SALES.getId());

        when(campaignRepository.save(any())).thenReturn(campaign);

        Campaign actual = campaignService.save(requestCampaign);
        assertThat(actual.getId()).isEqualTo(1L);
        assertThat(actual.getAccountId()).isEqualTo(requestCampaign.getAccountId());
        assertThat(actual.getBudget()).isEqualTo(requestCampaign.getBudget());
        assertThat(actual.getName()).isEqualTo(requestCampaign.getName());
        assertThat(actual.getDeleted()).isEqualTo(Deleted.NO.getId());
        assertThat(actual.getStatus()).isEqualTo(requestCampaign.getStatus());
        assertThat(actual.getType()).isEqualTo(requestCampaign.getType());
    }

    @Test
    @DisplayName("[正常系] キャンペーンの更新")
    public void saveByUpdate() {
        RequestCampaign requestCampaign = new RequestCampaign();
        requestCampaign.setAccountId(1L);
        requestCampaign.setCampaignId(1L);
        requestCampaign.setBudget(1000000L);
        requestCampaign.setName("テストキャンペーン_更新後");
        requestCampaign.setStatus(Status.ENABLE.getId());
        requestCampaign.setType(CampaignType.SALES.getId());

        Campaign currentCampaign = new Campaign();
        currentCampaign.setId(1L);
        currentCampaign.setAccountId(1L);
        currentCampaign.setBudget(1000000L);
        currentCampaign.setName("テストキャンペーン");
        currentCampaign.setDeleted(Deleted.NO.getId());
        currentCampaign.setStatus(Status.ENABLE.getId());
        currentCampaign.setType(CampaignType.SALES.getId());

        Campaign updatedCampaign = new Campaign();
        updatedCampaign.setId(1L);
        updatedCampaign.setAccountId(1L);
        updatedCampaign.setBudget(1000000L);
        updatedCampaign.setName("テストキャンペーン_更新後");
        updatedCampaign.setDeleted(Deleted.NO.getId());
        updatedCampaign.setStatus(Status.ENABLE.getId());
        updatedCampaign.setType(CampaignType.SALES.getId());

        when(campaignRepository.save(any())).thenReturn(updatedCampaign);
        when(campaignRepository.findByIdAndDeleted(1L, Deleted.NO.getId())).thenReturn(currentCampaign);

        Campaign actual = campaignService.save(requestCampaign);
        assertThat(actual.getId()).isEqualTo(1L);
        assertThat(actual.getAccountId()).isEqualTo(requestCampaign.getAccountId());
        assertThat(actual.getBudget()).isEqualTo(requestCampaign.getBudget());
        assertThat(actual.getName()).isEqualTo(requestCampaign.getName());
        assertThat(actual.getDeleted()).isEqualTo(Deleted.NO.getId());
        assertThat(actual.getStatus()).isEqualTo(requestCampaign.getStatus());
        assertThat(actual.getType()).isEqualTo(requestCampaign.getType());
    }

    @Test
    @DisplayName("[異常系] 更新対象のキャンペーンが見つからない場合")
    public void saveByUpdateCurrentCampaignNull() {
        RequestCampaign requestCampaign = new RequestCampaign();
        requestCampaign.setAccountId(1L);
        requestCampaign.setCampaignId(1L);
        requestCampaign.setBudget(1000000L);
        requestCampaign.setName("テストキャンペーン_更新後");
        requestCampaign.setStatus(Status.ENABLE.getId());
        requestCampaign.setType(CampaignType.SALES.getId());

        when(campaignRepository.findByIdAndDeleted(1L, Deleted.NO.getId())).thenReturn(null);

        try {
            Campaign actual = campaignService.save(requestCampaign);
        } catch(NotFoundException e) {
            assertThat(e.getMessage()).isEqualTo(ErrorCode.E004.getMessage());
        }
    }

}
