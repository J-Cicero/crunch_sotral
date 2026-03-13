package com.smart.sotral.transport.domain.services;

import java.util.List;

import com.smart.sotral.transport.application.dtos.LigneRequest;
import com.smart.sotral.transport.application.dtos.LigneResponse;
import java.util.UUID;

public interface LigneService {
    LigneResponse create(LigneRequest request);
    LigneResponse update(UUID trackingId, LigneRequest request);
    LigneResponse get(UUID trackingId);
    List<LigneResponse> list();
    void delete(UUID trackingId);
}
