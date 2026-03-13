package com.smart.sotral.transport.application.dtos;

import java.time.LocalDateTime;

import com.smart.sotral.transport.domain.enums.SourceSignal;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CapteurRequest {
    @NotNull
    private String vehiculeTrackingId;
    private Double latitude;
    private Double longitude;
    private Double vitesse;
    private Double cap;
    private LocalDateTime horodatage;
    private SourceSignal sourceSignal = SourceSignal.GPS_CAPTEUR;
}
