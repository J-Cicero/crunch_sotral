package com.smart.sotral.transport.domain.models;

import com.smart.sotral.Shared.utils.BaseEntity;
import com.smart.sotral.transport.domain.models.BusVehicule;
import java.util.UUID;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.OneToMany;
import jakarta.persistence.FetchType;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "bus")
@Getter
@Setter
public class Bus extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, updatable = false)
    private UUID trackingId;

    @Column(nullable = false, unique = true, length = 40)
    private String code;

    @OneToMany(mappedBy = "bus", fetch = FetchType.LAZY)
    private List<BusVehicule> busVehicules = new ArrayList<>();

    @PrePersist
    public void ensureTracking() {
        if (trackingId == null) trackingId = UUID.randomUUID();
    }
}
