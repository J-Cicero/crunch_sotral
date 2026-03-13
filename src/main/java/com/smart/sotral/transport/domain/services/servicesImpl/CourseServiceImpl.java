package com.smart.sotral.transport.domain.services.servicesImpl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smart.sotral.transport.application.dtos.CourseRequest;
import com.smart.sotral.transport.application.dtos.CourseResponse;
import com.smart.sotral.transport.application.mappers.CourseMapper;
import com.smart.sotral.transport.domain.models.Course;
import com.smart.sotral.transport.domain.models.Mission;
import com.smart.sotral.transport.domain.repositories.CourseRepository;
import com.smart.sotral.transport.domain.repositories.MissionRepository;
import com.smart.sotral.transport.domain.services.CourseService;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    private final CourseRepository repository;
    private final MissionRepository missionRepository;

    public CourseServiceImpl(CourseRepository repository, MissionRepository missionRepository) {
        this.repository = repository;
        this.missionRepository = missionRepository;
    }

    @Override
    public CourseResponse create(CourseRequest request) {
        Course entity = buildEntity(request, new Course());
        return CourseMapper.toResponse(repository.save(entity));
    }

    @Override
    public CourseResponse update(Long id, CourseRequest request) {
        Course existing = repository.findById(id).orElseThrow();
        Course entity = buildEntity(request, existing);
        return CourseMapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public CourseResponse get(Long id) {
        return repository.findById(id).map(CourseMapper::toResponse).orElseThrow();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseResponse> list() {
        return repository.findAll().stream().map(CourseMapper::toResponse).toList();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    private Course buildEntity(CourseRequest request, Course entity) {
        Mission mission = missionRepository.findById(request.getMissionId()).orElseThrow();
        entity.setMission(mission);
        entity.setDateDebut(request.getDateDebut());
        entity.setDateFin(request.getDateFin());
        entity.setLieuDebut(request.getLieuDebut());
        entity.setLieuFin(request.getLieuFin());
        entity.setStatut(request.getStatut());
        return entity;
    }
}
