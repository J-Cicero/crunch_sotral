package com.smart.sotral.transport.application.mappers;

import org.springframework.stereotype.Component;
import com.smart.sotral.transport.application.dtos.BusVehiculeRequest;
import com.smart.sotral.transport.application.dtos.BusVehiculeResponse;
import com.smart.sotral.transport.domain.models.Bus;
import com.smart.sotral.transport.domain.models.BusVehicule;
import com.smart.sotral.transport.domain.models.Vehicule;

@Component
public class BusVehiculeMapper {

    public static BusVehicule toEntity(BusVehiculeRequest request, Bus bus, Vehicule vehicule) {
        BusVehicule bv = new BusVehicule();
        bv.setBus(bus);
        bv.setVehicule(vehicule);
        bv.setStatut(request.getStatut());
        return bv;
    }

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
