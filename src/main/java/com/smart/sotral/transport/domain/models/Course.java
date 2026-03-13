package com.smart.sotral.transport.domain.models;

import java.time.LocalDateTime;

import com.smart.sotral.Shared.utils.BaseEntity;
import com.smart.sotral.transport.domain.enums.StatutCourse;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "courses")
@Getter
@Setter
public class Course extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, updatable = false)
    private UUID trackingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id", nullable = false)
    private Mission mission;

    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;

    @Column(length = 120)
    private String lieuDebut;

    @Column(length = 120)
    private String lieuFin;

    @Enumerated(EnumType.STRING)
    private StatutCourse statut = StatutCourse.EN_COURS;

    @PrePersist
    public void ensureTracking() {
        if (trackingId == null) trackingId = UUID.randomUUID();
    }
}
