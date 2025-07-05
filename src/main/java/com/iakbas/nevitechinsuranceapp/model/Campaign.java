package com.iakbas.nevitechinsuranceapp.model;

import jakarta.persistence.*;
import lombok.Data;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Data
public class Campaign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, length = 200)
    private String description;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime createdAt = LocalDateTime.now();
}