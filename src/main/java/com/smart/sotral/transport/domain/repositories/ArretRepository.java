package com.smart.sotral.transport.domain.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smart.sotral.transport.domain.models.Arret;

public interface ArretRepository extends JpaRepository<Arret, Long> {
    Optional<Arret> findByTrackingId(java.util.UUID trackingId);
}
