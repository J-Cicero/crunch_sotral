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

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.smart.sotral.transport.application.dtos.CapteurRequest;
import com.smart.sotral.transport.application.dtos.CapteurResponse;
import com.smart.sotral.transport.application.dtos.PositionBusDTO;
import com.smart.sotral.transport.domain.services.CapteurService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/capteurs")
@Tag(name = "CapteurController", description = "API de gestion des capteurs")
public class CapteurController {

    private final CapteurService service;

    public CapteurController(CapteurService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Lister les capteurs", description = "Retourne la liste de tous les capteurs")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Liste retournée")})
    public List<CapteurResponse> findAll() {
        return service.list();
    }

    @GetMapping("/{trackingId}")
    @Operation(summary = "Détail capteur", description = "Retourne un capteur par trackingId")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Capteur trouvé"),
            @ApiResponse(responseCode = "404", description = "Capteur introuvable")
    })
    public CapteurResponse findById(@PathVariable UUID trackingId) {
        return service.get(trackingId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Créer un capteur", description = "Crée un capteur")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Capteur créé"),
            @ApiResponse(responseCode = "400", description = "Requête invalide")
    })
    public CapteurResponse create(@RequestBody CapteurRequest capteur) {
        return service.create(capteur);
    }

    @PutMapping("/{trackingId}")
    @Operation(summary = "Mettre à jour un capteur", description = "Met à jour un capteur existant")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Capteur mis à jour"),
            @ApiResponse(responseCode = "404", description = "Capteur introuvable")
    })
    public CapteurResponse update(@PathVariable UUID trackingId, @RequestBody CapteurRequest capteur) {
        return service.update(trackingId, capteur);
    }

    @DeleteMapping("/{trackingId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Supprimer un capteur", description = "Supprime un capteur par trackingId")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Capteur supprimé"),
            @ApiResponse(responseCode = "404", description = "Capteur introuvable")
    })
    public ResponseEntity<Void> delete(@PathVariable UUID trackingId) {
        service.delete(trackingId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/position")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Long> recevoirPosition(@RequestBody CapteurRequest request) {
        var saved = service.enregistrerPosition(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved.getId());
    }

    @GetMapping("/dernieres")
    @Operation(summary = "Positions des bus actifs", description = "Retourne la dernière position GPS de chaque bus dont la mission est ACTIVE.")
    public List<PositionBusDTO> getDernieres() {
        return service.getDernieresPositionsActives();
    }

    @Operation(summary = "Positions par ligne", description = "Filtre les positions uniquement pour les bus d'une ligne identifiée par son trackingId.")
    @GetMapping("/dernieres/ligne/{ligneTrackingId}")
    public List<PositionBusDTO> getDernieresParLigne(@PathVariable UUID ligneTrackingId) {
        return service.getDernieresPositionsParLigne(ligneTrackingId);
    }

    @Operation(summary = "Positions par arrêt", description = "Retourne les bus qui ont des prédictions actives pour un arrêt donné (trackingId).")
    @GetMapping("/dernieres/arret/{arretTrackingId}")
    public List<PositionBusDTO> getDernieresParArret(@PathVariable UUID arretTrackingId) {
        return service.getDernieresPositionsParArret(arretTrackingId);
    }

    @GetMapping("/vehicule/{vehiculeTrackingId}/dernieres5")
    public List<CapteurResponse> getDernieres5(@PathVariable UUID vehiculeTrackingId) {
        return service.getDernieres5(vehiculeTrackingId);
    }

    @GetMapping("/vehicule/{vehiculeTrackingId}/historique")
    public List<CapteurResponse> getHistorique(@PathVariable UUID vehiculeTrackingId) {
        return service.getHistorique(vehiculeTrackingId);
    }
}
