package com.smart.sotral.transport.application.mappers;

import com.smart.sotral.transport.application.dtos.LigneArretResponse;
import com.smart.sotral.transport.domain.models.LigneArret;

public class LigneArretMapper {
    public static LigneArretResponse toResponse(LigneArret entity) {
        return LigneArretResponse.builder()
                .id(entity.getId())
                .trackingId(entity.getTrackingId())
                .ligneTrackingId(entity.getLigne().getTrackingId())
                .arretTrackingId(entity.getArret().getTrackingId())
                .ordre(entity.getOrdre())
                .build();
    }
}
