package com.smart.sotral.transport.application.dtos;

import lombok.Builder;
import lombok.Value;
import java.util.UUID;

@Value
@Builder
public class LigneArretResponse {
    Long id;
    UUID trackingId;
    UUID ligneTrackingId;
    UUID arretTrackingId;
    Integer ordre;
}
