package com.smart.sotral.transport.domain.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smart.sotral.transport.domain.models.Ligne;

public interface LigneRepository extends JpaRepository<Ligne, Long> {
    Optional<Ligne> findByTrackingId(java.util.UUID trackingId);
}
