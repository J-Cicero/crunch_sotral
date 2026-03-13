package com.smart.sotral.transport.application.dtos;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ArretResponse {
    Long id;
    String nom;
    Double latitude;
    Double longitude;
}
