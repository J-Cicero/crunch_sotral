package com.smart.sotral.transport.domain.services;

import java.util.List;

import com.smart.sotral.transport.application.dtos.ArretRequest;
import com.smart.sotral.transport.application.dtos.ArretResponse;

public interface ArretService {
    ArretResponse create(ArretRequest request);
    ArretResponse update(Long id, ArretRequest request);
    ArretResponse get(Long id);
    List<ArretResponse> list();
    void delete(Long id);
}
