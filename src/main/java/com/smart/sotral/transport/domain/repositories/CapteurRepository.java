package com.smart.sotral.transport.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

import com.smart.sotral.transport.domain.models.Capteur;

public interface CapteurRepository extends JpaRepository<Capteur, Long> {

    Optional<Capteur> findTopByVehicule_TrackingIdOrderByHorodatageDesc(java.util.UUID vehiculeTrackingId);

    List<Capteur> findTop5ByVehicule_TrackingIdOrderByHorodatageDesc(java.util.UUID vehiculeTrackingId);

    @Query("SELECT c FROM Capteur c WHERE c.horodatage = (SELECT MAX(c2.horodatage) FROM Capteur c2 WHERE c2.vehicule.trackingId = c.vehicule.trackingId)")
    List<Capteur> findDernierePositionParVehicule();

    List<Capteur> findByVehicule_TrackingIdOrderByHorodatageDesc(java.util.UUID vehiculeTrackingId);

    Optional<Capteur> findByTrackingId(java.util.UUID trackingId);
}
