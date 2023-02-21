package com.project.ttotw.dto;

import com.project.ttotw.entity.File;
import com.project.ttotw.entity.Wine;
import lombok.*;

import java.util.stream.Collectors;

public class WineResponseDto {

    private final String SEPARATOR = "/";
    private final String PERIOD = ".";

    @ToString
    @Setter
    @Getter
    @Builder
    @AllArgsConstructor
    public static class WineListView {

        private Long id;
        private String grade;
        private String type;
        private String originName;
        private String koreanName;
        private String fileFullPath;
        private Long minPrice;
        private Long maxPrice;
        private String country;

        @SuppressWarnings("unused")
        private WineListView() {
        }

        public static WineListView from(Wine wine) {

            File file = wine.getFile();
            final String SEPARATOR = "/";
            final String PERIOD = ".";

            String fullFilePath = file.getSavedHost() + SEPARATOR + file.getSavedPath() + SEPARATOR + file.getSavedName() + PERIOD + file.getFileExt();

            return WineListView.builder()
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

    @ToString
    @Setter
    @Getter
    @Builder
    @AllArgsConstructor
    public static class WineDetailsView {

        private Long id;
        private String grade;
        private String type;
        private String originName;
        private String koreanName;
        private Long fileId;
        private Long minPrice;
        private Long maxPrice;
        private Integer tannin;
        private Integer body;
        private Integer acidity;
        private Integer dry;
        private String country;
        private String countryDetails;
        private String varieties;
        private String varietiesDetails;

        @SuppressWarnings("unused")
        private WineDetailsView() {
        }

        public static WineDetailsView from(Wine wine) {
            if (wine == null) {
                return null;
            }

            File file = wine.getFile();
            final String SEPARATOR = "/";
            final String PERIOD = ".";

            return WineDetailsView.builder()
                    .id(wine.getId())
                    .grade(wine.getGrade().getDescription())
                    .type(wine.getType().getDescription())
                    .originName(wine.getOriginName())
                    .koreanName(wine.getKoreanName())
                    .fileId(wine.getFile().getId())
                    .minPrice(wine.getMinPrice())
                    .maxPrice(wine.getMaxPrice())
                    .tannin(wine.getTannin())
                    .body(wine.getBody())
                    .acidity(wine.getAcidity())
                    .dry(wine.getDry())
                    .country(wine.getCountry().getEnglishName())
                    .countryDetails(wine.getCountryDetails())
                    .varieties(wine.getVarieties().stream().map(v -> v.getOriginName()).collect(Collectors.joining(", ", "", "")))
                    .varietiesDetails(wine.getVarietiesDetails())
                    .build();
        }
    }
}
