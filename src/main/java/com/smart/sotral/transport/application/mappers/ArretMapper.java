package com.smart.sotral.transport.application.mappers;

import org.springframework.stereotype.Component;
import com.smart.sotral.transport.application.dtos.ArretRequest;
import com.smart.sotral.transport.application.dtos.ArretResponse;
import com.smart.sotral.transport.domain.models.Arret;

@Component
public class ArretMapper {
    public static Arret toEntity(ArretRequest request) {
        Arret a = new Arret();
        a.setNom(request.getNom());
        a.setLatitude(request.getLatitude());
        a.setLongitude(request.getLongitude());
        return a;
    }

    public static ArretResponse toResponse(Arret entity) {
        return ArretResponse.builder()
                .id(entity.getId())
                .trackingId(entity.getTrackingId())
                .nom(entity.getNom())
                .latitude(entity.getLatitude())
                .longitude(entity.getLongitude())
                .build();
    }
}
