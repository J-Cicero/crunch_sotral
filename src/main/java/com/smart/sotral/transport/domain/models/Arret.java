package com.smart.sotral.transport.domain.models;

import com.smart.sotral.Shared.utils.BaseEntity;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "arrets")
@Getter
@Setter
public class Arret extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, updatable = false)
    private UUID trackingId;

    @Column(nullable = false, length = 120)
    private String nom;

    private Double latitude;
    private Double longitude;

    @PrePersist
    public void ensureTracking() {
        if (trackingId == null) trackingId = UUID.randomUUID();
    }
}
