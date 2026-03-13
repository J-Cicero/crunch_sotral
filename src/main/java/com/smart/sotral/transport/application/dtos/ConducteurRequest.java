package com.smart.sotral.transport.application.dtos;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConducteurRequest {
    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String numeroPermis;

    @NotNull
    private LocalDate dateEmbauche;
}
