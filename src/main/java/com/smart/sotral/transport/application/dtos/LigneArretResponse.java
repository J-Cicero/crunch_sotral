package com.smart.sotral.transport.application.dtos;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class LigneArretResponse {
    Long id;
    Long ligneId;
    Long arretId;
    Integer ordre;
}
