package com.smart.sotral.transport.application.mappers;

import com.smart.sotral.transport.application.dtos.CourseResponse;
import com.smart.sotral.transport.domain.models.Course;

public class CourseMapper {
    public static CourseResponse toResponse(Course entity) {
        return CourseResponse.builder()
                .id(entity.getId())
                .missionId(entity.getMission().getId())
                .dateDebut(entity.getDateDebut())
                .dateFin(entity.getDateFin())
                .lieuDebut(entity.getLieuDebut())
                .lieuFin(entity.getLieuFin())
                .statut(entity.getStatut())
                .build();
    }
}
