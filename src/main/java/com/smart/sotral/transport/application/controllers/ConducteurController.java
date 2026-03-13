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

import com.smart.sotral.transport.application.dtos.ConducteurRequest;
import com.smart.sotral.transport.application.dtos.ConducteurResponse;
import com.smart.sotral.transport.domain.services.ConducteurService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/conducteurs")
@Tag(name = "ConducteurController", description = "API de gestion des conducteurs")
public class ConducteurController {

    private final ConducteurService service;

    public ConducteurController(ConducteurService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Lister les conducteurs", description = "Récupère la liste de tous les conducteurs")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Liste retournée"),
    })
    public List<ConducteurResponse> findAll() {
        return service.list();
    }

    @GetMapping("/{trackingId}")
    @Operation(summary = "Détail conducteur", description = "Récupère un conducteur par trackingId")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Conducteur trouvé"),
            @ApiResponse(responseCode = "404", description = "Conducteur introuvable")
    })
    public ConducteurResponse findById(@PathVariable UUID trackingId) {
        return service.get(trackingId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Créer un conducteur", description = "Crée un conducteur avec ses informations et son compte utilisateur")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Conducteur créé"),
            @ApiResponse(responseCode = "400", description = "Requête invalide")
    })
    public ConducteurResponse create(@RequestBody ConducteurRequest conducteur) {
        return service.create(conducteur);
    }

    @PutMapping("/{trackingId}")
    @Operation(summary = "Mettre à jour un conducteur", description = "Met à jour les informations d'un conducteur existant")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Conducteur mis à jour"),
            @ApiResponse(responseCode = "404", description = "Conducteur introuvable")
    })
    public ConducteurResponse update(@PathVariable UUID trackingId, @RequestBody ConducteurRequest conducteur) {
        return service.update(trackingId, conducteur);
    }

    @DeleteMapping("/{trackingId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Supprimer un conducteur", description = "Supprime un conducteur par trackingId")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Conducteur supprimé"),
            @ApiResponse(responseCode = "404", description = "Conducteur introuvable")
    })
    public ResponseEntity<Void> delete(@PathVariable UUID trackingId) {
        service.delete(trackingId);
        return ResponseEntity.noContent().build();
    }
}
