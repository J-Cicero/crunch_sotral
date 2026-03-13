package com.smart.sotral.transport.domain.models;

import java.time.LocalDateTime;
import java.util.UUID;

import com.smart.sotral.Shared.utils.BaseEntity;
import com.smart.sotral.transport.domain.enums.SourceSignal;

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
@Table(name = "capteurs")
@Getter
@Setter
public class Capteur extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, updatable = false)
    private UUID trackingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicule_id", nullable = false)
    private Vehicule vehicule;

    private Double latitude;
    private Double longitude;
    private Double vitesse;
    private Double cap;
    private LocalDateTime horodatage;

    @Enumerated(EnumType.STRING)
    private SourceSignal sourceSignal = SourceSignal.GPS_CAPTEUR;

    @PrePersist
    public void ensureTracking() {
        if (trackingId == null) trackingId = UUID.randomUUID();
    }
}
