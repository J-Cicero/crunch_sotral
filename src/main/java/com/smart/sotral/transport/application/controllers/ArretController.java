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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.smart.sotral.transport.application.dtos.ArretRequest;
import com.smart.sotral.transport.application.dtos.ArretResponse;
import com.smart.sotral.transport.domain.services.ArretService;

@RestController
@RequestMapping("/api/arrets")
@Tag(name = "ArretController", description = "API de gestion des arrêts")
public class ArretController {

    private final ArretService service;

    public ArretController(ArretService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Lister les arrêts", description = "Retourne la liste de tous les arrêts")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Liste retournée")})
    public List<ArretResponse> findAll() {
        return service.list();
    }

    @GetMapping("/{trackingId}")
    @Operation(summary = "Détail arrêt", description = "Retourne un arrêt par identifiant")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Arrêt trouvé"),
            @ApiResponse(responseCode = "404", description = "Arrêt introuvable")
    })
    public ArretResponse findById(@PathVariable UUID trackingId) {
        return service.get(trackingId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ArretResponse create(@RequestBody ArretRequest arret) {
        return service.create(arret);
    }

    @PutMapping("/{trackingId}")
    @Operation(summary = "Mettre à jour un arrêt", description = "Met à jour un arrêt existant")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Arrêt mis à jour"),
            @ApiResponse(responseCode = "404", description = "Arrêt introuvable")
    })
    public ArretResponse update(@PathVariable UUID trackingId, @RequestBody ArretRequest arret) {
        return service.update(trackingId, arret);
    }

    @DeleteMapping("/{trackingId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Supprimer un arrêt", description = "Supprime un arrêt par trackingId")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Arrêt supprimé"),
            @ApiResponse(responseCode = "404", description = "Arrêt introuvable")
    })
    public ResponseEntity<Void> delete(@PathVariable UUID trackingId) {
        service.delete(trackingId);
        return ResponseEntity.noContent().build();
    }
}
