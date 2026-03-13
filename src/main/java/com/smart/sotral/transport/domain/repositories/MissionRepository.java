package com.smart.sotral.transport.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

import com.smart.sotral.transport.domain.models.Mission;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    Optional<Mission> findByBusVehiculeIdAndStatut(Long busVehiculeId, com.smart.sotral.transport.domain.enums.StatutMission statut);
    List<Mission> findByStatut(com.smart.sotral.transport.domain.enums.StatutMission statut);
    Optional<Mission> findByTrackingId(java.util.UUID trackingId);
    List<Mission> findByBusVehicule_TrackingId(java.util.UUID busVehiculeTrackingId);
    List<Mission> findByConducteur_TrackingId(java.util.UUID conducteurTrackingId);
    Optional<Mission> findByBusVehicule_TrackingIdAndStatut(java.util.UUID busVehiculeTrackingId, com.smart.sotral.transport.domain.enums.StatutMission statut);
}
