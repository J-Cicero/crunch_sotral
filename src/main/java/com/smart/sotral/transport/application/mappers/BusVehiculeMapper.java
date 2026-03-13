package com.smart.sotral.transport.application.mappers;

import com.smart.sotral.transport.application.dtos.BusVehiculeResponse;
import com.smart.sotral.transport.domain.models.BusVehicule;

public class BusVehiculeMapper {
    public static BusVehiculeResponse toResponse(BusVehicule entity) {
        return BusVehiculeResponse.builder()
                .id(entity.getId())
                .trackingId(entity.getTrackingId())
                .busTrackingId(entity.getBus().getTrackingId())
                .vehiculeTrackingId(entity.getVehicule().getTrackingId())
                .statut(entity.getStatut())
                .build();
    }
}
