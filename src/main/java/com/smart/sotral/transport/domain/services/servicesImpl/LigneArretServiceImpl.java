package com.smart.sotral.transport.domain.services.servicesImpl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smart.sotral.transport.application.dtos.LigneArretRequest;
import com.smart.sotral.transport.application.dtos.LigneArretResponse;
import com.smart.sotral.transport.application.mappers.LigneArretMapper;
import com.smart.sotral.transport.domain.models.Arret;
import com.smart.sotral.transport.domain.models.Ligne;
import com.smart.sotral.transport.domain.models.LigneArret;
import com.smart.sotral.transport.domain.repositories.ArretRepository;
import com.smart.sotral.transport.domain.repositories.LigneArretRepository;
import com.smart.sotral.transport.domain.repositories.LigneRepository;
import com.smart.sotral.transport.domain.services.LigneArretService;

@Service
@Transactional
public class LigneArretServiceImpl implements LigneArretService {

    private final LigneArretRepository repository;
    private final LigneRepository ligneRepository;
    private final ArretRepository arretRepository;

    public LigneArretServiceImpl(LigneArretRepository repository, LigneRepository ligneRepository, ArretRepository arretRepository) {
        this.repository = repository;
        this.ligneRepository = ligneRepository;
        this.arretRepository = arretRepository;
    }

    @Override
    public LigneArretResponse create(LigneArretRequest request) {
        LigneArret entity = buildEntity(request, new LigneArret());
        return LigneArretMapper.toResponse(repository.save(entity));
    }

    @Override
    public LigneArretResponse update(UUID trackingId, LigneArretRequest request) {
        LigneArret existing = repository.findByTrackingId(trackingId).orElseThrow();
        LigneArret entity = buildEntity(request, existing);
        return LigneArretMapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public LigneArretResponse get(UUID trackingId) {
        return repository.findByTrackingId(trackingId).map(LigneArretMapper::toResponse).orElseThrow();
    }

    @Override
    @Transactional(readOnly = true)
    public List<LigneArretResponse> list() {
        return repository.findAll().stream().map(LigneArretMapper::toResponse).toList();
    }

    @Override
    public void delete(UUID trackingId) {
        LigneArret existing = repository.findByTrackingId(trackingId).orElseThrow();
        repository.delete(existing);
    }

    private LigneArret buildEntity(LigneArretRequest request, LigneArret entity) {
        Ligne ligne = ligneRepository.findByTrackingId(request.getLigneTrackingId()).orElseThrow();
        Arret arret = arretRepository.findByTrackingId(request.getArretTrackingId()).orElseThrow();
        entity.setLigne(ligne);
        entity.setArret(arret);
        entity.setOrdre(request.getOrdre());
        return entity;
    }
}
