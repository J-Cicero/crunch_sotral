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

import com.smart.sotral.transport.application.dtos.LigneArretRequest;
import com.smart.sotral.transport.application.dtos.LigneArretResponse;
import com.smart.sotral.transport.domain.services.LigneArretService;

@RestController
@RequestMapping("/api/ligne-arrets")
public class LigneArretController {

    private final LigneArretService service;

    public LigneArretController(LigneArretService service) {
        this.service = service;
    }

    @GetMapping
    public List<LigneArretResponse> findAll() {
        return service.list();
    }

    @GetMapping("/{id}")
    public LigneArretResponse findById(@PathVariable Long id) {
        return service.get(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LigneArretResponse create(@RequestBody LigneArretRequest ligneArret) {
        return service.create(ligneArret);
    }

    @PutMapping("/{id}")
    public LigneArretResponse update(@PathVariable Long id, @RequestBody LigneArretRequest ligneArret) {
        return service.update(id, ligneArret);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
