package com.smart.sotral.transport.application.dtos;

import lombok.Builder;
import lombok.Value;
import java.util.UUID;

@Value
@Builder
public class TypeLigneResponse {
    Long id;
    UUID trackingId;
    String nom;
}
