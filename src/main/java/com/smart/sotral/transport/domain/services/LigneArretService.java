package com.smart.sotral.transport.domain.services;

import java.util.List;

import com.smart.sotral.transport.application.dtos.LigneArretRequest;
import com.smart.sotral.transport.application.dtos.LigneArretResponse;

public interface LigneArretService {
    LigneArretResponse create(LigneArretRequest request);
    LigneArretResponse update(Long id, LigneArretRequest request);
    LigneArretResponse get(Long id);
    List<LigneArretResponse> list();
    void delete(Long id);
}
