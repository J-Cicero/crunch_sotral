package com.smart.sotral.transport.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

import com.smart.sotral.transport.domain.models.Prediction;

public interface PredictionRepository extends JpaRepository<Prediction, Long> {
    Optional<Prediction> findByBusIdAndArretId(Long busId, Long arretId);
    List<Prediction> findByArretIdOrderByTempsRestantMinutesAsc(Long arretId);
    List<Prediction> findByBusIdOrderByTempsRestantMinutesAsc(Long busId);
    void deleteByBusId(Long busId);
}
