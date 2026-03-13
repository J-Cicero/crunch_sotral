package com.smart.sotral.transport.application.mappers;

import org.springframework.stereotype.Component;
import com.smart.sotral.transport.application.dtos.PredictionRequest;
import com.smart.sotral.transport.application.dtos.PredictionResponse;
import com.smart.sotral.transport.domain.models.Arret;
import com.smart.sotral.transport.domain.models.Bus;
import com.smart.sotral.transport.domain.models.Prediction;

@Component
public class PredictionMapper {
    public static Prediction toEntity(PredictionRequest request, Bus bus, Arret arret) {
        Prediction p = new Prediction();
        p.setBus(bus);
        p.setArret(arret);
        p.setDistanceRestanteKm(request.getDistanceRestanteKm());
        p.setTempsRestantMinutes(request.getTempsRestantMinutes());
        p.setHeureEstimeeArrivee(request.getHeureEstimeeArrivee());
        p.setHorodatage(request.getHorodatage());
        return p;
    }

    public static PredictionResponse toResponse(Prediction entity) {
        return PredictionResponse.builder()
                .id(entity.getId())
                .trackingId(entity.getTrackingId())
                .busTrackingId(entity.getBus().getTrackingId())
                .arretTrackingId(entity.getArret().getTrackingId())
                .distanceRestanteKm(entity.getDistanceRestanteKm())
                .tempsRestantMinutes(entity.getTempsRestantMinutes())
                .heureEstimeeArrivee(entity.getHeureEstimeeArrivee())
                .horodatage(entity.getHorodatage())
                .build();
    }
}
