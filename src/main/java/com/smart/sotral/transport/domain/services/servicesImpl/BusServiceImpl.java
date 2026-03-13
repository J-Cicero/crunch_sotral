package com.smart.sotral.transport.domain.services.servicesImpl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smart.sotral.transport.application.dtos.BusRequest;
import com.smart.sotral.transport.application.dtos.BusResponse;
import com.smart.sotral.transport.application.mappers.BusMapper;
import com.smart.sotral.transport.domain.models.Bus;
import com.smart.sotral.transport.domain.repositories.BusRepository;
import com.smart.sotral.transport.domain.services.BusService;

@Service
@Transactional
public class BusServiceImpl implements BusService {

    private final BusRepository repository;

    public BusServiceImpl(BusRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public BusResponse create(BusRequest request) {
        Bus entity = BusMapper.toEntity(request);
        return BusMapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional
    public BusResponse update(UUID trackingId, BusRequest request) {
        Bus existing = repository.findByTrackingId(trackingId).orElseThrow();
        existing.setCode(request.getCode());
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
    @Transactional
    public void delete(UUID trackingId) {
        Bus existing = repository.findByTrackingId(trackingId).orElseThrow();
        repository.delete(existing);
    }
}
