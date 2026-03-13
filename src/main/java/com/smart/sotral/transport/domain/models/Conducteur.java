package com.smart.sotral.transport.domain.models;

import java.time.LocalDate;
import java.util.UUID;

import com.smart.sotral.Shared.utils.BaseEntity;

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
@Table(name = "conducteurs")
@Getter
@Setter
public class Conducteur extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, updatable = false)
    private UUID trackingId;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(nullable = false, length = 80)
    private String numeroPermis;

    private LocalDate dateEmbauche;

    @PrePersist
    public void ensureTracking() {
        if (trackingId == null) {
            trackingId = UUID.randomUUID();
        }
    }
}
