package com.smart.sotral.transport.application.dtos;

import com.smart.sotral.transport.domain.enums.StatutVehicule;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehiculeRequest {
    @NotBlank
    private String matricule;
    private String marque;
    private String modele;
    @NotNull
    private StatutVehicule statut;
}
