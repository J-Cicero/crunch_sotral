package com.smart.sotral.transport.domain.services.servicesImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smart.sotral.transport.application.dtos.PredictionRequest;
import com.smart.sotral.transport.application.dtos.PredictionResponse;
import com.smart.sotral.transport.application.mappers.PredictionMapper;
import com.smart.sotral.transport.domain.models.Arret;
import com.smart.sotral.transport.domain.models.Bus;
import com.smart.sotral.transport.domain.models.BusVehicule;
import com.smart.sotral.transport.domain.models.Capteur;
import com.smart.sotral.transport.domain.models.LigneArret;
import com.smart.sotral.transport.domain.models.Mission;
import com.smart.sotral.transport.domain.models.Prediction;
import com.smart.sotral.transport.domain.repositories.ArretRepository;
import com.smart.sotral.transport.domain.repositories.BusRepository;
import com.smart.sotral.transport.domain.repositories.BusVehiculeRepository;
import com.smart.sotral.transport.domain.repositories.LigneArretRepository;
import com.smart.sotral.transport.domain.repositories.LigneRepository;
import com.smart.sotral.transport.domain.repositories.MissionRepository;
import com.smart.sotral.transport.domain.repositories.PredictionRepository;
import com.smart.sotral.transport.domain.services.PredictionService;

@Service
@Transactional
public class PredictionServiceImpl implements PredictionService {

    private final PredictionRepository predictionRepository;
    private final BusVehiculeRepository busVehiculeRepository;
    private final MissionRepository missionRepository;
    private final BusRepository busRepository;
    private final LigneArretRepository ligneArretRepository;
    private final ArretRepository arretRepository;
    private final com.smart.sotral.transport.domain.repositories.CapteurRepository capteurRepository;

    public PredictionServiceImpl(PredictionRepository predictionRepository,
                                 BusVehiculeRepository busVehiculeRepository,
                                 MissionRepository missionRepository,
                                 BusRepository busRepository,
                                 LigneArretRepository ligneArretRepository,
                                 ArretRepository arretRepository,
                                 com.smart.sotral.transport.domain.repositories.CapteurRepository capteurRepository) {
        this.predictionRepository = predictionRepository;
        this.busVehiculeRepository = busVehiculeRepository;
        this.missionRepository = missionRepository;
        this.busRepository = busRepository;
        this.ligneArretRepository = ligneArretRepository;
        this.arretRepository = arretRepository;
        this.capteurRepository = capteurRepository;
    }

    @Override
    public PredictionResponse create(PredictionRequest request) {
        Prediction entity = buildEntity(request, new Prediction());
        return PredictionMapper.toResponse(predictionRepository.save(entity));
    }

    @Override
    public PredictionResponse update(UUID trackingId, PredictionRequest request) {
        Prediction existing = predictionRepository.findByTrackingId(trackingId).orElseThrow();
        Prediction entity = buildEntity(request, existing);
        return PredictionMapper.toResponse(predictionRepository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public PredictionResponse get(UUID trackingId) {
        return predictionRepository.findByTrackingId(trackingId).map(PredictionMapper::toResponse).orElseThrow();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PredictionResponse> list() {
        return predictionRepository.findAll().stream().map(PredictionMapper::toResponse).toList();
    }

    @Override
    public void delete(UUID trackingId) {
        Prediction existing = predictionRepository.findByTrackingId(trackingId).orElseThrow();
        predictionRepository.delete(existing);
    }

    @Override
    public List<PredictionResponse> findByArret(UUID arretTrackingId) {
        return predictionRepository.findByArret_TrackingIdOrderByTempsRestantMinutesAsc(arretTrackingId)
                .stream().map(PredictionMapper::toResponse).toList();
    }

    @Override
    public List<PredictionResponse> findByBus(UUID busTrackingId) {
        return predictionRepository.findByBus_TrackingIdOrderByTempsRestantMinutesAsc(busTrackingId)
                .stream().map(PredictionMapper::toResponse).toList();
    }

    @Override
    public void calculerPrediction(UUID vehiculeTrackingId, Capteur capteurActuel) {
        BusVehicule bv = busVehiculeRepository.findByVehicule_TrackingIdAndStatut(vehiculeTrackingId, "ACTIF").orElse(null);
        if (bv == null) {
            return;
        }
        Mission mission = missionRepository.findByBusVehicule_TrackingIdAndStatut(bv.getTrackingId(), com.smart.sotral.transport.domain.enums.StatutMission.ACTIVE).orElse(null);
        if (mission == null) {
            return;
        }

        Bus bus = busRepository.findByTrackingId(bv.getBus().getTrackingId()).orElseThrow();
        // Bus is no longer linked to a ligne; until a new route source is provided,
        // we skip prediction calculations to avoid inconsistent data.
        return;
    }

    private Prediction buildEntity(PredictionRequest request, Prediction entity) {
        Bus bus = busRepository.findByTrackingId(request.getBusTrackingId()).orElseThrow();
        Arret arret = arretRepository.findByTrackingId(request.getArretTrackingId()).orElseThrow();
        entity.setBus(bus);
        entity.setArret(arret);
        entity.setDistanceRestanteKm(request.getDistanceRestanteKm());
        entity.setTempsRestantMinutes(request.getTempsRestantMinutes());
        entity.setHeureEstimeeArrivee(request.getHeureEstimeeArrivee());
        entity.setHorodatage(request.getHorodatage());
        return entity;
    }

    private double calculerVitesseMoyenne(UUID vehiculeTrackingId) {
        List<Capteur> dernieres = capteurRepository.findTop5ByVehicule_TrackingIdOrderByHorodatageDesc(vehiculeTrackingId);
        if (dernieres.isEmpty()) {
            return 20.0;
        }
        double moyenne = dernieres.stream().mapToDouble(c -> c.getVitesse() != null ? c.getVitesse() : 0.0).average().orElse(20.0);
        return Math.max(moyenne, 5.0);
    }

    private int determinerOrdreActuel(Capteur capteur, UUID ligneTrackingId) {
        List<LigneArret> tous = ligneArretRepository.findByLigne_TrackingIdOrderByOrdreAsc(ligneTrackingId);
        int ordrePlusProche = 1;
        double distanceMin = Double.MAX_VALUE;
        for (LigneArret la : tous) {
            Arret arret = la.getArret();
            double d = haversine(capteur.getLatitude(), capteur.getLongitude(), arret.getLatitude(), arret.getLongitude());
            if (d < distanceMin) {
                distanceMin = d;
                ordrePlusProche = la.getOrdre();
            }
        }
        return ordrePlusProche;
    }

    private double haversine(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }
}
