package com.smart.sotral.transport.domain.services;

import java.util.List;

import com.smart.sotral.transport.application.dtos.LigneArretRequest;
import com.smart.sotral.transport.application.dtos.LigneArretResponse;
import java.util.UUID;

public interface LigneArretService {
    LigneArretResponse create(LigneArretRequest request);
    LigneArretResponse update(UUID trackingId, LigneArretRequest request);
    LigneArretResponse get(UUID trackingId);
    List<LigneArretResponse> list();
    void delete(UUID trackingId);
}
