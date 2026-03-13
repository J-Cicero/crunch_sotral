package com.smart.sotral.transport.application.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LigneRequest {
    @NotBlank
    private String numero;
    @NotBlank
    private String depart;
    @NotBlank
    private String arrive;
    @NotNull
    private Long typeLigneId;
}
