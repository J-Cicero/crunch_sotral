package com.smart.sotral.transport.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

import com.smart.sotral.transport.domain.models.BusVehicule;

public interface BusVehiculeRepository extends JpaRepository<BusVehicule, Long> {
    Optional<BusVehicule> findByVehiculeIdAndStatut(Long vehiculeId, String statut);
    List<BusVehicule> findByStatut(String statut);
    Optional<BusVehicule> findByTrackingId(java.util.UUID trackingId);
    List<BusVehicule> findByBus_TrackingId(java.util.UUID busTrackingId);
    List<BusVehicule> findByVehicule_TrackingId(java.util.UUID vehiculeTrackingId);
}
