package com.project.ttotw.service;

import com.project.ttotw.dto.WineRequestDto;
import com.project.ttotw.dto.WineResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface WineService {

    void registerWine(WineRequestDto.RegisterWine registerWine, MultipartFile wineImage);

    Page<WineResponseDto.WindListView> getWineList(Pageable pageable);

    WineResponseDto.WineDetailsView getWineDetails(Long id);
}
