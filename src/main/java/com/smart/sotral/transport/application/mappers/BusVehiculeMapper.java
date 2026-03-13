package com.smart.sotral.transport.application.mappers;

import com.smart.sotral.transport.application.dtos.BusVehiculeResponse;
import com.smart.sotral.transport.domain.models.BusVehicule;

public class BusVehiculeMapper {
    public static BusVehiculeResponse toResponse(BusVehicule entity) {
        return BusVehiculeResponse.builder()
                .id(entity.getId())
                .busId(entity.getBus().getId())
                .vehiculeId(entity.getVehicule().getId())
                .statut(entity.getStatut())
                .build();
    }
}
