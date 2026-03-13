package com.smart.sotral.transport.domain.services;

import java.util.List;

import com.smart.sotral.transport.application.dtos.CapteurRequest;
import com.smart.sotral.transport.application.dtos.CapteurResponse;

public interface CapteurService {
    CapteurResponse create(CapteurRequest request);
    CapteurResponse update(Long id, CapteurRequest request);
    CapteurResponse get(Long id);
    List<CapteurResponse> list();
    List<CapteurResponse> getDernierePositionParVehicule();
    List<CapteurResponse> getDernieres5(Long vehiculeId);
    List<CapteurResponse> getHistorique(Long vehiculeId);
    com.smart.sotral.transport.domain.models.Capteur enregistrerPosition(CapteurRequest request);
    void delete(Long id);
}
