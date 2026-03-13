package com.smart.sotral.transport.application.dtos;

import lombok.Builder;
import lombok.Value;
import java.util.UUID;

@Value
@Builder
public class BusVehiculeResponse {
    Long id;
    UUID trackingId;
    UUID busTrackingId;
    UUID vehiculeTrackingId;
    String statut;
}
