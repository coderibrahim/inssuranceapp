package com.iakbas.nevitechinsuranceapp.service;

import com.iakbas.nevitechinsuranceapp.model.*;
import com.iakbas.nevitechinsuranceapp.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CampaignService {

    private final CampaignRepository campaignRepository;
    private final CampaignStatusHistoryRepository historyRepository;

    public Campaign createCampaign(Campaign campaign) {
        if (campaignRepository.existsByTitleAndDescription(campaign.getTitle(), campaign.getDescription())) {
            campaign.setStatus(Status.MUKERRER);
        } else {
            if (campaign.getCategory() == Category.HAYAT) {
                campaign.setStatus(Status.AKTIF);
            } else {
                campaign.setStatus(Status.ONAY_BEKLIYOR);
            }
        }

        Campaign saved = campaignRepository.save(campaign);
        CampaignStatusHistory history = new CampaignStatusHistory();
        history.setCampaign(saved);
        history.setStatus(saved.getStatus());
        history.setChangedAt(LocalDateTime.now());
        historyRepository.save(history);

        return saved;
    }

    public Campaign changeStatus(Long id, Status newStatus) {
        Campaign campaign = campaignRepository.findById(id).orElseThrow();
        if (campaign.getStatus() == Status.MUKERRER) return campaign;

        campaign.setStatus(newStatus);
        Campaign updated = campaignRepository.save(campaign);

        CampaignStatusHistory history = new CampaignStatusHistory();
        history.setCampaign(campaign);
        history.setStatus(newStatus);
        history.setChangedAt(LocalDateTime.now());
        historyRepository.save(history);

        return updated;
    }

    public Campaign getById(Long id) {
        return campaignRepository.findById(id).orElseThrow();
    }

    
    public List<Campaign> getAllCampaigns() {
        return campaignRepository.findAllByOrderByCreatedAtDesc();
    }

    public List<CampaignStatusHistory> getStatusHistory(Campaign campaign) {
        return historyRepository.findByCampaignOrderByChangedAtDesc(campaign);
    }

    public Map<Status, Long> getStatistics() {
        return campaignRepository.findAll().stream()
                .collect(Collectors.groupingBy(Campaign::getStatus, Collectors.counting()));
    }
}
