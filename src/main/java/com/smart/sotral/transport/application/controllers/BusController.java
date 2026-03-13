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

import com.smart.sotral.transport.application.dtos.BusRequest;
import com.smart.sotral.transport.application.dtos.BusResponse;
import com.smart.sotral.transport.domain.services.BusService;

@RestController
@RequestMapping("/api/bus")
public class BusController {

    private final BusService service;

    public BusController(BusService service) {
        this.service = service;
    }

    @GetMapping
    public List<BusResponse> findAll() {
        return service.list();
    }

    @GetMapping("/{id}")
    public BusResponse findById(@PathVariable Long id) {
        return service.get(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BusResponse create(@RequestBody BusRequest bus) {
        return service.create(bus);
    }

    @PutMapping("/{id}")
    public BusResponse update(@PathVariable Long id, @RequestBody BusRequest bus) {
        return service.update(id, bus);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
