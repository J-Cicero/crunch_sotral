package com.smart.sotral.transport.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import com.smart.sotral.transport.domain.models.LigneArret;

public interface LigneArretRepository extends JpaRepository<LigneArret, Long> {
    List<LigneArret> findByLigneIdOrderByOrdreAsc(Long ligneId);
    List<LigneArret> findByLigne_TrackingIdOrderByOrdreAsc(java.util.UUID ligneTrackingId);
    java.util.Optional<LigneArret> findByTrackingId(java.util.UUID trackingId);
}
