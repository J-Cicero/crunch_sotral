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

import com.smart.sotral.transport.application.dtos.BusRequest;
import com.smart.sotral.transport.application.dtos.BusResponse;
import com.smart.sotral.transport.domain.services.BusService;

@RestController
@RequestMapping("/api/bus")
@Tag(name = "BusController", description = "API de gestion des bus")
public class BusController {

    private final BusService service;

    public BusController(BusService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Lister les bus", description = "Retourne la liste de tous les bus")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Liste retournée")})
    public List<BusResponse> findAll() {
        return service.list();
    }

    @GetMapping("/{trackingId}")
    @Operation(summary = "Détail bus", description = "Retourne un bus par identifiant")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Bus trouvé"),
            @ApiResponse(responseCode = "404", description = "Bus introuvable")
    })
    public BusResponse findById(@PathVariable UUID trackingId) {
        return service.get(trackingId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Créer un bus", description = "Crée un bus")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Bus créé"),
            @ApiResponse(responseCode = "400", description = "Requête invalide")
    })
    public BusResponse create(@RequestBody BusRequest bus) {
        return service.create(bus);
    }

    @PutMapping("/{trackingId}")
    @Operation(summary = "Mettre à jour un bus", description = "Met à jour un bus existant")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Bus mis à jour"),
            @ApiResponse(responseCode = "404", description = "Bus introuvable")
    })
    public BusResponse update(@PathVariable UUID trackingId, @RequestBody BusRequest bus) {
        return service.update(trackingId, bus);
    }

    @DeleteMapping("/{trackingId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Supprimer un bus", description = "Supprime un bus par identifiant")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Bus supprimé"),
            @ApiResponse(responseCode = "404", description = "Bus introuvable")
    })
    public ResponseEntity<Void> delete(@PathVariable UUID trackingId) {
        service.delete(trackingId);
        return ResponseEntity.noContent().build();
    }
}
