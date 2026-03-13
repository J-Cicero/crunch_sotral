package com.smart.sotral.transport.application.dtos;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PredictionRequest {
    @NotNull
    private Long busId;
    @NotNull
    private Long ligneId;
    @NotNull
    private Long arretId;
    private Double distanceRestanteKm;
    private Integer tempsRestantMinutes;
    private LocalDateTime heureEstimeeArrivee;
    private LocalDateTime horodatage;
}
