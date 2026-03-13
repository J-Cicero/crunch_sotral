package com.smart.sotral.transport.application.dtos;

import lombok.Builder;
import lombok.Value;
import java.util.UUID;

@Value
@Builder
public class LigneResponse {
    Long id;
    UUID trackingId;
    String numero;
    String depart;
    String arrive;
    UUID typeLigneTrackingId;
}
