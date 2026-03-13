package com.smart.sotral.transport.domain.services.servicesImpl;

import java.util.List;

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
    public MissionResponse update(Long id, MissionRequest request) {
        Mission existing = repository.findById(id).orElseThrow();
        Mission entity = buildEntity(request, existing);
        return MissionMapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public MissionResponse get(Long id) {
        return repository.findById(id).map(MissionMapper::toResponse).orElseThrow();
    }

    @Override
    @Transactional(readOnly = true)
    public List<MissionResponse> list() {
        return repository.findAll().stream().map(MissionMapper::toResponse).toList();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    private Mission buildEntity(MissionRequest request, Mission entity) {
        BusVehicule busVehicule = busVehiculeRepository.findById(request.getBusVehiculeId()).orElseThrow();
        Conducteur conducteur = conducteurRepository.findById(request.getConducteurId()).orElseThrow();
        entity.setBusVehicule(busVehicule);
        entity.setConducteur(conducteur);
        entity.setDateDebut(request.getDateDebut());
        entity.setDateFin(request.getDateFin());
        entity.setStatut(request.getStatut());
        return entity;
    }
}
