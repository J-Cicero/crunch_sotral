package com.smart.sotral.transport.domain.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smart.sotral.transport.domain.models.TypeLigne;

public interface TypeLigneRepository extends JpaRepository<TypeLigne, Long> {
    Optional<TypeLigne> findByTrackingId(java.util.UUID trackingId);
}
