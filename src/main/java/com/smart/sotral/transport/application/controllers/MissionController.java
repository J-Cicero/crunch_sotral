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

import com.smart.sotral.transport.application.dtos.MissionRequest;
import com.smart.sotral.transport.application.dtos.MissionResponse;
import com.smart.sotral.transport.domain.services.MissionService;

@RestController
@RequestMapping("/api/missions")
public class MissionController {

    private final MissionService service;

    public MissionController(MissionService service) {
        this.service = service;
    }

    @GetMapping
    public List<MissionResponse> findAll() {
        return service.list();
    }

    @GetMapping("/{id}")
    public MissionResponse findById(@PathVariable Long id) {
        return service.get(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MissionResponse create(@RequestBody MissionRequest mission) {
        return service.create(mission);
    }

    @PutMapping("/{id}")
    public MissionResponse update(@PathVariable Long id, @RequestBody MissionRequest mission) {
        return service.update(id, mission);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
