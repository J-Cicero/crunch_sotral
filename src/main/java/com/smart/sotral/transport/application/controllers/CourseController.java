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

import com.smart.sotral.transport.application.dtos.CourseRequest;
import com.smart.sotral.transport.application.dtos.CourseResponse;
import com.smart.sotral.transport.domain.services.CourseService;

@RestController
@RequestMapping("/api/courses")
@Tag(name = "CourseController", description = "API de gestion des courses")
public class CourseController {

    private final CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Lister les courses", description = "Retourne la liste de toutes les courses")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Liste retournée")})
    public List<CourseResponse> findAll() {
        return service.list();
    }

    @GetMapping("/{trackingId}")
    @Operation(summary = "Détail course", description = "Retourne une course par trackingId")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Course trouvée"),
            @ApiResponse(responseCode = "404", description = "Course introuvable")
    })
    public CourseResponse findById(@PathVariable UUID trackingId) {
        return service.get(trackingId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CourseResponse create(@RequestBody CourseRequest course) {
        return service.create(course);
    }

    @PutMapping("/{trackingId}")
    public CourseResponse update(@PathVariable UUID trackingId, @RequestBody CourseRequest course) {
        return service.update(trackingId, course);
    }

    @DeleteMapping("/{trackingId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable UUID trackingId) {
        service.delete(trackingId);
        return ResponseEntity.noContent().build();
    }
}
