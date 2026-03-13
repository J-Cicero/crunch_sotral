package com.smart.sotral.transport.application.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.smart.sotral.transport.domain.enums.StatutCourse;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseRequest {
    @NotNull
    private UUID missionTrackingId;
    @NotNull
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private String lieuDebut;
    private String lieuFin;
    @NotNull
    private StatutCourse statut;
}
