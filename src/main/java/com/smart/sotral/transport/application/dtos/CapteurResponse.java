package com.smart.sotral.transport.application.dtos;

import java.time.LocalDateTime;

import com.smart.sotral.transport.domain.enums.SourceSignal;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CapteurResponse {
    Long id;
    java.util.UUID trackingId;
    Long vehiculeId;
    java.util.UUID vehiculeTrackingId;
    Double latitude;
    Double longitude;
    Double vitesse;
    Double cap;
    LocalDateTime horodatage;
    SourceSignal sourceSignal;
}
