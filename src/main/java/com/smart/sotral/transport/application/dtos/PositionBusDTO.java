package com.smart.sotral.transport.application.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PositionBusDTO {
    UUID vehiculeTrackingId;
    UUID busTrackingId;
    String busCode;
    UUID ligneTrackingId;
    String ligneNumero;
    Double latitude;
    Double longitude;
    Double vitesse;
    LocalDateTime horodatage;
    Boolean missionActive;
}
