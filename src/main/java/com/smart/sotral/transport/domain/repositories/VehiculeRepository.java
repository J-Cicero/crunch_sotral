package com.smart.sotral.transport.domain.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smart.sotral.transport.domain.models.Vehicule;

public interface VehiculeRepository extends JpaRepository<Vehicule, Long> {
    Optional<Vehicule> findByMatricule(String matricule);
    Optional<Vehicule> findByTrackingId(java.util.UUID trackingId);
}
