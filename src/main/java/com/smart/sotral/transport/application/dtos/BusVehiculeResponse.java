package com.smart.sotral.transport.application.dtos;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class BusVehiculeResponse {
    Long id;
    Long busId;
    Long vehiculeId;
    String statut;
}
