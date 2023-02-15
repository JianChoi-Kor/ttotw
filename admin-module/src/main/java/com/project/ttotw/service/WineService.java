package com.project.ttotw.service;

import com.project.ttotw.dto.WineRequestDto;
import com.project.ttotw.dto.WineResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface WineService {

    void registerWine(WineRequestDto.RegisterWine registerWine, MultipartFile wineImage);
    Page<WineResponseDto.WineListView> getWineList(WineRequestDto.SearchWineList searchWineList);
    WineResponseDto.WineDetailsView getWineDetails(Long id);
    void getWineImage(Long fileId, HttpServletResponse servletResponse);
}
