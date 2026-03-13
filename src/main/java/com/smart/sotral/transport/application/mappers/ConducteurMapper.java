package com.smart.sotral.transport.application.mappers;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.smart.sotral.transport.application.dtos.ConducteurRequest;
import com.smart.sotral.transport.application.dtos.ConducteurResponse;
import com.smart.sotral.transport.domain.models.Conducteur;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ConducteurMapper {

    private final PasswordEncoder passwordEncoder;

    public Conducteur toEntity(ConducteurRequest request) {
        Conducteur c = new Conducteur();
        c.setFirstName(request.getFirstName());
        c.setLastName(request.getLastName());
        c.setEmail(request.getEmail());
        if (request.getPassword() == null || request.getPassword().isBlank()) {
            throw new IllegalArgumentException("Le mot de passe du conducteur est obligatoire");
        }
        c.setPassword(passwordEncoder.encode(request.getPassword()));
        c.setActive(true);
        c.setDateEmbauche(request.getDateEmbauche());
        return c;
    }

    public String encodePassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    public ConducteurResponse toResponse(Conducteur entity) {
        return ConducteurResponse.builder()
                .id(entity.getId())
                .trackingId(entity.getTrackingId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .dateEmbauche(entity.getDateEmbauche())
                .active(entity.isActive())
                .build();
    }
}
