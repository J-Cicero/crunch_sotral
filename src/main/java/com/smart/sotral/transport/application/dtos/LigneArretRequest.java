package com.smart.sotral.transport.application.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LigneArretRequest {
    @NotNull
    private Long ligneId;
    @NotNull
    private Long arretId;
    @NotNull
    private Integer ordre;
}
