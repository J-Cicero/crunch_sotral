package com.smart.sotral.transport.application.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PredictionRequest {
    @NotNull
    private UUID busTrackingId;
    @NotNull
    private UUID ligneTrackingId;
    @NotNull
    private UUID arretTrackingId;
    private Double distanceRestanteKm;
    private Integer tempsRestantMinutes;
    private LocalDateTime heureEstimeeArrivee;
    private LocalDateTime horodatage;
}
