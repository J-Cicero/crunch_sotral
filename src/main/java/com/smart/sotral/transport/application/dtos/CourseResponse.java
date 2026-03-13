package com.smart.sotral.transport.application.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.smart.sotral.transport.domain.enums.StatutCourse;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CourseResponse {
    Long id;
    UUID trackingId;
    UUID missionTrackingId;
    LocalDateTime dateDebut;
    LocalDateTime dateFin;
    String lieuDebut;
    String lieuFin;
    StatutCourse statut;
}
