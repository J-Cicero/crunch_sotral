package com.smart.sotral.transport.domain.services.servicesImpl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smart.sotral.transport.application.dtos.ArretRequest;
import com.smart.sotral.transport.application.dtos.ArretResponse;
import com.smart.sotral.transport.application.mappers.ArretMapper;
import com.smart.sotral.transport.domain.models.Arret;
import com.smart.sotral.transport.domain.repositories.ArretRepository;
import com.smart.sotral.transport.domain.services.ArretService;

@Service
@Transactional
public class ArretServiceImpl implements ArretService {

    private final ArretRepository repository;

    public ArretServiceImpl(ArretRepository repository) {
        this.repository = repository;
    }

    @Override
    public ArretResponse create(ArretRequest request) {
        Arret entity = new Arret();
        entity.setNom(request.getNom());
        entity.setLatitude(request.getLatitude());
        entity.setLongitude(request.getLongitude());
        return ArretMapper.toResponse(repository.save(entity));
    }

    @Override
    public ArretResponse update(Long id, ArretRequest request) {
        Arret existing = repository.findById(id).orElseThrow();
        existing.setNom(request.getNom());
        existing.setLatitude(request.getLatitude());
        existing.setLongitude(request.getLongitude());
        return ArretMapper.toResponse(repository.save(existing));
    }

    @Override
    @Transactional(readOnly = true)
    public ArretResponse get(Long id) {
        return repository.findById(id).map(ArretMapper::toResponse).orElseThrow();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ArretResponse> list() {
        return repository.findAll().stream().map(ArretMapper::toResponse).toList();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
