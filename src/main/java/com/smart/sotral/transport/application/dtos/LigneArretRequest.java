package com.smart.sotral.transport.application.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter
public class LigneArretRequest {
    @NotNull
    private UUID ligneTrackingId;
    @NotNull
    private UUID arretTrackingId;
    @NotNull
    private Integer ordre;
}
