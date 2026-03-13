package com.smart.sotral.transport.application.dtos;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class BusResponse {
    Long id;
    String code;
    Long ligneId;
}
