package com.smart.sotral.transport.application.dtos;

import com.smart.sotral.transport.domain.enums.StatutVehicule;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class VehiculeResponse {
    Long id;
    String matricule;
    String marque;
    String modele;
    StatutVehicule statut;
}
