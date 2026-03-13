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

import com.smart.sotral.transport.application.dtos.MissionRequest;
import com.smart.sotral.transport.application.dtos.MissionResponse;
import com.smart.sotral.transport.domain.services.MissionService;

@RestController
@RequestMapping("/api/missions")
@Tag(name = "MissionController", description = "API de gestion des missions")
public class MissionController {

    private final MissionService service;

    public MissionController(MissionService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Lister les missions", description = "Retourne toutes les missions")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Liste retournée")})
    public List<MissionResponse> findAll() {
        return service.list();
    }

    @GetMapping("/{trackingId}")
    @Operation(summary = "Détail mission", description = "Retourne une mission par trackingId")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Mission trouvée"),
            @ApiResponse(responseCode = "404", description = "Mission introuvable")
    })
    public MissionResponse findById(@PathVariable UUID trackingId) {
        return service.get(trackingId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Créer une mission", description = "Crée une mission")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Mission créée"),
            @ApiResponse(responseCode = "400", description = "Requête invalide")
    })
    public MissionResponse create(@RequestBody MissionRequest mission) {
        return service.create(mission);
    }

    @PutMapping("/{trackingId}")
    @Operation(summary = "Mettre à jour une mission", description = "Met à jour une mission existante")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Mission mise à jour"),
            @ApiResponse(responseCode = "404", description = "Mission introuvable")
    })
    public MissionResponse update(@PathVariable UUID trackingId, @RequestBody MissionRequest mission) {
        return service.update(trackingId, mission);
    }

    @DeleteMapping("/{trackingId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Supprimer une mission", description = "Supprime une mission par trackingId")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Mission supprimée"),
            @ApiResponse(responseCode = "404", description = "Mission introuvable")
    })
    public ResponseEntity<Void> delete(@PathVariable UUID trackingId) {
        service.delete(trackingId);
        return ResponseEntity.noContent().build();
    }
}
