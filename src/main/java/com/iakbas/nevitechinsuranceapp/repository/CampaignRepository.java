package com.iakbas.nevitechinsuranceapp.repository;

import com.iakbas.nevitechinsuranceapp.model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Locale;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {
    boolean existsByTitleAndDescription(String title, String description);

    List<Campaign> findAllByOrderByCreatedAtDesc();
}