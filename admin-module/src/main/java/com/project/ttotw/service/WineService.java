package com.project.ttotw.service;

import com.project.ttotw.dto.WineRequestDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface WineService {

    void registerWine(WineRequestDto.RegisterWine registerWine, MultipartFile wineImage);
}
