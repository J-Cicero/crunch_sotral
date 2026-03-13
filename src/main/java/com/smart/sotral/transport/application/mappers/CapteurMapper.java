package com.smart.sotral.transport.application.mappers;

import com.smart.sotral.transport.application.dtos.CapteurResponse;
import com.smart.sotral.transport.domain.models.Capteur;

public class CapteurMapper {
    public static CapteurResponse toResponse(Capteur entity) {
        return CapteurResponse.builder()
                .id(entity.getId())
                .trackingId(entity.getTrackingId())
                .vehiculeId(entity.getVehicule().getId())
                .vehiculeTrackingId(entity.getVehicule().getTrackingId())
                .latitude(entity.getLatitude())
                .longitude(entity.getLongitude())
                .vitesse(entity.getVitesse())
                .cap(entity.getCap())
                .horodatage(entity.getHorodatage())
                .sourceSignal(entity.getSourceSignal())
                .build();
    }
}
