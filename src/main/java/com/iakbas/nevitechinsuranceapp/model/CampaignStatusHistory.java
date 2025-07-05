package com.iakbas.nevitechinsuranceapp.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class CampaignStatusHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Campaign campaign;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime changedAt = LocalDateTime.now();
}
