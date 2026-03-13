package com.smart.sotral.transport.application.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusVehiculeRequest {
    @NotNull
    private Long busId;
    @NotNull
    private Long vehiculeId;
    private String statut = "ACTIF";
}
