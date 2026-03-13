package com.smart.sotral.transport.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

import com.smart.sotral.transport.domain.models.Capteur;

public interface CapteurRepository extends JpaRepository<Capteur, Long> {

    Optional<Capteur> findTopByVehiculeIdOrderByHorodatageDesc(Long vehiculeId);

    List<Capteur> findTop5ByVehiculeIdOrderByHorodatageDesc(Long vehiculeId);

    @Query("SELECT c FROM Capteur c WHERE c.horodatage = (SELECT MAX(c2.horodatage) FROM Capteur c2 WHERE c2.vehicule.id = c.vehicule.id)")
    List<Capteur> findDernierePositionParVehicule();

    List<Capteur> findByVehiculeIdOrderByHorodatageDesc(Long vehiculeId);

    Optional<Capteur> findByTrackingId(java.util.UUID trackingId);
    List<Capteur> findByVehicule_TrackingIdOrderByHorodatageDesc(java.util.UUID vehiculeTrackingId);
}
