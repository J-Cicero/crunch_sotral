package com.smart.sotral.transport.domain.services;

import java.util.List;

import com.smart.sotral.transport.application.dtos.TypeLigneRequest;
import com.smart.sotral.transport.application.dtos.TypeLigneResponse;

public interface TypeLigneService {
    TypeLigneResponse create(TypeLigneRequest request);
    TypeLigneResponse update(Long id, TypeLigneRequest request);
    TypeLigneResponse get(Long id);
    List<TypeLigneResponse> list();
    void delete(Long id);
}
