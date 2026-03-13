package com.smart.sotral.transport.application.mappers;

import com.smart.sotral.transport.application.dtos.PredictionResponse;
import com.smart.sotral.transport.domain.models.Prediction;

public class PredictionMapper {
    public static PredictionResponse toResponse(Prediction entity) {
        return PredictionResponse.builder()
                .id(entity.getId())
                .trackingId(entity.getTrackingId())
                .busTrackingId(entity.getBus().getTrackingId())
                .ligneTrackingId(entity.getLigne().getTrackingId())
                .arretTrackingId(entity.getArret().getTrackingId())
                .distanceRestanteKm(entity.getDistanceRestanteKm())
                .tempsRestantMinutes(entity.getTempsRestantMinutes())
                .heureEstimeeArrivee(entity.getHeureEstimeeArrivee())
                .horodatage(entity.getHorodatage())
                .build();
    }
}
