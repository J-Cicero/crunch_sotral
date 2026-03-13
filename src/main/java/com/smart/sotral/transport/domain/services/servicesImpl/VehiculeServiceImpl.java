package com.smart.sotral.transport.domain.services.servicesImpl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smart.sotral.transport.application.dtos.VehiculeRequest;
import com.smart.sotral.transport.application.dtos.VehiculeResponse;
import com.smart.sotral.transport.application.mappers.VehiculeMapper;
import com.smart.sotral.transport.domain.models.Vehicule;
import com.smart.sotral.transport.domain.repositories.VehiculeRepository;
import com.smart.sotral.transport.domain.services.VehiculeService;

@Service
@Transactional
public class VehiculeServiceImpl implements VehiculeService {

    private final VehiculeRepository repository;

    public VehiculeServiceImpl(VehiculeRepository repository) {
        this.repository = repository;
    }

    @Override
    public VehiculeResponse create(VehiculeRequest request) {
        Vehicule entity = VehiculeMapper.toEntity(request);
        return VehiculeMapper.toResponse(repository.save(entity));
    }

    @Override
    public VehiculeResponse update(UUID trackingId, VehiculeRequest request) {
        Vehicule existing = repository.findByTrackingId(trackingId).orElseThrow();
        existing.setMatricule(request.getMatricule());
        existing.setMarque(request.getMarque());
        existing.setModele(request.getModele());
        existing.setStatut(request.getStatut());
        return VehiculeMapper.toResponse(repository.save(existing));
    }

    @Override
    @Transactional(readOnly = true)
    public VehiculeResponse get(UUID trackingId) {
        return repository.findByTrackingId(trackingId).map(VehiculeMapper::toResponse).orElseThrow();
    }

    @Override
    @Transactional(readOnly = true)
    public List<VehiculeResponse> list() {
        return repository.findAll().stream().map(VehiculeMapper::toResponse).toList();
    }

    @Override
    public void delete(UUID trackingId) {
        Vehicule existing = repository.findByTrackingId(trackingId).orElseThrow();
        repository.delete(existing);
    }
}
