package com.smart.sotral.transport.domain.services;

import java.util.List;

import com.smart.sotral.transport.application.dtos.VehiculeRequest;
import com.smart.sotral.transport.application.dtos.VehiculeResponse;

public interface VehiculeService {
    VehiculeResponse create(VehiculeRequest request);
    VehiculeResponse update(Long id, VehiculeRequest request);
    VehiculeResponse get(Long id);
    List<VehiculeResponse> list();
    void delete(Long id);
}
