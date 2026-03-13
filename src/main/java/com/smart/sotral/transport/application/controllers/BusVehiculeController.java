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

import com.smart.sotral.transport.application.dtos.BusVehiculeRequest;
import com.smart.sotral.transport.application.dtos.BusVehiculeResponse;
import com.smart.sotral.transport.domain.services.BusVehiculeService;

@RestController
@RequestMapping("/api/bus-vehicules")
@Tag(name = "BusVehiculeController", description = "API d'association bus/vehicule")
public class BusVehiculeController {

    private final BusVehiculeService service;

    public BusVehiculeController(BusVehiculeService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Lister les associations", description = "Retourne toutes les associations bus-vehicule")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Liste retournée")})
    public List<BusVehiculeResponse> findAll() {
        return service.list();
    }

    @GetMapping("/{trackingId}")
    @Operation(summary = "Détail association", description = "Retourne une association bus-vehicule par trackingId")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Association trouvée"),
            @ApiResponse(responseCode = "404", description = "Association introuvable")
    })
    public BusVehiculeResponse findById(@PathVariable UUID trackingId) {
        return service.get(trackingId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Créer une association", description = "Crée une association bus-vehicule")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Association créée"),
            @ApiResponse(responseCode = "400", description = "Requête invalide")
    })
    public BusVehiculeResponse create(@RequestBody BusVehiculeRequest busVehicule) {
        return service.create(busVehicule);
    }

    @PutMapping("/{trackingId}")
    @Operation(summary = "Mettre à jour une association", description = "Met à jour une association bus-vehicule")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Association mise à jour"),
            @ApiResponse(responseCode = "404", description = "Association introuvable")
    })
    public BusVehiculeResponse update(@PathVariable UUID trackingId, @RequestBody BusVehiculeRequest busVehicule) {
        return service.update(trackingId, busVehicule);
    }

    @DeleteMapping("/{trackingId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Supprimer une association", description = "Supprime une association bus-vehicule par trackingId")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Association supprimée"),
            @ApiResponse(responseCode = "404", description = "Association introuvable")
    })
    public ResponseEntity<Void> delete(@PathVariable UUID trackingId) {
        service.delete(trackingId);
        return ResponseEntity.noContent().build();
    }
}
