package com.smart.sotral.transport.application.mappers;

import org.springframework.stereotype.Component;
import com.smart.sotral.transport.application.dtos.LigneArretRequest;
import com.smart.sotral.transport.application.dtos.LigneArretResponse;
import com.smart.sotral.transport.domain.models.Arret;
import com.smart.sotral.transport.domain.models.Ligne;
import com.smart.sotral.transport.domain.models.LigneArret;

@Component
public class LigneArretMapper {
    public static LigneArret toEntity(LigneArretRequest request, Ligne ligne, Arret arret) {
        LigneArret la = new LigneArret();
        la.setLigne(ligne);
        la.setArret(arret);
        la.setOrdre(request.getOrdre());
        return la;
    }

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
