package com.smart.sotral.transport.application.mappers;

import com.smart.sotral.transport.application.dtos.LigneResponse;
import com.smart.sotral.transport.domain.models.Ligne;

public class LigneMapper {
    public static LigneResponse toResponse(Ligne entity) {
        return LigneResponse.builder()
                .id(entity.getId())
                .numero(entity.getNumero())
                .depart(entity.getDepart())
                .arrive(entity.getArrive())
                .typeLigneId(entity.getTypeLigne().getId())
                .build();
    }
}
