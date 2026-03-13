package com.smart.sotral.transport.domain.services;

import java.util.List;

import com.smart.sotral.transport.application.dtos.PredictionRequest;
import com.smart.sotral.transport.application.dtos.PredictionResponse;
import com.smart.sotral.transport.domain.models.Capteur;
import java.util.UUID;

public interface PredictionService {
    PredictionResponse create(PredictionRequest request);
    PredictionResponse update(UUID trackingId, PredictionRequest request);
    PredictionResponse get(UUID trackingId);
    List<PredictionResponse> list();
    void delete(UUID trackingId);

    void calculerPrediction(UUID vehiculeTrackingId, Capteur capteurActuel);

    List<PredictionResponse> findByArret(UUID arretTrackingId);
    List<PredictionResponse> findByBus(UUID busTrackingId);
}
