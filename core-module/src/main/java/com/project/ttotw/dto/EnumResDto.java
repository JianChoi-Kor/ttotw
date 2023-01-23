package com.project.ttotw.dto;

import com.project.ttotw.entity.GrapeVarieties;
import lombok.*;

public class EnumResDto {

    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    @Getter
    public static class CommonEnumRes {
        private String code;
        private String description;
    }

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    @Getter
    public static class CommonGrapeVarietiesRes {
        private Long id;
        private String originName;

        public static EnumResDto.CommonGrapeVarietiesRes from(GrapeVarieties grapeVarieties) {
            return EnumResDto.CommonGrapeVarietiesRes.builder()
                    .id(grapeVarieties.getId())
                    .originName(grapeVarieties.getOriginName())
                    .build();
        }
    }
}
