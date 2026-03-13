package com.smart.sotral.transport.domain.services.servicesImpl;

import java.util.List;
import java.util.UUID;

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
        Ligne ligne = request.getLigneTrackingId() != null ? ligneRepository.findByTrackingId(request.getLigneTrackingId()).orElse(null) : null;
        Bus entity = BusMapper.toEntity(request, ligne);
        return BusMapper.toResponse(repository.save(entity));
    }

    @Override
    public BusResponse update(UUID trackingId, BusRequest request) {
        Bus existing = repository.findByTrackingId(trackingId).orElseThrow();
        existing.setCode(request.getCode());
        if (request.getLigneTrackingId() != null) {
            Ligne ligne = ligneRepository.findByTrackingId(request.getLigneTrackingId()).orElseThrow();
            existing.setLigne(ligne);
        } else {
            existing.setLigne(null);
        }
        return BusMapper.toResponse(repository.save(existing));
    }

    @Override
    @Transactional(readOnly = true)
    public BusResponse get(UUID trackingId) {
        return repository.findByTrackingId(trackingId).map(BusMapper::toResponse).orElseThrow();
    }

    @Override
    @Transactional(readOnly = true)
    public List<BusResponse> list() {
        return repository.findAll().stream().map(BusMapper::toResponse).toList();
    }

    @Override
    public void delete(UUID trackingId) {
        Bus existing = repository.findByTrackingId(trackingId).orElseThrow();
        repository.delete(existing);
    }
}
