package com.smart.sotral.transport.application.mappers;

import com.smart.sotral.transport.application.dtos.LigneArretResponse;
import com.smart.sotral.transport.domain.models.LigneArret;

public class LigneArretMapper {
    public static LigneArretResponse toResponse(LigneArret entity) {
        return LigneArretResponse.builder()
                .id(entity.getId())
                .ligneId(entity.getLigne().getId())
                .arretId(entity.getArret().getId())
                .ordre(entity.getOrdre())
                .build();
    }
}
