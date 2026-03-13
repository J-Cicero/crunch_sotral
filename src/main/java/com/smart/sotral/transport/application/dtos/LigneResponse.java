package com.smart.sotral.transport.application.dtos;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class LigneResponse {
    Long id;
    String numero;
    String depart;
    String arrive;
    Long typeLigneId;
}
