package com.smart.sotral.transport.application.mappers;

import com.smart.sotral.transport.application.dtos.VehiculeRequest;
import com.smart.sotral.transport.application.dtos.VehiculeResponse;
import com.smart.sotral.transport.domain.models.Vehicule;

public class VehiculeMapper {
    public static Vehicule toEntity(VehiculeRequest request) {
        Vehicule v = new Vehicule();
        v.setMatricule(request.getMatricule());
        v.setMarque(request.getMarque());
        v.setModele(request.getModele());
        v.setStatut(request.getStatut());
        return v;
    }

    public static VehiculeResponse toResponse(Vehicule entity) {
        return VehiculeResponse.builder()
                .id(entity.getId())
                .trackingId(entity.getTrackingId())
                .matricule(entity.getMatricule())
                .marque(entity.getMarque())
                .modele(entity.getModele())
                .statut(entity.getStatut())
                .build();
    }
}
