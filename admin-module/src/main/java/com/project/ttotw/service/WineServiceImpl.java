package com.project.ttotw.service;

import com.project.ttotw.dto.WineRequestDto;
import com.project.ttotw.dto.WineResponseDto;
import com.project.ttotw.entity.File;
import com.project.ttotw.entity.Wine;
import com.project.ttotw.enums.FileDirectory;
import com.project.ttotw.lib.FtpImpl;
import com.project.ttotw.repository.FileRepository;
import com.project.ttotw.repository.WineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class WineServiceImpl implements WineService {

    private final WineRepository wineRepository;
    private final FileRepository fileRepository;

    private final FtpImpl ftpImplUtils;

    @Transactional
    @Override
    public void registerWine(WineRequestDto.RegisterWine registerWine, MultipartFile wineImage) {
        //파일 등록
        File file = ftpImplUtils.upload(FileDirectory.WINE.getDirectoryName(), wineImage);
        //파일 저장
        fileRepository.save(file);

        try {
            //와인 등록
            Wine wine = Wine.builder()
                    .grade(registerWine.getGrade())
                    .type(registerWine.getType())
                    .originName(registerWine.getOriginName())
                    .koreanName(registerWine.getKoreanName())
                    .file(file)
                    .minPrice(registerWine.getPrice())
                    .maxPrice(registerWine.getPrice())
                    .tannin(registerWine.getTannin())
                    .body(registerWine.getBody())
                    .acidity(registerWine.getAcidity())
                    .dry(registerWine.getDry())
                    .country(registerWine.getCountry())
                    .countryDetails(registerWine.getCountryDetails())
                    .varieties(registerWine.getVarieties())
                    .varietiesDetails(registerWine.getVarietiesDetails())
                    .useAt(true)
                    .build();
            //save
            wineRepository.save(wine);
        } catch (Exception e) {
            String fullFilePath = ftpImplUtils.fullFilePath(file);
            ftpImplUtils.delete(fullFilePath);
            //TODO:: customException 변경
            throw new RuntimeException("register wine failed.");
        }
    }

    @Override
    public Page<WineResponseDto.WineListView> getWineList(WineRequestDto.SearchWineList searchWineList) {
        Pageable pageable = searchWineList.of();

        Page<Wine> wineList;
        if (StringUtils.hasText(searchWineList.getKeyword())) {
            wineList = wineRepository.findByOriginNameContainingOrKoreanNameContaining(searchWineList.getKeyword(), searchWineList.getKeyword(), pageable);
        } else {
            wineList = wineRepository.findAll(pageable);
        }

        List<WineResponseDto.WineListView> content = new ArrayList<>();
        if (wineList.getContent().isEmpty()) {
            return new PageImpl<>(content, pageable, wineList.getTotalElements());
        }

        content = wineList.getContent()
                .stream()
                .map(WineResponseDto.WineListView::from)
                .collect(Collectors.toList());

        return new PageImpl<>(content, pageable, wineList.getTotalElements());
    }

    @Override
    public WineResponseDto.WineDetailsView getWineDetails(Long id) {
        Wine wine = wineRepository.findById(id).orElse(null);
        return WineResponseDto.WineDetailsView.from(wine);
    }

    @Override
    public void getWineImage(Long fileId, HttpServletResponse servletResponse) {
        File file = fileRepository.findById(fileId).orElse(null);
        if (file == null) {
            return;
        }

        String fullFilePath = ftpImplUtils.fullFilePath(file);
//        ftpUtils.getFile(fullFilePath);
    }
}
