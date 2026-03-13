package com.smart.sotral.transport.domain.services;

import java.util.List;

import com.smart.sotral.transport.application.dtos.MissionRequest;
import com.smart.sotral.transport.application.dtos.MissionResponse;
import java.util.UUID;

public interface MissionService {
    MissionResponse create(MissionRequest request);
    MissionResponse update(UUID trackingId, MissionRequest request);
    MissionResponse get(UUID trackingId);
    List<MissionResponse> list();
    void delete(UUID trackingId);
}
