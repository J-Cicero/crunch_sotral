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

import com.smart.sotral.transport.application.dtos.LigneArretRequest;
import com.smart.sotral.transport.application.dtos.LigneArretResponse;
import com.smart.sotral.transport.domain.services.LigneArretService;

@RestController
@RequestMapping("/api/ligne-arrets")
@Tag(name = "LigneArretController", description = "API de gestion des arrêts sur lignes")
public class LigneArretController {

    private final LigneArretService service;

    public LigneArretController(LigneArretService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Lister les liaisons ligne-arrêt", description = "Retourne toutes les associations")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Liste retournée")})
    public List<LigneArretResponse> findAll() {
        return service.list();
    }

    @GetMapping("/{trackingId}")
    @Operation(summary = "Détail liaison", description = "Retourne une liaison par trackingId")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Liaison trouvée"),
            @ApiResponse(responseCode = "404", description = "Liaison introuvable")
    })
    public LigneArretResponse findById(@PathVariable UUID trackingId) {
        return service.get(trackingId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Créer une liaison ligne-arrêt", description = "Crée une nouvelle association")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Liaison créée"),
            @ApiResponse(responseCode = "400", description = "Requête invalide")
    })
    public LigneArretResponse create(@RequestBody LigneArretRequest ligneArret) {
        return service.create(ligneArret);
    }

    @PutMapping("/{trackingId}")
    @Operation(summary = "Mettre à jour une liaison", description = "Met à jour une association existante")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Liaison mise à jour"),
            @ApiResponse(responseCode = "404", description = "Liaison introuvable")
    })
    public LigneArretResponse update(@PathVariable UUID trackingId, @RequestBody LigneArretRequest ligneArret) {
        return service.update(trackingId, ligneArret);
    }

    @DeleteMapping("/{trackingId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Supprimer une liaison", description = "Supprime une association par trackingId")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Liaison supprimée"),
            @ApiResponse(responseCode = "404", description = "Liaison introuvable")
    })
    public ResponseEntity<Void> delete(@PathVariable UUID trackingId) {
        service.delete(trackingId);
        return ResponseEntity.noContent().build();
    }
}
