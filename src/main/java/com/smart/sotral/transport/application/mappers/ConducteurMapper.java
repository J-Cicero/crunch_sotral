package com.smart.sotral.transport.application.mappers;

import com.smart.sotral.transport.application.dtos.ConducteurRequest;
import com.smart.sotral.transport.application.dtos.ConducteurResponse;
import com.smart.sotral.transport.domain.models.Conducteur;

public class ConducteurMapper {
    public static Conducteur toEntity(ConducteurRequest request) {
        Conducteur c = new Conducteur();
        c.setEmail(request.getEmail());
        c.setNumeroPermis(request.getNumeroPermis());
        c.setDateEmbauche(request.getDateEmbauche());
        return c;
    }

    public static ConducteurResponse toResponse(Conducteur entity) {
        return ConducteurResponse.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .numeroPermis(entity.getNumeroPermis())
                .dateEmbauche(entity.getDateEmbauche())
                .build();
    }
}
