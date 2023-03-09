package com.project.ttotw.dto;

import com.project.ttotw.entity.File;
import com.project.ttotw.entity.Wine;
import lombok.*;

public class WineResponseDto {

    @ToString
    @Setter
    @Getter
    @Builder
    @AllArgsConstructor
    public static class RecommendListView {

        private Long id;
        private String grade;
        private String type;
        private String originName;
        private String koreanName;
        private String fileFullPath;
        private Integer minPrice;
        private Integer maxPrice;
        private String country;

        public static RecommendListView from(Wine wine) {

            File file = wine.getFile();
            final String SEPARATOR = "/";
            final String PERIOD = ".";
            final String HTTP = "http://";

            String fullFilePath = HTTP + file.getSavedHost() + file.getSavedPath2() + SEPARATOR + file.getSavedName() + PERIOD + file.getFileExt();

            return RecommendListView.builder()
                    .id(wine.getId())
                    .grade(wine.getGrade().name())
                    .type(wine.getType().name())
                    .originName(wine.getOriginName())
                    .koreanName(wine.getKoreanName())
                    .fileFullPath(fullFilePath)
                    .minPrice(wine.getMinPrice())
                    .maxPrice(wine.getMaxPrice())
                    .country(wine.getCountry().getEnglishName())
                    .build();
        }
    }
}
