package com.smart.sotral.transport.domain.services.servicesImpl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smart.sotral.transport.application.dtos.BusVehiculeRequest;
import com.smart.sotral.transport.application.dtos.BusVehiculeResponse;
import com.smart.sotral.transport.application.mappers.BusVehiculeMapper;
import com.smart.sotral.transport.domain.models.Bus;
import com.smart.sotral.transport.domain.models.BusVehicule;
import com.smart.sotral.transport.domain.models.Vehicule;
import com.smart.sotral.transport.domain.repositories.BusRepository;
import com.smart.sotral.transport.domain.repositories.BusVehiculeRepository;
import com.smart.sotral.transport.domain.repositories.VehiculeRepository;
import com.smart.sotral.transport.domain.services.BusVehiculeService;

@Service
@Transactional
public class BusVehiculeServiceImpl implements BusVehiculeService {

    private final BusVehiculeRepository repository;
    private final BusRepository busRepository;
    private final VehiculeRepository vehiculeRepository;

    public BusVehiculeServiceImpl(BusVehiculeRepository repository, BusRepository busRepository, VehiculeRepository vehiculeRepository) {
        this.repository = repository;
        this.busRepository = busRepository;
        this.vehiculeRepository = vehiculeRepository;
    }

    @Override
    public BusVehiculeResponse create(BusVehiculeRequest request) {
        BusVehicule entity = buildEntity(request, new BusVehicule());
        return BusVehiculeMapper.toResponse(repository.save(entity));
    }

    @Override
    public BusVehiculeResponse update(Long id, BusVehiculeRequest request) {
        BusVehicule existing = repository.findById(id).orElseThrow();
        BusVehicule entity = buildEntity(request, existing);
        return BusVehiculeMapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public BusVehiculeResponse get(Long id) {
        return repository.findById(id).map(BusVehiculeMapper::toResponse).orElseThrow();
    }

    @Override
    @Transactional(readOnly = true)
    public List<BusVehiculeResponse> list() {
        return repository.findAll().stream().map(BusVehiculeMapper::toResponse).toList();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    private BusVehicule buildEntity(BusVehiculeRequest request, BusVehicule entity) {
        Bus bus = busRepository.findById(request.getBusId()).orElseThrow();
        Vehicule vehicule = vehiculeRepository.findById(request.getVehiculeId()).orElseThrow();
        entity.setBus(bus);
        entity.setVehicule(vehicule);
        entity.setStatut(request.getStatut());
        return entity;
    }
}
