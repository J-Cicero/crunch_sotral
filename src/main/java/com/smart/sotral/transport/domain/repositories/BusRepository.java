package com.smart.sotral.transport.domain.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smart.sotral.transport.domain.models.Bus;

public interface BusRepository extends JpaRepository<Bus, Long> {
    Optional<Bus> findByCode(String code);
    Optional<Bus> findByTrackingId(java.util.UUID trackingId);
}
