package com.smart.sotral.transport.domain.services.servicesImpl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smart.sotral.transport.application.dtos.BusRequest;
import com.smart.sotral.transport.application.dtos.BusResponse;
import com.smart.sotral.transport.application.mappers.BusMapper;
import com.smart.sotral.transport.domain.models.Bus;
import com.smart.sotral.transport.domain.models.Ligne;
import com.smart.sotral.transport.domain.repositories.BusRepository;
import com.smart.sotral.transport.domain.repositories.LigneRepository;
import com.smart.sotral.transport.domain.services.BusService;

@Service
@Transactional
public class BusServiceImpl implements BusService {

    private final BusRepository repository;
    private final LigneRepository ligneRepository;

    public BusServiceImpl(BusRepository repository, LigneRepository ligneRepository) {
        this.repository = repository;
        this.ligneRepository = ligneRepository;
    }

    @Override
    public BusResponse create(BusRequest request) {
        Ligne ligne = request.getLigneId() != null ? ligneRepository.findById(request.getLigneId()).orElse(null) : null;
        Bus entity = BusMapper.toEntity(request, ligne);
        return BusMapper.toResponse(repository.save(entity));
    }

    @Override
    public BusResponse update(Long id, BusRequest request) {
        Bus existing = repository.findById(id).orElseThrow();
        existing.setCode(request.getCode());
        if (request.getLigneId() != null) {
            Ligne ligne = ligneRepository.findById(request.getLigneId()).orElseThrow();
            existing.setLigne(ligne);
        } else {
            existing.setLigne(null);
        }
        return BusMapper.toResponse(repository.save(existing));
    }

    @Override
    @Transactional(readOnly = true)
    public BusResponse get(Long id) {
        return repository.findById(id).map(BusMapper::toResponse).orElseThrow();
    }

    @Override
    @Transactional(readOnly = true)
    public List<BusResponse> list() {
        return repository.findAll().stream().map(BusMapper::toResponse).toList();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
