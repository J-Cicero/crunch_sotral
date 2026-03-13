package com.smart.sotral.transport.domain.services;

import java.util.List;

import com.smart.sotral.transport.application.dtos.ConducteurRequest;
import com.smart.sotral.transport.application.dtos.ConducteurResponse;

public interface ConducteurService {
    ConducteurResponse create(ConducteurRequest request);
    ConducteurResponse update(Long id, ConducteurRequest request);
    ConducteurResponse get(Long id);
    List<ConducteurResponse> list();
    void delete(Long id);
}
