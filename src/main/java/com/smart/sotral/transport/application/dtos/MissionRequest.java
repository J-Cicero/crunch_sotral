package com.smart.sotral.transport.application.dtos;

import java.time.LocalDateTime;

import com.smart.sotral.transport.domain.enums.StatutMission;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MissionRequest {
    @NotNull
    private Long busVehiculeId;
    @NotNull
    private Long conducteurId;
    @NotNull
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    @NotNull
    private StatutMission statut;
}
