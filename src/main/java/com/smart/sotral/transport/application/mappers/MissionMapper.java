package com.smart.sotral.transport.application.mappers;

import com.smart.sotral.transport.application.dtos.MissionResponse;
import com.smart.sotral.transport.domain.models.Mission;

public class MissionMapper {
    public static MissionResponse toResponse(Mission entity) {
        return MissionResponse.builder()
                .id(entity.getId())
                .trackingId(entity.getTrackingId())
                .busVehiculeTrackingId(entity.getBusVehicule().getTrackingId())
                .conducteurTrackingId(entity.getConducteur().getTrackingId())
                .dateDebut(entity.getDateDebut())
                .dateFin(entity.getDateFin())
                .statut(entity.getStatut())
                .build();
    }
}
