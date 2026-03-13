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

import com.smart.sotral.transport.application.dtos.TypeLigneRequest;
import com.smart.sotral.transport.application.dtos.TypeLigneResponse;
import com.smart.sotral.transport.domain.services.TypeLigneService;

@RestController
@RequestMapping("/api/types-ligne")
public class TypeLigneController {

    private final TypeLigneService service;

    public TypeLigneController(TypeLigneService service) {
        this.service = service;
    }

    @GetMapping
    public List<TypeLigneResponse> findAll() {
        return service.list();
    }

    @GetMapping("/{id}")
    public TypeLigneResponse findById(@PathVariable Long id) {
        return service.get(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TypeLigneResponse create(@RequestBody TypeLigneRequest typeLigne) {
        return service.create(typeLigne);
    }

    @PutMapping("/{id}")
    public TypeLigneResponse update(@PathVariable Long id, @RequestBody TypeLigneRequest typeLigne) {
        return service.update(id, typeLigne);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
