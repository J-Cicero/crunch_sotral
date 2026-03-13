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

import com.smart.sotral.transport.application.dtos.CapteurRequest;
import com.smart.sotral.transport.application.dtos.CapteurResponse;
import com.smart.sotral.transport.domain.services.CapteurService;

@RestController
@RequestMapping("/api/capteurs")
public class CapteurController {

    private final CapteurService service;

    public CapteurController(CapteurService service) {
        this.service = service;
    }

    @GetMapping
    public List<CapteurResponse> findAll() {
        return service.list();
    }

    @GetMapping("/{id}")
    public CapteurResponse findById(@PathVariable Long id) {
        return service.get(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CapteurResponse create(@RequestBody CapteurRequest capteur) {
        return service.create(capteur);
    }

    @PutMapping("/{id}")
    public CapteurResponse update(@PathVariable Long id, @RequestBody CapteurRequest capteur) {
        return service.update(id, capteur);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/position")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Long> recevoirPosition(@RequestBody CapteurRequest request) {
        var saved = service.enregistrerPosition(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved.getId());
    }

    @GetMapping("/dernieres")
    public List<CapteurResponse> getDernieres() {
        return service.getDernierePositionParVehicule();
    }

    @GetMapping("/vehicule/{id}/dernieres5")
    public List<CapteurResponse> getDernieres5(@PathVariable Long id) {
        return service.getDernieres5(id);
    }

    @GetMapping("/vehicule/{id}/historique")
    public List<CapteurResponse> getHistorique(@PathVariable Long id) {
        return service.getHistorique(id);
    }
}
