package com.smart.sotral.transport.application.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.smart.sotral.transport.application.dtos.PredictionRequest;
import com.smart.sotral.transport.application.dtos.PredictionResponse;
import com.smart.sotral.transport.domain.services.PredictionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/predictions")
@Tag(name = "PredictionController", description = "API des prédictions d'arrivée bus")
public class PredictionController {

    private final PredictionService service;

    public PredictionController(PredictionService service) {
        this.service = service;
    }

    @GetMapping
    public List<PredictionResponse> findAll() {
        return service.list();
    }

    @GetMapping("/{trackingId}")
    @Operation(summary = "Détail prédiction", description = "Retourne une prédiction par trackingId")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Prédiction trouvée"),
            @ApiResponse(responseCode = "404", description = "Prédiction introuvable")
    })
    public PredictionResponse findById(@PathVariable UUID trackingId) {
        return service.get(trackingId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PredictionResponse create(@RequestBody PredictionRequest prediction) {
        return service.create(prediction);
    }

    @PutMapping("/{trackingId}")
    public PredictionResponse update(@PathVariable UUID trackingId, @RequestBody PredictionRequest prediction) {
        return service.update(trackingId, prediction);
    }

    @DeleteMapping("/{trackingId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable UUID trackingId) {
        service.delete(trackingId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/arret/{arretTrackingId}")
    public List<PredictionResponse> findByArret(@PathVariable UUID arretTrackingId) {
        return service.findByArret(arretTrackingId);
    }

    @GetMapping("/bus/{busTrackingId}")
    public List<PredictionResponse> findByBus(@PathVariable UUID busTrackingId) {
        return service.findByBus(busTrackingId);
    }
}
