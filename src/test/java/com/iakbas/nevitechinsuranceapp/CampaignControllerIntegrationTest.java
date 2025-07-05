package com.iakbas.nevitechinsuranceapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iakbas.nevitechinsuranceapp.model.Campaign;
import com.iakbas.nevitechinsuranceapp.model.Category;
import com.iakbas.nevitechinsuranceapp.model.Status;
import com.iakbas.nevitechinsuranceapp.repository.CampaignRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CampaignControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CampaignRepository campaignRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateCampaign() throws Exception {
        Campaign campaign = new Campaign();
        campaign.setTitle("Test Kampanyası");
        campaign.setDescription("Test açıklaması");
        campaign.setCategory(Category.HAYAT);

        mockMvc.perform(post("/api/campaigns")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(campaign)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.status").value("AKTIF")); // HAYAT ise status AKTIF atanıyor

        assertThat(campaignRepository.findAll()).anyMatch(c -> c.getTitle().equals("Test Kampanyası"));
    }

    @Test
    void testChangeStatus() throws Exception {
        // İlk olarak bir kampanya oluştur
        Campaign campaign = new Campaign();
        campaign.setTitle("Durum Test");
        campaign.setDescription("Durum testi açıklama");
        campaign.setCategory(Category.HAYAT);
        Campaign saved = campaignRepository.save(campaign);

        mockMvc.perform(patch("/api/campaigns/" + saved.getId() + "/status?status=DEAKTIF"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("DEAKTIF"));
    }

    @Test
    void testGetStatistics() throws Exception {
        mockMvc.perform(get("/api/campaigns/dashboard/classifieds/statistics"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}