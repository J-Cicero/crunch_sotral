package com.smart.sotral.transport.domain.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smart.sotral.transport.domain.models.Bus;

public interface BusRepository extends JpaRepository<Bus, Long> {
    Optional<Bus> findByCode(String code);
    Optional<Bus> findByTrackingId(java.util.UUID trackingId);
    List<Bus> findByLigneId(Long ligneId);
    List<Bus> findByLigne_TrackingId(UUID ligneTrackingId);
}
