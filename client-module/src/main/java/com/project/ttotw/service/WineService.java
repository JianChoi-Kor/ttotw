package com.project.ttotw.service;

import com.project.ttotw.dto.WineRequestDto;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface WineService {

    ResponseEntity<?> recommend(WineRequestDto.Recommend recommend, Pageable pageable);
}
