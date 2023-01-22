package com.project.ttotw.dto;

import com.project.ttotw.entity.GrapeVarieties;
import com.project.ttotw.enums.CountryOfOrigin;
import com.project.ttotw.enums.WineGrade;
import com.project.ttotw.enums.WineType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class WineRequestDto {

    @Getter
    @Setter
    public static class RegisterWine {
        //등급
        private WineGrade grade;
        //타입
        private WineType type;
        //제품명
        private String originName;
        //제품명(한글)
        private String koreanName;
        //와인 이미지
        private MultipartFile multipartFile;
        //가격
        private Long price;
        //타닌
        private Integer tannin;
        //바디
        private Integer body;
        //산미
        private Integer acidity;
        //당도
        private Integer dry;
        //생산지 분류
        private CountryOfOrigin country;
        //생산지 상세(영문)
        private String countryDetails;
        //품종
        private List<GrapeVarieties> grapeVarieties;
    }

    @Getter
    @Setter
    public static class ModifyWine {
        //등급
        private WineGrade grade;
        //타입
        private WineType wineType;
        //제품명
        private String originName;
        //제품명(한글)
        private String koreanName;
        //와인 이미지
        private MultipartFile multipartFile;
        //가격
        private Long price;
        //타닌
        private Integer tannin;
        //바디
        private Integer body;
        //산미
        private Integer acidity;
        //당도
        private Integer dry;
        //생산지 분류
        private CountryOfOrigin country;
        //생산지 상세(영문)
        private String countryDetails;
        //품종
        private List<GrapeVarieties> grapeVarieties;
    }
}
