package com.smart.sotral.transport.application.schedulers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.smart.sotral.transport.domain.models.BusVehicule;
import com.smart.sotral.transport.domain.models.Capteur;
import com.smart.sotral.transport.domain.repositories.BusVehiculeRepository;
import com.smart.sotral.transport.domain.repositories.CapteurRepository;
import com.smart.sotral.transport.domain.repositories.MissionRepository;
import com.smart.sotral.transport.domain.services.PredictionService;
import com.smart.sotral.transport.domain.enums.StatutMission;
import java.util.UUID;

@Component
public class PredictionScheduler {

    private final CapteurRepository capteurRepository;
    private final BusVehiculeRepository busVehiculeRepository;
    private final MissionRepository missionRepository;
    private final PredictionService predictionService;

    public PredictionScheduler(CapteurRepository capteurRepository,
                               BusVehiculeRepository busVehiculeRepository,
                               MissionRepository missionRepository,
                               PredictionService predictionService) {
        this.capteurRepository = capteurRepository;
        this.busVehiculeRepository = busVehiculeRepository;
        this.missionRepository = missionRepository;
        this.predictionService = predictionService;
    }

    @Scheduled(fixedDelay = 30000)
    public void recalculerToutesPredictions() {
        List<BusVehicule> actifs = busVehiculeRepository.findByStatut("ACTIF");
        for (BusVehicule bv : actifs) {
            var capteurOpt = capteurRepository.findTopByVehicule_TrackingIdOrderByHorodatageDesc(bv.getVehicule().getTrackingId());
            if (capteurOpt.isEmpty()) {
                continue;
            }
            Capteur capteur = capteurOpt.get();
            if (capteur.getHorodatage() != null && capteur.getHorodatage().isBefore(LocalDateTime.now().minusMinutes(5))) {
                continue;
            }
            predictionService.calculerPrediction(bv.getVehicule().getTrackingId(), capteur);
        }
    }

    @Scheduled(fixedDelay = 600000)
    public void nettoyerPredictionsObsoletes() {
        // simple: delete predictions if no active mission found
        var actifs = missionRepository.findByStatut(StatutMission.ACTIVE);
        var busTrackingActifs = actifs.stream()
                .map(m -> m.getBusVehicule().getBus().getTrackingId())
                .filter(id -> id != null)
                .toList();
        predictionService.list().stream()
                .filter(p -> !busTrackingActifs.contains(p.getBusTrackingId()))
                .forEach(p -> predictionService.delete(p.getTrackingId()));
    }
}
