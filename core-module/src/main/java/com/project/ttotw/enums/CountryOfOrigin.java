package com.project.ttotw.enums;

import com.project.ttotw.dto.EnumResDto;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public enum CountryOfOrigin {
    FRA("France", "프랑스", "old"),
    NZL("New Zealand", "뉴질랜드", "new"),
    USA("United States of America", "미국", "new"),
    DEU("Germany", "독일", "old"),
    ESP("Spain", "스페인", "old"),
    ITA("Italy", "이탈리아", "old"),
    CHL("Chile", "칠레", "new"),
    AUS("Australia", "호주", "new"),
    PRT("Portugal", "포르투갈", "old"),
    ARG("Argentina", "아르헨티나", "new"),
    ETC("etc", "기타", "new");

    private String englishName;
    private String koreanName;
    private String continent;

    CountryOfOrigin(String englishName, String koreanName, String continent) {
        this.englishName = englishName;
        this.koreanName = koreanName;
        this.continent = continent;
    }

    public static List<EnumResDto.CommonEnumRes> getEnumList() {
        return Arrays.stream(CountryOfOrigin.class.getEnumConstants())
                .map(targetEnum -> new EnumResDto.CommonEnumRes(targetEnum.name(), targetEnum.getEnglishName()))
                .collect(Collectors.toList());
    }
}
