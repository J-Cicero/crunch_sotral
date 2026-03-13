package com.smart.sotral.transport.domain.services;

import java.util.List;

import com.smart.sotral.transport.application.dtos.BusRequest;
import com.smart.sotral.transport.application.dtos.BusResponse;
import java.util.UUID;

public interface BusService {
    BusResponse create(BusRequest request);
    BusResponse update(UUID trackingId, BusRequest request);
    BusResponse get(UUID trackingId);
    List<BusResponse> list();
    void delete(UUID trackingId);
}
