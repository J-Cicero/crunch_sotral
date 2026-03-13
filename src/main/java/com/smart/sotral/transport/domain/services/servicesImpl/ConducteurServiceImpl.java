package com.smart.sotral.transport.domain.services.servicesImpl;

import java.util.List;

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

    public ConducteurServiceImpl(ConducteurRepository repository) {
        this.repository = repository;
    }

    @Override
    public ConducteurResponse create(ConducteurRequest request) {
        Conducteur entity = ConducteurMapper.toEntity(request);
        return ConducteurMapper.toResponse(repository.save(entity));
    }

    @Override
    public ConducteurResponse update(Long id, ConducteurRequest request) {
        Conducteur existing = repository.findById(id).orElseThrow();
        existing.setEmail(request.getEmail());
        existing.setNumeroPermis(request.getNumeroPermis());
        existing.setDateEmbauche(request.getDateEmbauche());
        return ConducteurMapper.toResponse(repository.save(existing));
    }

    @Override
    @Transactional(readOnly = true)
    public ConducteurResponse get(Long id) {
        return repository.findById(id).map(ConducteurMapper::toResponse).orElseThrow();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ConducteurResponse> list() {
        return repository.findAll().stream().map(ConducteurMapper::toResponse).toList();
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Conducteur introuvable");
        }
        repository.deleteById(id);
    }
}
