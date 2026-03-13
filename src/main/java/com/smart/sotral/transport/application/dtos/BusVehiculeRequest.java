package com.smart.sotral.transport.application.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter
public class BusVehiculeRequest {
    @NotNull
    private UUID busTrackingId;
    @NotNull
    private UUID vehiculeTrackingId;
    private String statut = "ACTIF";
}
