package com.smart.sotral.transport.application.mappers;

import org.springframework.stereotype.Component;
import com.smart.sotral.transport.application.dtos.LigneRequest;
import com.smart.sotral.transport.application.dtos.LigneResponse;
import com.smart.sotral.transport.domain.models.Ligne;
import com.smart.sotral.transport.domain.models.TypeLigne;

@Component
public class LigneMapper {
    public static Ligne toEntity(LigneRequest request, TypeLigne typeLigne) {
        Ligne l = new Ligne();
        l.setNumero(request.getNumero());
        l.setDepart(request.getDepart());
        l.setArrive(request.getArrive());
        l.setTypeLigne(typeLigne);
        return l;
    }

    public static LigneResponse toResponse(Ligne entity) {
        return LigneResponse.builder()
                .id(entity.getId())
                .trackingId(entity.getTrackingId())
                .numero(entity.getNumero())
                .depart(entity.getDepart())
                .arrive(entity.getArrive())
                .typeLigneTrackingId(entity.getTypeLigne().getTrackingId())
                .build();
    }
}
