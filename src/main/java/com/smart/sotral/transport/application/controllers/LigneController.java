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

import com.smart.sotral.transport.application.dtos.LigneRequest;
import com.smart.sotral.transport.application.dtos.LigneResponse;
import com.smart.sotral.transport.domain.services.LigneService;

@RestController
@RequestMapping("/api/lignes")
public class LigneController {

    private final LigneService service;

    public LigneController(LigneService service) {
        this.service = service;
    }

    @GetMapping
    public List<LigneResponse> findAll() {
        return service.list();
    }

    @GetMapping("/{id}")
    public LigneResponse findById(@PathVariable Long id) {
        return service.get(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LigneResponse create(@RequestBody LigneRequest ligne) {
        return service.create(ligne);
    }

    @PutMapping("/{id}")
    public LigneResponse update(@PathVariable Long id, @RequestBody LigneRequest ligne) {
        return service.update(id, ligne);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
