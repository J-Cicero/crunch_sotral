package com.smart.sotral.transport.application.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter
public class BusRequest {
    @NotBlank
    private String code;
    private UUID ligneTrackingId;
}
