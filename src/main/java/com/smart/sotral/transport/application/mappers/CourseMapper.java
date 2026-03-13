package com.smart.sotral.transport.application.mappers;

import org.springframework.stereotype.Component;
import com.smart.sotral.transport.application.dtos.CourseRequest;
import com.smart.sotral.transport.application.dtos.CourseResponse;
import com.smart.sotral.transport.domain.models.Course;
import com.smart.sotral.transport.domain.models.Mission;

@Component
public class CourseMapper {
    public static Course toEntity(CourseRequest request, Mission mission) {
        Course c = new Course();
        c.setMission(mission);
        c.setDateDebut(request.getDateDebut());
        c.setDateFin(request.getDateFin());
        c.setLieuDebut(request.getLieuDebut());
        c.setLieuFin(request.getLieuFin());
        c.setStatut(request.getStatut());
        return c;
    }

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
