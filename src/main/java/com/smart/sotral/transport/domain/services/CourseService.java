package com.smart.sotral.transport.domain.services;

import java.util.List;

import com.smart.sotral.transport.application.dtos.CourseRequest;
import com.smart.sotral.transport.application.dtos.CourseResponse;

public interface CourseService {
    CourseResponse create(CourseRequest request);
    CourseResponse update(Long id, CourseRequest request);
    CourseResponse get(Long id);
    List<CourseResponse> list();
    void delete(Long id);
}
