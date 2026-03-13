package com.smart.sotral.transport.domain.services;

import java.util.List;

import com.smart.sotral.transport.application.dtos.BusVehiculeRequest;
import com.smart.sotral.transport.application.dtos.BusVehiculeResponse;
import java.util.UUID;

public interface BusVehiculeService {
    BusVehiculeResponse create(BusVehiculeRequest request);
    BusVehiculeResponse update(UUID trackingId, BusVehiculeRequest request);
    BusVehiculeResponse get(UUID trackingId);
    List<BusVehiculeResponse> list();
    void delete(UUID trackingId);
}
