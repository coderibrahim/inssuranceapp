package com.iakbas.nevitechinsuranceapp.controller;

import com.iakbas.nevitechinsuranceapp.model.*;
import com.iakbas.nevitechinsuranceapp.service.CampaignService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api/campaigns")
@Tag(name = "Campaign API", description = "Kampanya işlemleri için API")
public class CampaignController {

    private final CampaignService campaignService;
    public CampaignController(CampaignService campaignService) {
        this.campaignService = campaignService;
    }

    @Operation(summary = "Yeni kampanya oluşturur")
    @PostMapping
    public ResponseEntity<Campaign> createCampaign(@RequestBody Campaign campaign) {
        return ResponseEntity.ok(campaignService.createCampaign(campaign));
    }

    @Operation(summary = "Var Olan Kampanyanın durumunu değiştirir")
    @PatchMapping("/{id}/status")
    public ResponseEntity<Campaign> changeStatus(@PathVariable Long id, @RequestParam Status status) {
        return ResponseEntity.ok(campaignService.changeStatus(id, status));
    }

    @Operation(summary = "Kampanyanın geçmişteki durum değişikliklerinin kayıdını döndürür")
    @GetMapping("/{id}/status-history")
    public ResponseEntity<List<CampaignStatusHistory>> getStatusHistory(@PathVariable Long id) {
        Campaign campaign = campaignService.getById(id);
        return ResponseEntity.ok(campaignService.getStatusHistory(campaign));
    }

    @Operation(summary = "Tüm Kampanyaları Getirir")
    @GetMapping("/all")
    public ResponseEntity<List<Campaign>> getAllCampains() {
        return ResponseEntity.ok(campaignService.getAllCampaigns());
    }

    @Operation(summary = "Kampanya Durum Kategorilerinin istatiksel olarak gösterir")
    @GetMapping("/dashboard/classifieds/statistics")
    public ResponseEntity<Map<Status, Long>> getStatistics() {
        return ResponseEntity.ok(campaignService.getStatistics());
    }
}