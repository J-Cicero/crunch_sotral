package com.smart.sotral.transport.domain.services.servicesImpl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smart.sotral.transport.application.dtos.ConducteurRequest;
import com.smart.sotral.transport.application.dtos.ConducteurResponse;
import com.smart.sotral.transport.application.mappers.ConducteurMapper;
import com.smart.sotral.transport.domain.models.Conducteur;
import com.smart.sotral.transport.domain.repositories.ConducteurRepository;
import com.smart.sotral.transport.domain.services.ConducteurService;

@Service
@Transactional
public class ConducteurServiceImpl implements ConducteurService {

    private final ConducteurRepository repository;
    private final ConducteurMapper mapper;

    public ConducteurServiceImpl(ConducteurRepository repository, ConducteurMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ConducteurResponse create(ConducteurRequest request) {
        Conducteur entity = mapper.toEntity(request);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    public ConducteurResponse update(UUID trackingId, ConducteurRequest request) {
        Conducteur existing = repository.findByTrackingId(trackingId).orElseThrow();
        existing.setFirstName(request.getFirstName());
        existing.setLastName(request.getLastName());
        existing.setEmail(request.getEmail());
        // password optional: only update when provided
        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            existing.setPassword(mapper.encodePassword(request.getPassword()));
        }
        existing.setDateEmbauche(request.getDateEmbauche());
        return mapper.toResponse(repository.save(existing));
    }

    @Override
    @Transactional(readOnly = true)
    public ConducteurResponse get(UUID trackingId) {
        return repository.findByTrackingId(trackingId).map(mapper::toResponse).orElseThrow();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ConducteurResponse> list() {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    @Override
    public void delete(UUID trackingId) {
        Conducteur existing = repository.findByTrackingId(trackingId).orElseThrow(() -> new IllegalArgumentException("Conducteur introuvable"));
        repository.delete(existing);
    }
}
