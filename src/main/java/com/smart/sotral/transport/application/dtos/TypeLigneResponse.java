package com.smart.sotral.transport.application.dtos;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class TypeLigneResponse {
    Long id;
    String nom;
}
