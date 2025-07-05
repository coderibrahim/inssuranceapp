package com.iakbas.nevitechinsuranceapp.repository;


import com.iakbas.nevitechinsuranceapp.model.CampaignStatusHistory;
import com.iakbas.nevitechinsuranceapp.model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CampaignStatusHistoryRepository extends JpaRepository<CampaignStatusHistory, Long> {
    List<CampaignStatusHistory> findByCampaignOrderByChangedAtDesc(Campaign campaign);
}
