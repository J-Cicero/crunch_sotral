package com.smart.sotral.transport.domain.services;

import java.util.List;

import com.smart.sotral.transport.application.dtos.BusRequest;
import com.smart.sotral.transport.application.dtos.BusResponse;

public interface BusService {
    BusResponse create(BusRequest request);
    BusResponse update(Long id, BusRequest request);
    BusResponse get(Long id);
    List<BusResponse> list();
    void delete(Long id);
}
