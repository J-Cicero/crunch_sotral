package com.smart.sotral.transport.domain.services;

import java.util.List;

import com.smart.sotral.transport.application.dtos.VehiculeRequest;
import com.smart.sotral.transport.application.dtos.VehiculeResponse;
import java.util.UUID;

public interface VehiculeService {
    VehiculeResponse create(VehiculeRequest request);
    VehiculeResponse update(UUID trackingId, VehiculeRequest request);
    VehiculeResponse get(UUID trackingId);
    List<VehiculeResponse> list();
    void delete(UUID trackingId);
}
