package com.smart.sotral.transport.application.mappers;

import com.smart.sotral.transport.application.dtos.ArretResponse;
import com.smart.sotral.transport.domain.models.Arret;

public class ArretMapper {
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
