package com.smart.sotral.transport.domain.services;

import java.util.List;

import com.smart.sotral.transport.application.dtos.LigneRequest;
import com.smart.sotral.transport.application.dtos.LigneResponse;

public interface LigneService {
    LigneResponse create(LigneRequest request);
    LigneResponse update(Long id, LigneRequest request);
    LigneResponse get(Long id);
    List<LigneResponse> list();
    void delete(Long id);
}
