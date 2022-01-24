package com.example.google_ads_api.repository.master;

import com.example.google_ads_api.domain.master.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CampaignRepository extends JpaRepository<Campaign, Long> {

    public Campaign findByIdAndDeleted(Long id, int deleted);

    public List<Campaign> findByIdInAndDeleted(List<Long> ids, int deleted);

    public List<Campaign> findByAccountIdAndDeleted(Long accountId, int deleted);

}
