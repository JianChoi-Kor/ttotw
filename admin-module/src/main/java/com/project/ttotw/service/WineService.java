package com.project.ttotw.service;

import com.project.ttotw.dto.WineRequestDto;
import org.springframework.web.multipart.MultipartFile;

public interface WineService {

    void registerWine(WineRequestDto.RegisterWine registerWine, MultipartFile wineImage);
}
