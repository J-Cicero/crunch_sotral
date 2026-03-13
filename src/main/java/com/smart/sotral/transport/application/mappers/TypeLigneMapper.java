package com.smart.sotral.transport.application.mappers;

import org.springframework.stereotype.Component;
import com.smart.sotral.transport.application.dtos.TypeLigneRequest;
import com.smart.sotral.transport.application.dtos.TypeLigneResponse;
import com.smart.sotral.transport.domain.models.TypeLigne;

@Component
public class TypeLigneMapper {
    public static TypeLigne toEntity(TypeLigneRequest request) {
        TypeLigne t = new TypeLigne();
        t.setNom(request.getNom());
        return t;
    }

    public static TypeLigneResponse toResponse(TypeLigne entity) {
        return TypeLigneResponse.builder()
                .id(entity.getId())
                .trackingId(entity.getTrackingId())
                .nom(entity.getNom())
                .build();
    }
}
