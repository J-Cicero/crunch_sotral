package com.smart.sotral.transport.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.smart.sotral.transport.domain.models.Prediction;

public interface PredictionRepository extends JpaRepository<Prediction, Long> {
    Optional<Prediction> findByTrackingId(UUID trackingId);
    Optional<Prediction> findByBus_TrackingIdAndArret_TrackingId(UUID busTrackingId, UUID arretTrackingId);
    List<Prediction> findByArret_TrackingIdOrderByTempsRestantMinutesAsc(UUID arretTrackingId);
    List<Prediction> findByBus_TrackingIdOrderByTempsRestantMinutesAsc(UUID busTrackingId);
    void deleteByBus_TrackingId(UUID busTrackingId);
    List<Prediction> findByArret_TrackingId(UUID arretTrackingId);
    @Query("SELECT DISTINCT p.bus.trackingId FROM Prediction p WHERE p.arret.trackingId = :arretTrackingId")
    List<UUID> findDistinctBusTrackingIdByArret_TrackingId(UUID arretTrackingId);
}
