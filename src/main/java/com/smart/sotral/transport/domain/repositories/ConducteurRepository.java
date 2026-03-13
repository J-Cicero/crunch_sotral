package com.smart.sotral.transport.domain.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smart.sotral.transport.domain.models.Conducteur;

public interface ConducteurRepository extends JpaRepository<Conducteur, Long> {
    Optional<Conducteur> findByEmail(String email);
    Optional<Conducteur> findByTrackingId(java.util.UUID trackingId);
}
