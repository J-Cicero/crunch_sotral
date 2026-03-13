package com.smart.sotral.transport.application.controllers;

import java.util.List;

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

@RestController
@RequestMapping("/api/predictions")
public class PredictionController {

    private final PredictionService service;

    public PredictionController(PredictionService service) {
        this.service = service;
    }

    @GetMapping
    public List<PredictionResponse> findAll() {
        return service.list();
    }

    @GetMapping("/{id}")
    public PredictionResponse findById(@PathVariable Long id) {
        return service.get(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PredictionResponse create(@RequestBody PredictionRequest prediction) {
        return service.create(prediction);
    }

    @PutMapping("/{id}")
    public PredictionResponse update(@PathVariable Long id, @RequestBody PredictionRequest prediction) {
        return service.update(id, prediction);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/arret/{arretId}")
    public List<PredictionResponse> findByArret(@PathVariable Long arretId) {
        return service.findByArret(arretId);
    }

    @GetMapping("/bus/{busId}")
    public List<PredictionResponse> findByBus(@PathVariable Long busId) {
        return service.findByBus(busId);
    }
}
