package com.smart.sotral.transport.application.mappers;

import com.smart.sotral.transport.application.dtos.PredictionResponse;
import com.smart.sotral.transport.domain.models.Prediction;

public class PredictionMapper {
    public static PredictionResponse toResponse(Prediction entity) {
        return PredictionResponse.builder()
                .id(entity.getId())
                .busId(entity.getBus().getId())
                .ligneId(entity.getLigne().getId())
                .arretId(entity.getArret().getId())
                .distanceRestanteKm(entity.getDistanceRestanteKm())
                .tempsRestantMinutes(entity.getTempsRestantMinutes())
                .heureEstimeeArrivee(entity.getHeureEstimeeArrivee())
                .horodatage(entity.getHorodatage())
                .build();
    }
}
