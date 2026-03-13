package com.smart.sotral.transport.application.mappers;

import com.smart.sotral.transport.application.dtos.TypeLigneResponse;
import com.smart.sotral.transport.domain.models.TypeLigne;

public class TypeLigneMapper {
    public static TypeLigneResponse toResponse(TypeLigne entity) {
        return TypeLigneResponse.builder()
                .id(entity.getId())
                .trackingId(entity.getTrackingId())
                .nom(entity.getNom())
                .build();
    }
}
