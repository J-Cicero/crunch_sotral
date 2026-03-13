package com.smart.sotral.transport.domain.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smart.sotral.transport.domain.models.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findByTrackingId(java.util.UUID trackingId);
    List<Course> findByMission_TrackingId(java.util.UUID missionTrackingId);
}
