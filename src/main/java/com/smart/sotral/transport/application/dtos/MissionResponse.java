package com.smart.sotral.transport.application.dtos;

import java.time.LocalDateTime;

import com.smart.sotral.transport.domain.enums.StatutMission;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class MissionResponse {
    Long id;
    Long busVehiculeId;
    Long conducteurId;
    LocalDateTime dateDebut;
    LocalDateTime dateFin;
    StatutMission statut;
}
