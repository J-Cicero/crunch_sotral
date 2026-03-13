package com.smart.sotral.transport.domain.services;

import java.util.List;

import com.smart.sotral.transport.application.dtos.PredictionRequest;
import com.smart.sotral.transport.application.dtos.PredictionResponse;
import com.smart.sotral.transport.domain.models.Capteur;

public interface PredictionService {
    PredictionResponse create(PredictionRequest request);
    PredictionResponse update(Long id, PredictionRequest request);
    PredictionResponse get(Long id);
    List<PredictionResponse> list();
    void delete(Long id);

    void calculerPrediction(Long vehiculeId, Capteur capteurActuel);

    List<PredictionResponse> findByArret(Long arretId);
    List<PredictionResponse> findByBus(Long busId);
}
