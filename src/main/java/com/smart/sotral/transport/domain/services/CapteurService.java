package com.smart.sotral.transport.domain.services;

import java.util.List;
import java.util.UUID;

import com.smart.sotral.transport.application.dtos.CapteurRequest;
import com.smart.sotral.transport.application.dtos.CapteurResponse;
import com.smart.sotral.transport.application.dtos.PositionBusDTO;

public interface CapteurService {
    CapteurResponse create(CapteurRequest request);
    CapteurResponse update(UUID trackingId, CapteurRequest request);
    CapteurResponse get(UUID trackingId);
    List<CapteurResponse> list();
    List<PositionBusDTO> getDernieresPositionsActives();
    List<PositionBusDTO> getDernieresPositionsParLigne(UUID ligneTrackingId);
    List<PositionBusDTO> getDernieresPositionsParArret(UUID arretTrackingId);
    List<CapteurResponse> getDernieres5(UUID vehiculeTrackingId);
    List<CapteurResponse> getHistorique(UUID vehiculeTrackingId);
    com.smart.sotral.transport.domain.models.Capteur enregistrerPosition(CapteurRequest request);
    void delete(UUID trackingId);
}
