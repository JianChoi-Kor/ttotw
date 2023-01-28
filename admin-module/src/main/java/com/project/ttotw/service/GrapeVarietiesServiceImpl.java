package com.project.ttotw.service;

import com.project.ttotw.entity.GrapeVarieties;
import com.project.ttotw.repository.GrapeVarietiesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GrapeVarietiesServiceImpl implements GrapeVarietiesService {

    private final GrapeVarietiesRepository grapeVarietiesRepository;

    public List<GrapeVarieties> findAll() {
        return grapeVarietiesRepository.findAll();
    }
}
