package com.smart.sotral.transport.application.dtos;

import lombok.Builder;
import lombok.Value;
import java.util.UUID;

@Value
@Builder
public class BusResponse {
    Long id;
    UUID trackingId;
    String code;
}
