package com.smart.sotral.transport.application.mappers;

import org.springframework.stereotype.Component;
import com.smart.sotral.transport.application.dtos.CapteurRequest;
import com.smart.sotral.transport.application.dtos.CapteurResponse;
import com.smart.sotral.transport.domain.models.Capteur;
import com.smart.sotral.transport.domain.models.Vehicule;

@Component
public class CapteurMapper {
    public static Capteur toEntity(CapteurRequest request, Vehicule vehicule) {
        Capteur c = new Capteur();
        c.setVehicule(vehicule);
        c.setLatitude(request.getLatitude());
        c.setLongitude(request.getLongitude());
        c.setVitesse(request.getVitesse());
        c.setCap(request.getCap());
        c.setHorodatage(request.getHorodatage());
        c.setSourceSignal(request.getSourceSignal());
        return c;
    }

    public static CapteurResponse toResponse(Capteur entity) {
        return CapteurResponse.builder()
                .id(entity.getId())
                .trackingId(entity.getTrackingId())
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
