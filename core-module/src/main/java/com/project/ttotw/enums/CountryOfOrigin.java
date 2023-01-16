package com.project.ttotw.enums;

import lombok.Getter;

@Getter
public enum CountryOfOrigin {
    FRA("France", "프랑스"),
    NZL("New Zealand", "뉴질랜드"),
    USA("United States of America", "미국"),
    DEU("Germany", "독일"),
    ESP("Spain", "스페인"),
    ITA("Italy", "이탈리아"),
    CHL("Chile", "칠레"),
    AUS("Australia", "호주"),
    PRT("Portugal", "포르투갈"),
    ARG("Argentina", "아르헨티나"),
    ETC("etc", "기타");

    private String englishName;
    private String koreanName;

    CountryOfOrigin(String englishName, String koreanName) {
        this.englishName = englishName;
        this.koreanName = koreanName;
    }
}
