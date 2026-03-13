package com.smart.sotral.transport.domain.models;

import java.time.LocalDateTime;

import com.smart.sotral.Shared.utils.BaseEntity;
import com.smart.sotral.transport.domain.enums.StatutMission;
import java.util.UUID;

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
import jakarta.persistence.Column;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "missions")
@Getter
@Setter
public class Mission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, updatable = false)
    private UUID trackingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bus_vehicule_id", nullable = false)
    private BusVehicule busVehicule;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conducteur_id", nullable = false)
    private Conducteur conducteur;

    private LocalDateTime dateDebut;

    private LocalDateTime dateFin;

    @Enumerated(EnumType.STRING)
    private StatutMission statut = StatutMission.ACTIVE;

    @Column(length = 120)
    private String description;

    @PrePersist
    public void ensureTracking() {
        if (trackingId == null) trackingId = UUID.randomUUID();
    }
}
