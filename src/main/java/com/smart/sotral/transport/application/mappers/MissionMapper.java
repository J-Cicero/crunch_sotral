package com.smart.sotral.transport.application.mappers;

import org.springframework.stereotype.Component;
import com.smart.sotral.transport.application.dtos.MissionRequest;
import com.smart.sotral.transport.application.dtos.MissionResponse;
import com.smart.sotral.transport.domain.models.BusVehicule;
import com.smart.sotral.transport.domain.models.Conducteur;
import com.smart.sotral.transport.domain.models.Mission;

@Component
public class MissionMapper {
    public static Mission toEntity(MissionRequest request, BusVehicule busVehicule, Conducteur conducteur) {
        Mission m = new Mission();
        m.setBusVehicule(busVehicule);
        m.setConducteur(conducteur);
        m.setDateDebut(request.getDateDebut());
        m.setDateFin(request.getDateFin());
        m.setStatut(request.getStatut());
        return m;
    }

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
