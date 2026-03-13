package com.smart.sotral.transport.application.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusRequest {
    @NotBlank
    private String code;
}
