package com.smart.sotral.transport.application.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArretRequest {
    @NotBlank
    private String nom;
    private Double latitude;
    private Double longitude;
}
