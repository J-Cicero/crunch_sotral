package com.smart.sotral.transport.application.dtos;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ConducteurResponse {
    Long id;
    String email;
    String numeroPermis;
    LocalDate dateEmbauche;
}
