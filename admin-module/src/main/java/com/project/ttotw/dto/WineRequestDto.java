package com.project.ttotw.dto;

import com.project.ttotw.entity.GrapeVarieties;
import com.project.ttotw.enums.CountryOfOrigin;
import com.project.ttotw.enums.WineGrade;
import com.project.ttotw.enums.WineType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

public class WineRequestDto {

    @Getter
    @Setter
    public static class RegisterWine {
        //등급
        @NotNull(message = "등급은 필수 입력값입니다.")
        private WineGrade grade;
        //타입
        @NotNull(message = "타입은 필수 입력값입니다.")
        private WineType type;
        //제품명
        @NotBlank(message = "제품명(영문)은 필수 입력값입니다.")
        private String originName;
        //제품명(한글)
        @NotBlank(message = "제품명(한글)은 필수 입력값입니다.")
        private String koreanName;
        //가격
        @NotNull(message = "가격은 필수 입력값입니다.")
        @Positive(message = "잘못된 가격 입력값입니다.")
        private Integer price;
        //타닌
        @NotNull(message = "타닌은 필수 입력값입니다.")
        @Positive(message = "잘못된 타닌(tannin) 입력값입니다.")
        @Max(value = 5L, message = "잘못된 타닌(tannin) 입력값입니다.")
        private Integer tannin;
        //바디
        @NotNull(message = "바디는 필수 입력값입니다.")
        @Positive(message = "잘못된 바디(body) 입력값입니다.")
        @Max(value = 5L, message = "잘못된 바디(body) 입력값입니다.")
        private Integer body;
        //산미
        @NotNull(message = "산미는 필수 입력값입니다.")
        @Positive(message = "잘못된 산미(acidity) 입력값입니다.")
        @Max(value = 5L, message = "잘못된 산미(acidity) 입력값입니다.")
        private Integer acidity;
        //당도
        @NotNull(message = "당도는 필수 입력값입니다.")
        @Positive(message = "잘못된 당도(dry) 입력값입니다.")
        @Max(value = 5L, message = "잘못된 당도(dry) 입력값입니다.")
        private Integer dry;
        //생산지 분류
        @NotNull(message = "생산지는 필수 입력값입니다.")
        private CountryOfOrigin country;
        //생산지 상세(영문)
        @NotBlank(message = "생산지 상세(영문)는 필수 입력값입니다.")
        private String countryDetails;
        //품종
        @NotNull(message = "품종은 필수 입력값입니다.")
        private List<GrapeVarieties> varieties;
        //품종 상세(영문)
        @NotBlank(message = "품종 상세(영문)는 필수 입력값입니다.")
        private String varietiesDetails;
    }

    @Getter
    @Setter
    public static class ModifyWine {
        //등급
        @NotNull(message = "등급은 필수 입력값입니다.")
        private WineGrade grade;
        //타입
        @NotNull(message = "타입은 필수 입력값입니다.")
        private WineType type;
        //제품명
        @NotBlank(message = "제품명(영문)은 필수 입력값입니다.")
        private String originName;
        //제품명(한글)
        @NotBlank(message = "제품명(한글)은 필수 입력값입니다.")
        private String koreanName;
        //가격
        @NotNull(message = "가격은 필수 입력값입니다.")
        @Positive(message = "잘못된 가격 입력값입니다.")
        private Long price;
        //타닌
        @NotNull(message = "타닌은 필수 입력값입니다.")
        @Positive(message = "잘못된 타닌(tannin) 입력값입니다.")
        @Max(value = 5L, message = "잘못된 타닌(tannin) 입력값입니다.")
        private Integer tannin;
        //바디
        @NotNull(message = "바디는 필수 입력값입니다.")
        @Positive(message = "잘못된 바디(body) 입력값입니다.")
        @Max(value = 5L, message = "잘못된 바디(body) 입력값입니다.")
        private Integer body;
        //산미
        @NotNull(message = "산미는 필수 입력값입니다.")
        @Positive(message = "잘못된 산미(acidity) 입력값입니다.")
        @Max(value = 5L, message = "잘못된 산미(acidity) 입력값입니다.")
        private Integer acidity;
        //당도
        @NotNull(message = "당도는 필수 입력값입니다.")
        @Positive(message = "잘못된 당도(dry) 입력값입니다.")
        @Max(value = 5L, message = "잘못된 당도(dry) 입력값입니다.")
        private Integer dry;
        //생산지 분류
        @NotNull(message = "생산지는 필수 입력값입니다.")
        private CountryOfOrigin country;
        //생산지 상세(영문)
        @NotBlank(message = "생산지 상세(영문)는 필수 입력값입니다.")
        private String countryDetails;
        //품종
        @NotNull(message = "품종은 필수 입력값입니다.")
        private List<GrapeVarieties> varieties;
        //품종 상세(영문)
        @NotBlank(message = "품종 상세(영문)는 필수 입력값입니다.")
        private String varietiesDetails;
    }

    @Getter
    @Setter
    public static class SearchWineList extends PageRequest {
        //검색어
        private String keyword;
    }
}
