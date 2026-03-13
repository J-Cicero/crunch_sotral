package com.smart.sotral.transport.domain.services;

import java.util.List;

import com.smart.sotral.transport.application.dtos.MissionRequest;
import com.smart.sotral.transport.application.dtos.MissionResponse;

public interface MissionService {
    MissionResponse create(MissionRequest request);
    MissionResponse update(Long id, MissionRequest request);
    MissionResponse get(Long id);
    List<MissionResponse> list();
    void delete(Long id);
}
