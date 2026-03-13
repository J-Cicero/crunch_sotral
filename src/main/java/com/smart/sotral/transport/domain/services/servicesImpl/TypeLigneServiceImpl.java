package com.smart.sotral.transport.domain.services.servicesImpl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smart.sotral.transport.application.dtos.TypeLigneRequest;
import com.smart.sotral.transport.application.dtos.TypeLigneResponse;
import com.smart.sotral.transport.application.mappers.TypeLigneMapper;
import com.smart.sotral.transport.domain.models.TypeLigne;
import com.smart.sotral.transport.domain.repositories.TypeLigneRepository;
import com.smart.sotral.transport.domain.services.TypeLigneService;

@Service
@Transactional
public class TypeLigneServiceImpl implements TypeLigneService {

    private final TypeLigneRepository repository;

    public TypeLigneServiceImpl(TypeLigneRepository repository) {
        this.repository = repository;
    }

    @Override
    public TypeLigneResponse create(TypeLigneRequest request) {
        TypeLigne entity = new TypeLigne();
        entity.setNom(request.getNom());
        return TypeLigneMapper.toResponse(repository.save(entity));
    }

    @Override
    public TypeLigneResponse update(UUID trackingId, TypeLigneRequest request) {
        TypeLigne existing = repository.findByTrackingId(trackingId).orElseThrow();
        existing.setNom(request.getNom());
        return TypeLigneMapper.toResponse(repository.save(existing));
    }

    @Override
    @Transactional(readOnly = true)
    public TypeLigneResponse get(UUID trackingId) {
        return repository.findByTrackingId(trackingId).map(TypeLigneMapper::toResponse).orElseThrow();
    }

    @Override
    @Transactional(readOnly = true)
    public List<TypeLigneResponse> list() {
        return repository.findAll().stream().map(TypeLigneMapper::toResponse).toList();
    }

    @Override
    public void delete(UUID trackingId) {
        TypeLigne existing = repository.findByTrackingId(trackingId).orElseThrow();
        repository.delete(existing);
    }
}
