package com.smart.sotral.transport.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

import com.smart.sotral.transport.domain.models.BusVehicule;

public interface BusVehiculeRepository extends JpaRepository<BusVehicule, Long> {
    Optional<BusVehicule> findByVehicule_TrackingIdAndStatut(java.util.UUID vehiculeTrackingId, String statut);
    List<BusVehicule> findByStatut(String statut);
    Optional<BusVehicule> findByTrackingId(java.util.UUID trackingId);
    List<BusVehicule> findByBus_TrackingId(java.util.UUID busTrackingId);
    List<BusVehicule> findByVehicule_TrackingId(java.util.UUID vehiculeTrackingId);
    List<BusVehicule> findByBus_TrackingIdInAndStatut(List<java.util.UUID> busTrackingIds, String statut);
}
