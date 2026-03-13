package com.smart.sotral.transport.domain.services;

import java.util.List;

import com.smart.sotral.transport.application.dtos.CourseRequest;
import com.smart.sotral.transport.application.dtos.CourseResponse;
import java.util.UUID;

public interface CourseService {
    CourseResponse create(CourseRequest request);
    CourseResponse update(UUID trackingId, CourseRequest request);
    CourseResponse get(UUID trackingId);
    List<CourseResponse> list();
    void delete(UUID trackingId);
}
