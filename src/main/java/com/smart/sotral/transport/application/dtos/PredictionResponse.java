package com.smart.sotral.transport.application.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PredictionResponse {
    Long id;
    UUID trackingId;
    UUID busTrackingId;
    UUID ligneTrackingId;
    UUID arretTrackingId;
    Double distanceRestanteKm;
    Integer tempsRestantMinutes;
    LocalDateTime heureEstimeeArrivee;
    LocalDateTime horodatage;
}
