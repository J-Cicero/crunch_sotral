package com.smart.sotral.transport.application.dtos;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PredictionResponse {
    Long id;
    Long busId;
    Long ligneId;
    Long arretId;
    Double distanceRestanteKm;
    Integer tempsRestantMinutes;
    LocalDateTime heureEstimeeArrivee;
    LocalDateTime horodatage;
}
