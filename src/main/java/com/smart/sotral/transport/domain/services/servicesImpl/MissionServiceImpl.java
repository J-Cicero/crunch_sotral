package com.smart.sotral.transport.domain.services.servicesImpl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smart.sotral.transport.application.dtos.MissionRequest;
import com.smart.sotral.transport.application.dtos.MissionResponse;
import com.smart.sotral.transport.application.mappers.MissionMapper;
import com.smart.sotral.transport.domain.models.BusVehicule;
import com.smart.sotral.transport.domain.models.Conducteur;
import com.smart.sotral.transport.domain.models.Mission;
import com.smart.sotral.transport.domain.repositories.BusVehiculeRepository;
import com.smart.sotral.transport.domain.repositories.ConducteurRepository;
import com.smart.sotral.transport.domain.repositories.MissionRepository;
import com.smart.sotral.transport.domain.services.MissionService;

@Service
@Transactional
public class MissionServiceImpl implements MissionService {

    private final MissionRepository repository;
    private final BusVehiculeRepository busVehiculeRepository;
    private final ConducteurRepository conducteurRepository;

    public MissionServiceImpl(MissionRepository repository, BusVehiculeRepository busVehiculeRepository, ConducteurRepository conducteurRepository) {
        this.repository = repository;
        this.busVehiculeRepository = busVehiculeRepository;
        this.conducteurRepository = conducteurRepository;
    }

    @Override
    public MissionResponse create(MissionRequest request) {
        Mission entity = buildEntity(request, new Mission());
        return MissionMapper.toResponse(repository.save(entity));
    }

    @Override
    public MissionResponse update(UUID trackingId, MissionRequest request) {
        Mission existing = repository.findByTrackingId(trackingId).orElseThrow();
        Mission entity = buildEntity(request, existing);
        return MissionMapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public MissionResponse get(UUID trackingId) {
        return repository.findByTrackingId(trackingId).map(MissionMapper::toResponse).orElseThrow();
    }

    @Override
    @Transactional(readOnly = true)
    public List<MissionResponse> list() {
        return repository.findAll().stream().map(MissionMapper::toResponse).toList();
    }

    @Override
    public void delete(UUID trackingId) {
        Mission existing = repository.findByTrackingId(trackingId).orElseThrow();
        repository.delete(existing);
    }

    private Mission buildEntity(MissionRequest request, Mission entity) {
        BusVehicule busVehicule = busVehiculeRepository.findByTrackingId(request.getBusVehiculeTrackingId()).orElseThrow();
        Conducteur conducteur = conducteurRepository.findByTrackingId(request.getConducteurTrackingId()).orElseThrow();
        entity.setBusVehicule(busVehicule);
        entity.setConducteur(conducteur);
        entity.setDateDebut(request.getDateDebut());
        entity.setDateFin(request.getDateFin());
        entity.setStatut(request.getStatut());
        return entity;
    }
}
