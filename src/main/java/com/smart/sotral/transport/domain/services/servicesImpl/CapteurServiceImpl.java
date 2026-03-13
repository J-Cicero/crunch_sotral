package com.smart.sotral.transport.domain.services.servicesImpl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smart.sotral.transport.application.dtos.CapteurRequest;
import com.smart.sotral.transport.application.dtos.CapteurResponse;
import com.smart.sotral.transport.application.mappers.CapteurMapper;
import com.smart.sotral.transport.domain.models.Capteur;
import com.smart.sotral.transport.domain.models.Vehicule;
import com.smart.sotral.transport.domain.repositories.CapteurRepository;
import com.smart.sotral.transport.domain.repositories.VehiculeRepository;
import com.smart.sotral.transport.domain.services.CapteurService;
import java.time.LocalDateTime;

@Service
@Transactional
public class CapteurServiceImpl implements CapteurService {

    private final CapteurRepository repository;
    private final VehiculeRepository vehiculeRepository;

    public CapteurServiceImpl(CapteurRepository repository, VehiculeRepository vehiculeRepository) {
        this.repository = repository;
        this.vehiculeRepository = vehiculeRepository;
    }

    @Override
    public CapteurResponse create(CapteurRequest request) {
        Capteur entity = buildEntity(request, new Capteur());
        return CapteurMapper.toResponse(repository.save(entity));
    }

    @Override
    public CapteurResponse update(Long id, CapteurRequest request) {
        Capteur existing = repository.findById(id).orElseThrow();
        Capteur entity = buildEntity(request, existing);
        return CapteurMapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public CapteurResponse get(Long id) {
        return repository.findById(id).map(CapteurMapper::toResponse).orElseThrow();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CapteurResponse> list() {
        return repository.findAll().stream().map(CapteurMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<CapteurResponse> getDernierePositionParVehicule() {
        return repository.findDernierePositionParVehicule().stream().map(CapteurMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<CapteurResponse> getDernieres5(Long vehiculeId) {
        return repository.findTop5ByVehiculeIdOrderByHorodatageDesc(vehiculeId).stream().map(CapteurMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<CapteurResponse> getHistorique(Long vehiculeId) {
        return repository.findByVehiculeIdOrderByHorodatageDesc(vehiculeId).stream().map(CapteurMapper::toResponse).toList();
    }

    public Capteur enregistrerPosition(CapteurRequest request) {
        Capteur entity = buildEntity(request, new Capteur());
        return repository.save(entity);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    private Capteur buildEntity(CapteurRequest request, Capteur entity) {
        Vehicule vehicule = vehiculeRepository.findByTrackingId(java.util.UUID.fromString(request.getVehiculeTrackingId())).orElseThrow();
        entity.setVehicule(vehicule);
        entity.setLatitude(request.getLatitude());
        entity.setLongitude(request.getLongitude());
        Double vitesse = request.getVitesse();
        entity.setVitesse(vitesse != null && vitesse >= 0 ? vitesse : 0.0);
        entity.setCap(request.getCap());
        entity.setHorodatage(request.getHorodatage() != null ? request.getHorodatage() : LocalDateTime.now());
        entity.setSourceSignal(request.getSourceSignal());
        return entity;
    }
}
