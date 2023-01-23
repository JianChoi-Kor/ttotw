package com.project.ttotw.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class EnumResDto {

    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    @Getter
    public static class CommonEnumRes {
        private String code;
        private String description;
    }
}
