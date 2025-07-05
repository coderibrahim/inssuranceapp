package com.iakbas.nevitechinsuranceapp;

import com.iakbas.nevitechinsuranceapp.model.*;
import com.iakbas.nevitechinsuranceapp.repository.CampaignRepository;
import com.iakbas.nevitechinsuranceapp.repository.CampaignStatusHistoryRepository;
import com.iakbas.nevitechinsuranceapp.service.CampaignService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class CampaignServiceTest {

    private CampaignRepository campaignRepository;
    private CampaignStatusHistoryRepository historyRepository;
    private CampaignService campaignService;

    @BeforeEach
    void setup() {
        campaignRepository = mock(CampaignRepository.class);
        historyRepository = mock(CampaignStatusHistoryRepository.class);
        campaignService = new CampaignService(campaignRepository, historyRepository);
    }

    @Test
    void shouldCreateCampaignWithAktifStatusWhenCategoryIsHayat() {
        Campaign campaign = new Campaign();
        campaign.setTitle("Test");
        campaign.setDescription("Test desc");
        campaign.setCategory(Category.HAYAT);

        when(campaignRepository.existsByTitleAndDescription("Test", "Test desc"))
                .thenReturn(false);

        Campaign savedCampaign = new Campaign();
        savedCampaign.setId(1L);
        savedCampaign.setTitle("Test");
        savedCampaign.setDescription("Test desc");
        savedCampaign.setStatus(Status.AKTIF);
        savedCampaign.setCategory(Category.HAYAT);

        when(campaignRepository.save(any(Campaign.class))).thenReturn(savedCampaign);

        Campaign result = campaignService.createCampaign(campaign);

        assertThat(result.getStatus()).isEqualTo(Status.AKTIF);
        verify(historyRepository).save(any(CampaignStatusHistory.class));
    }

    @Test
    void shouldChangeStatusOfCampaign() {
        Campaign campaign = new Campaign();
        campaign.setId(1L);
        campaign.setStatus(Status.ONAY_BEKLIYOR);

        when(campaignRepository.findById(1L)).thenReturn(Optional.of(campaign));
        when(campaignRepository.save(any())).thenReturn(campaign);

        Campaign updated = campaignService.changeStatus(1L, Status.AKTIF);

        assertThat(updated.getStatus()).isEqualTo(Status.AKTIF);
        verify(historyRepository).save(any(CampaignStatusHistory.class));
    }

    @Test
    void shouldReturnStatusHistoryList() {
        Campaign campaign = new Campaign();
        campaign.setId(1L);

        List<CampaignStatusHistory> mockHistory = List.of(
                new CampaignStatusHistory(), new CampaignStatusHistory()
        );

        when(historyRepository.findByCampaignOrderByChangedAtDesc(campaign))
                .thenReturn(mockHistory);

        List<CampaignStatusHistory> result = campaignService.getStatusHistory(campaign);

        assertThat(result).hasSize(2);
    }
}