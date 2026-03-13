package com.smart.sotral.transport.domain.services;

import java.util.List;

import com.smart.sotral.transport.application.dtos.TypeLigneRequest;
import com.smart.sotral.transport.application.dtos.TypeLigneResponse;
import java.util.UUID;

public interface TypeLigneService {
    TypeLigneResponse create(TypeLigneRequest request);
    TypeLigneResponse update(UUID trackingId, TypeLigneRequest request);
    TypeLigneResponse get(UUID trackingId);
    List<TypeLigneResponse> list();
    void delete(UUID trackingId);
}
