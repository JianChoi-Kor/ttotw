package com.project.ttotw.service;

import com.project.ttotw.dto.WineRequestDto;
import com.project.ttotw.entity.Wine;
import com.project.ttotw.lib.FtpUtils;
import com.project.ttotw.repository.WineRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class WineServiceImpl implements WineService {

    private final WineRepository wineRepository;

    private final FtpUtils ftpUtils;

    @Override
    public void registerWine(WineRequestDto.RegisterWine registerWine, MultipartFile wineImage) throws IOException {
        //파일 등록
        ftpUtils.upload(wineImage);

//        //와인 등록
//        Wine wine = Wine.builder()
//                .grade(registerWine.getGrade())
//                .type(registerWine.getType())
//                .originName(registerWine.getOriginName())
//                .koreanName(registerWine.getKoreanName())
//                .fileId()
//                .minPrice(registerWine.getPrice())
//                .maxPrice(registerWine.getPrice())
//                .tannin(registerWine.getTannin())
//                .body(registerWine.getBody())
//                .acidity(registerWine.getAcidity())
//                .dry(registerWine.getDry())
//                .country(registerWine.getCountry())
//                .countryDetails(registerWine.getCountryDetails())
//                .varieties(registerWine.getVarieties())
//                .varietiesDetails(registerWine.getVarietiesDetails())
//                .useAt(true)
//                .build();
//
//        //save
//        wineRepository.save(wine);
    }
}
