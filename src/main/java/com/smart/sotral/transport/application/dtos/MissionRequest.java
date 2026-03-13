package com.smart.sotral.transport.application.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.smart.sotral.transport.domain.enums.StatutMission;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MissionRequest {
    @NotNull
    private UUID busVehiculeTrackingId;
    @NotNull
    private UUID conducteurTrackingId;
    @NotNull
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    @NotNull
    private StatutMission statut;
}
