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

import com.smart.sotral.transport.application.dtos.ArretRequest;
import com.smart.sotral.transport.application.dtos.ArretResponse;
import com.smart.sotral.transport.domain.services.ArretService;

@RestController
@RequestMapping("/api/arrets")
public class ArretController {

    private final ArretService service;

    public ArretController(ArretService service) {
        this.service = service;
    }

    @GetMapping
    public List<ArretResponse> findAll() {
        return service.list();
    }

    @GetMapping("/{id}")
    public ArretResponse findById(@PathVariable Long id) {
        return service.get(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ArretResponse create(@RequestBody ArretRequest arret) {
        return service.create(arret);
    }

    @PutMapping("/{id}")
    public ArretResponse update(@PathVariable Long id, @RequestBody ArretRequest arret) {
        return service.update(id, arret);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
