package com.example.google_ads_api.repository.performance;

import com.example.google_ads_api.domain.performance.AdPerformance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdPerformanceRepository extends JpaRepository<AdPerformance, Long> {
//    public AdPerformance findByCompanyCodeAndDeleted(String companyCode, int deleted);

//    @Query(value = "SELECT campaign_id, impressions, clicks, conversions, cost FROM ad_performances" +
//            " WHERE date BETWEEN ")
//    public List<Object[]> getCampaignSummaries();
}
