package com.smart.sotral.transport.application.dtos;

import java.time.LocalDate;
import java.util.UUID;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ConducteurResponse {
    Long id;
    UUID trackingId;
    String firstName;
    String lastName;
    String email;
    String numeroPermis;
    LocalDate dateEmbauche;
    boolean active;
}
