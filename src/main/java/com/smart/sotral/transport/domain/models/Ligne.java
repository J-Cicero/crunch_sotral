package com.smart.sotral.transport.domain.models;

import com.smart.sotral.Shared.utils.BaseEntity;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "lignes")
@Getter
@Setter
public class Ligne extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, updatable = false)
    private UUID trackingId;

    @Column(nullable = false, length = 30)
    private String numero;

    @Column(nullable = false, length = 120)
    private String depart;

    @Column(nullable = false, length = 120)
    private String arrive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_ligne_id", nullable = false)
    private TypeLigne typeLigne;

    @PrePersist
    public void ensureTracking() {
        if (trackingId == null) trackingId = UUID.randomUUID();
    }
}
