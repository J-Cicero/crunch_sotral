package com.smart.sotral.transport.domain.services;

import java.util.List;

import com.smart.sotral.transport.application.dtos.BusVehiculeRequest;
import com.smart.sotral.transport.application.dtos.BusVehiculeResponse;

public interface BusVehiculeService {
    BusVehiculeResponse create(BusVehiculeRequest request);
    BusVehiculeResponse update(Long id, BusVehiculeRequest request);
    BusVehiculeResponse get(Long id);
    List<BusVehiculeResponse> list();
    void delete(Long id);
}
