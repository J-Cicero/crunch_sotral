package com.smart.sotral.transport.domain.services;

import java.util.List;

import com.smart.sotral.transport.application.dtos.ConducteurRequest;
import com.smart.sotral.transport.application.dtos.ConducteurResponse;
import java.util.UUID;

public interface ConducteurService {
    ConducteurResponse create(ConducteurRequest request);
    ConducteurResponse update(UUID trackingId, ConducteurRequest request);
    ConducteurResponse get(UUID trackingId);
    List<ConducteurResponse> list();
    void delete(UUID trackingId);
}
