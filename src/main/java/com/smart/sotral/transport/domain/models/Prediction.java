package com.smart.sotral.transport.domain.models;

import java.time.LocalDateTime;
import java.util.UUID;

import com.smart.sotral.Shared.utils.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "predictions")
@Getter
@Setter
public class Prediction extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, updatable = false)
    private UUID trackingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bus_id", nullable = false)
    private Bus bus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "arret_id", nullable = false)
    private Arret arret;

    private Double distanceRestanteKm;
    private Integer tempsRestantMinutes;
    private LocalDateTime heureEstimeeArrivee;
    private LocalDateTime horodatage;

    @PrePersist
    public void ensureTracking() {
        if (trackingId == null) trackingId = UUID.randomUUID();
    }
}
