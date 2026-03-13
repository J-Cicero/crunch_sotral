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

import com.smart.sotral.transport.application.dtos.BusVehiculeRequest;
import com.smart.sotral.transport.application.dtos.BusVehiculeResponse;
import com.smart.sotral.transport.domain.services.BusVehiculeService;

@RestController
@RequestMapping("/api/bus-vehicules")
public class BusVehiculeController {

    private final BusVehiculeService service;

    public BusVehiculeController(BusVehiculeService service) {
        this.service = service;
    }

    @GetMapping
    public List<BusVehiculeResponse> findAll() {
        return service.list();
    }

    @GetMapping("/{id}")
    public BusVehiculeResponse findById(@PathVariable Long id) {
        return service.get(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BusVehiculeResponse create(@RequestBody BusVehiculeRequest busVehicule) {
        return service.create(busVehicule);
    }

    @PutMapping("/{id}")
    public BusVehiculeResponse update(@PathVariable Long id, @RequestBody BusVehiculeRequest busVehicule) {
        return service.update(id, busVehicule);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
