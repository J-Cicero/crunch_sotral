package com.smart.sotral.transport.application.mappers;

import com.smart.sotral.transport.application.dtos.CourseResponse;
import com.smart.sotral.transport.domain.models.Course;

public class CourseMapper {
    public static CourseResponse toResponse(Course entity) {
        return CourseResponse.builder()
                .id(entity.getId())
                .trackingId(entity.getTrackingId())
                .missionTrackingId(entity.getMission().getTrackingId())
                .dateDebut(entity.getDateDebut())
                .dateFin(entity.getDateFin())
                .lieuDebut(entity.getLieuDebut())
                .lieuFin(entity.getLieuFin())
                .statut(entity.getStatut())
                .build();
    }
}
