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

import com.smart.sotral.transport.application.dtos.LigneRequest;
import com.smart.sotral.transport.application.dtos.LigneResponse;
import com.smart.sotral.transport.domain.services.LigneService;

@RestController
@RequestMapping("/api/lignes")
@Tag(name = "LigneController", description = "API de gestion des lignes")
public class LigneController {

    private final LigneService service;

    public LigneController(LigneService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Lister les lignes", description = "Retourne la liste des lignes")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Liste retournée")})
    public List<LigneResponse> findAll() {
        return service.list();
    }

    @GetMapping("/{trackingId}")
    @Operation(summary = "Détail ligne", description = "Retourne une ligne par trackingId")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ligne trouvée"),
            @ApiResponse(responseCode = "404", description = "Ligne introuvable")
    })
    public LigneResponse findById(@PathVariable UUID trackingId) {
        return service.get(trackingId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Créer une ligne", description = "Crée une nouvelle ligne")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Ligne créée"),
            @ApiResponse(responseCode = "400", description = "Requête invalide")
    })
    public LigneResponse create(@RequestBody LigneRequest ligne) {
        return service.create(ligne);
    }

    @PutMapping("/{trackingId}")
    @Operation(summary = "Mettre à jour une ligne", description = "Met à jour une ligne existante")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ligne mise à jour"),
            @ApiResponse(responseCode = "404", description = "Ligne introuvable")
    })
    public LigneResponse update(@PathVariable UUID trackingId, @RequestBody LigneRequest ligne) {
        return service.update(trackingId, ligne);
    }

    @DeleteMapping("/{trackingId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Supprimer une ligne", description = "Supprime une ligne par trackingId")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Ligne supprimée"),
            @ApiResponse(responseCode = "404", description = "Ligne introuvable")
    })
    public ResponseEntity<Void> delete(@PathVariable UUID trackingId) {
        service.delete(trackingId);
        return ResponseEntity.noContent().build();
    }
}
