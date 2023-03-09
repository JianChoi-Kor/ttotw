package com.project.ttotw.controller;

import com.project.ttotw.dto.PageRequest;
import com.project.ttotw.dto.WineRequestDto;
import com.project.ttotw.service.WineServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/v1/wine")
@RestController
public class WineController {

    private final WineServiceImpl wineServiceImpl;

    //TODO:: 해당 기능에 검색엔진 적용
    @GetMapping(value = "/recommend")
    public ResponseEntity<?> recommend(WineRequestDto.Recommend recommend) {
        PageRequest pageRequest = new PageRequest();
        pageRequest.setSize(3);

        if (recommend.isAdd()) {
            pageRequest.setPage(2);
        }

        return wineServiceImpl.recommend(recommend, pageRequest.of());
    }
}
