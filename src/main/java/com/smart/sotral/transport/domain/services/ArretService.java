package com.smart.sotral.transport.domain.services;

import java.util.List;

import com.smart.sotral.transport.application.dtos.ArretRequest;
import com.smart.sotral.transport.application.dtos.ArretResponse;
import java.util.UUID;

public interface ArretService {
    ArretResponse create(ArretRequest request);
    ArretResponse update(UUID trackingId, ArretRequest request);
    ArretResponse get(UUID trackingId);
    List<ArretResponse> list();
    void delete(UUID trackingId);
}
