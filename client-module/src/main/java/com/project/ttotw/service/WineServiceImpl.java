package com.project.ttotw.service;

import com.project.ttotw.dto.ApiResponse;
import com.project.ttotw.dto.WineRequestDto;
import com.project.ttotw.entity.QWine;
import com.project.ttotw.repository.WineRepository;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class WineServiceImpl implements WineService {

    private final WineRepository wineRepository;

    private final ApiResponse response;

    public ResponseEntity<?> recommend(WineRequestDto.Recommend recommend, Pageable pageable) {

        List<Predicate> where = new ArrayList<>();
        if (recommend.getType() != null) {
            where.add(QWine.wine.type.eq(recommend.getType()));
        }
        if (recommend.getPrice() != null) {
            Integer basePrice = recommend.getPrice() * 10000;
            where.add(QWine.wine.minPrice.goe(basePrice));
            where.add(QWine.wine.maxPrice.loe(basePrice + 9999));
        }


        wineRepository.findAll(where, pageable);

        return null;
    }
}
