package com.smart.sotral.transport.application.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.smart.sotral.transport.domain.enums.StatutMission;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class MissionResponse {
    Long id;
    UUID trackingId;
    UUID busVehiculeTrackingId;
    UUID conducteurTrackingId;
    LocalDateTime dateDebut;
    LocalDateTime dateFin;
    StatutMission statut;
}
