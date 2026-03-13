package com.smart.sotral.transport.domain.services.servicesImpl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smart.sotral.transport.application.dtos.LigneRequest;
import com.smart.sotral.transport.application.dtos.LigneResponse;
import com.smart.sotral.transport.application.mappers.LigneMapper;
import com.smart.sotral.transport.domain.models.Ligne;
import com.smart.sotral.transport.domain.models.TypeLigne;
import com.smart.sotral.transport.domain.repositories.LigneRepository;
import com.smart.sotral.transport.domain.repositories.TypeLigneRepository;
import com.smart.sotral.transport.domain.services.LigneService;

@Service
@Transactional
public class LigneServiceImpl implements LigneService {

    private final LigneRepository repository;
    private final TypeLigneRepository typeLigneRepository;

    public LigneServiceImpl(LigneRepository repository, TypeLigneRepository typeLigneRepository) {
        this.repository = repository;
        this.typeLigneRepository = typeLigneRepository;
    }

    @Override
    public LigneResponse create(LigneRequest request) {
        Ligne entity = buildEntity(request, new Ligne());
        return LigneMapper.toResponse(repository.save(entity));
    }

    @Override
    public LigneResponse update(Long id, LigneRequest request) {
        Ligne existing = repository.findById(id).orElseThrow();
        Ligne entity = buildEntity(request, existing);
        return LigneMapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public LigneResponse get(Long id) {
        return repository.findById(id).map(LigneMapper::toResponse).orElseThrow();
    }

    @Override
    @Transactional(readOnly = true)
    public List<LigneResponse> list() {
        return repository.findAll().stream().map(LigneMapper::toResponse).toList();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    private Ligne buildEntity(LigneRequest request, Ligne entity) {
        TypeLigne type = typeLigneRepository.findById(request.getTypeLigneId()).orElseThrow();
        entity.setNumero(request.getNumero());
        entity.setDepart(request.getDepart());
        entity.setArrive(request.getArrive());
        entity.setTypeLigne(type);
        return entity;
    }
}
