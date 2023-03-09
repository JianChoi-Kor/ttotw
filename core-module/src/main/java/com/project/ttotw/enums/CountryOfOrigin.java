package com.project.ttotw.enums;

import com.project.ttotw.dto.EnumResDto;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public enum CountryOfOrigin {
    FRA("France", "프랑스", Continent.OLD),
    NZL("New Zealand", "뉴질랜드", Continent.NEW),
    USA("United States of America", "미국", Continent.NEW),
    DEU("Germany", "독일", Continent.OLD),
    ESP("Spain", "스페인", Continent.OLD),
    ITA("Italy", "이탈리아", Continent.OLD),
    CHL("Chile", "칠레", Continent.NEW),
    AUS("Australia", "호주", Continent.NEW),
    PRT("Portugal", "포르투갈", Continent.OLD),
    ARG("Argentina", "아르헨티나", Continent.NEW),
    ETC("etc", "기타", Continent.NEW);

    private String englishName;
    private String koreanName;
    private Continent continent;

    CountryOfOrigin(String englishName, String koreanName, Continent continent) {
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
