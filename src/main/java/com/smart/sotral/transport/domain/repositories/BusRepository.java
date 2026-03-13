package com.smart.sotral.transport.domain.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.EntityGraph;

import com.smart.sotral.transport.domain.models.Bus;

public interface BusRepository extends JpaRepository<Bus, Long> {
    Optional<Bus> findByCode(String code);
    Optional<Bus> findByTrackingId(UUID trackingId);

    @Override
    @EntityGraph(attributePaths = {"busVehicules"})
    List<Bus> findAll();
}
