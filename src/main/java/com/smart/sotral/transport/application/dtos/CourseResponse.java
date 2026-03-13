package com.smart.sotral.transport.application.dtos;

import java.time.LocalDateTime;

import com.smart.sotral.transport.domain.enums.StatutCourse;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CourseResponse {
    Long id;
    Long missionId;
    LocalDateTime dateDebut;
    LocalDateTime dateFin;
    String lieuDebut;
    String lieuFin;
    StatutCourse statut;
}
