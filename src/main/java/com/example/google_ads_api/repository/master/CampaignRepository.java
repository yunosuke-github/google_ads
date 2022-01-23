package com.example.google_ads_api.repository.master;

import com.example.google_ads_api.domain.master.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CampaignRepository extends JpaRepository<Campaign, Long> {

    public Campaign findByIdAndDeleted(Long id, int deleted);

}
