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

import com.smart.sotral.transport.application.dtos.TypeLigneRequest;
import com.smart.sotral.transport.application.dtos.TypeLigneResponse;
import com.smart.sotral.transport.domain.services.TypeLigneService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/types-ligne")
@Tag(name = "TypeLigneController", description = "API de gestion des types de ligne")
public class TypeLigneController {

    private final TypeLigneService service;

    public TypeLigneController(TypeLigneService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Lister les types de ligne", description = "Retourne tous les types de ligne")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Liste retournée")})
    public List<TypeLigneResponse> findAll() {
        return service.list();
    }

    @GetMapping("/{trackingId}")
    @Operation(summary = "Détail type de ligne", description = "Retourne un type de ligne par trackingId")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Type de ligne trouvé"),
            @ApiResponse(responseCode = "404", description = "Type de ligne introuvable")
    })
    public TypeLigneResponse findById(@PathVariable UUID trackingId) {
        return service.get(trackingId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Créer un type de ligne", description = "Crée un type de ligne")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Type de ligne créé"),
            @ApiResponse(responseCode = "400", description = "Requête invalide")
    })
    public TypeLigneResponse create(@RequestBody TypeLigneRequest typeLigne) {
        return service.create(typeLigne);
    }

    @PutMapping("/{trackingId}")
    @Operation(summary = "Mettre à jour un type de ligne", description = "Met à jour un type de ligne existant")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Type de ligne mis à jour"),
            @ApiResponse(responseCode = "404", description = "Type de ligne introuvable")
    })
    public TypeLigneResponse update(@PathVariable UUID trackingId, @RequestBody TypeLigneRequest typeLigne) {
        return service.update(trackingId, typeLigne);
    }

    @DeleteMapping("/{trackingId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Supprimer un type de ligne", description = "Supprime un type de ligne par trackingId")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Type de ligne supprimé"),
            @ApiResponse(responseCode = "404", description = "Type de ligne introuvable")
    })
    public ResponseEntity<Void> delete(@PathVariable UUID trackingId) {
        service.delete(trackingId);
        return ResponseEntity.noContent().build();
    }
}
