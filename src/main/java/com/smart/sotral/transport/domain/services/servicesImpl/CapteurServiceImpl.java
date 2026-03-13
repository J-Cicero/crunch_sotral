package com.smart.sotral.transport.domain.services.servicesImpl;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smart.sotral.transport.application.dtos.CapteurRequest;
import com.smart.sotral.transport.application.dtos.CapteurResponse;
import com.smart.sotral.transport.application.dtos.PositionBusDTO;
import com.smart.sotral.transport.application.mappers.CapteurMapper;
import com.smart.sotral.transport.domain.enums.StatutMission;
import com.smart.sotral.transport.domain.models.Bus;
import com.smart.sotral.transport.domain.models.BusVehicule;
import com.smart.sotral.transport.domain.models.Capteur;
import com.smart.sotral.transport.domain.models.Vehicule;
import com.smart.sotral.transport.domain.repositories.BusRepository;
import com.smart.sotral.transport.domain.repositories.BusVehiculeRepository;
import com.smart.sotral.transport.domain.repositories.CapteurRepository;
import com.smart.sotral.transport.domain.repositories.MissionRepository;
import com.smart.sotral.transport.domain.repositories.PredictionRepository;
import com.smart.sotral.transport.domain.repositories.VehiculeRepository;
import com.smart.sotral.transport.domain.services.CapteurService;

@Service
@Transactional
public class CapteurServiceImpl implements CapteurService {

    private final CapteurRepository repository;
    private final VehiculeRepository vehiculeRepository;
    private final BusRepository busRepository;
    private final BusVehiculeRepository busVehiculeRepository;
    private final MissionRepository missionRepository;
    private final PredictionRepository predictionRepository;

    public CapteurServiceImpl(
            CapteurRepository repository,
            VehiculeRepository vehiculeRepository,
            BusRepository busRepository,
            BusVehiculeRepository busVehiculeRepository,
            MissionRepository missionRepository,
            PredictionRepository predictionRepository
    ) {
        this.repository = repository;
        this.vehiculeRepository = vehiculeRepository;
        this.busRepository = busRepository;
        this.busVehiculeRepository = busVehiculeRepository;
        this.missionRepository = missionRepository;
        this.predictionRepository = predictionRepository;
    }

    @Override
    public CapteurResponse create(CapteurRequest request) {
        Capteur entity = buildEntity(request, new Capteur());
        return CapteurMapper.toResponse(repository.save(entity));
    }

    @Override
    public CapteurResponse update(UUID trackingId, CapteurRequest request) {
        Capteur existing = repository.findByTrackingId(trackingId).orElseThrow();
        Capteur entity = buildEntity(request, existing);
        return CapteurMapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public CapteurResponse get(UUID trackingId) {
        return repository.findByTrackingId(trackingId).map(CapteurMapper::toResponse).orElseThrow();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CapteurResponse> list() {
        return repository.findAll().stream().map(CapteurMapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PositionBusDTO> getDernieresPositionsActives() {
        // Pas de requête globale en arrière-plan : retour vide par défaut
        return List.of();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PositionBusDTO> getDernieresPositionsParLigne(UUID ligneTrackingId) {
        // Ligne information is no longer stored on Bus; without a replacement mapping
        // this endpoint cannot resolve buses for a ligne, so return empty for now.
        return List.of();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PositionBusDTO> getDernieresPositionsParArret(UUID arretTrackingId) {
        List<UUID> busTrackingIds = predictionRepository.findDistinctBusTrackingIdByArret_TrackingId(arretTrackingId);
        if (busTrackingIds.isEmpty()) {
            return List.of();
        }
        List<BusVehicule> vehicules = busVehiculeRepository.findByBus_TrackingIdInAndStatut(busTrackingIds, "ACTIF");
        return buildPositions(vehicules);
    }

    @Transactional(readOnly = true)
    public List<CapteurResponse> getDernieres5(UUID vehiculeTrackingId) {
        return repository.findTop5ByVehicule_TrackingIdOrderByHorodatageDesc(vehiculeTrackingId).stream().map(CapteurMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<CapteurResponse> getHistorique(UUID vehiculeTrackingId) {
        return repository.findByVehicule_TrackingIdOrderByHorodatageDesc(vehiculeTrackingId).stream().map(CapteurMapper::toResponse).toList();
    }

    public Capteur enregistrerPosition(CapteurRequest request) {
        Capteur entity = buildEntity(request, new Capteur());
        return repository.save(entity);
    }

    @Override
    public void delete(UUID trackingId) {
        Capteur existing = repository.findByTrackingId(trackingId).orElseThrow();
        repository.delete(existing);
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

    private List<PositionBusDTO> buildPositions(Collection<BusVehicule> busVehicules) {
        return busVehicules.stream()
                .filter(this::hasActiveMission)
                .map(this::buildPosition)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }

    private boolean hasActiveMission(BusVehicule busVehicule) {
        return missionRepository
                .findByBusVehicule_TrackingIdAndStatut(busVehicule.getTrackingId(), StatutMission.ACTIVE)
                .isPresent();
    }

    private Optional<PositionBusDTO> buildPosition(BusVehicule busVehicule) {
        if (busVehicule.getVehicule() == null || busVehicule.getBus() == null) {
            return Optional.empty();
        }
        Optional<Capteur> latest = repository.findTopByVehicule_TrackingIdOrderByHorodatageDesc(busVehicule.getVehicule().getTrackingId());
        if (latest.isEmpty()) {
            return Optional.empty();
        }
        Capteur capteur = latest.get();
        Bus bus = busVehicule.getBus();
        return Optional.of(PositionBusDTO.builder()
                .vehiculeTrackingId(busVehicule.getVehicule().getTrackingId())
                .busTrackingId(bus.getTrackingId())
                .busCode(bus.getCode())
                .latitude(capteur.getLatitude())
                .longitude(capteur.getLongitude())
                .vitesse(capteur.getVitesse())
                .horodatage(capteur.getHorodatage())
                .missionActive(true)
                .build());
    }
}
