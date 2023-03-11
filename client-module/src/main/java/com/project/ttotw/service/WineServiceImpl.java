package com.project.ttotw.service;

import com.project.ttotw.dto.ApiResponse;
import com.project.ttotw.dto.WineRequestDto;
import com.project.ttotw.dto.WineResponseDto;
import com.project.ttotw.entity.QWine;
import com.project.ttotw.entity.Wine;
import com.project.ttotw.repository.WineRepository;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class WineServiceImpl implements WineService {

    private final WineRepository wineRepository;

    private final ApiResponse response;

    public ResponseEntity<?> recommend(WineRequestDto.Recommend recommend, Pageable pageable) {

        //TODO:: 더 좋은 방법?
        List<Predicate> where = new ArrayList<>();
        if (recommend.getType() != null) {
            where.add(QWine.wine.type.eq(recommend.getType()));
        }
        if (recommend.getPrice() != null) {
            Integer basePrice = recommend.getPrice() * 10000;
            where.add(QWine.wine.minPrice.goe(basePrice));
            where.add(QWine.wine.maxPrice.loe(basePrice + 9999));
        }
        if (recommend.getDry() != null) {
            where.add(QWine.wine.dry.eq(recommend.getDry()));
        }
        if (recommend.getBody() != null) {
            where.add(QWine.wine.body.eq(recommend.getBody()));
        }
        if (recommend.getAcidity() != null) {
            where.add(QWine.wine.acidity.eq(recommend.getAcidity()));
        }
        if (recommend.getTannin() != null) {
            where.add(QWine.wine.tannin.eq(recommend.getTannin()));
        }
        if (recommend.getContinent() != null) {
            where.add(QWine.wine.continent.eq(recommend.getContinent()));
        }

        List<Wine> wineList = wineRepository.findAll(where, pageable);

        List<WineResponseDto.RecommendListView> content = new ArrayList<>();
        if (!wineList.isEmpty()) {
            content = wineList
                    .stream()
                    .map(WineResponseDto.RecommendListView::from)
                    .collect(Collectors.toList());
        }

        return response.success(content);
    }
}
