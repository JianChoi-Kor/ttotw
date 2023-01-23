package com.project.ttotw.enums;

import com.project.ttotw.dto.EnumResDto;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public enum WineGrade {
    NORMAL("일반"),
    PREMIUM("고급");

    private String description;

    WineGrade(String description) {
        this.description = description;
    }

    public static List<EnumResDto.CommonEnumRes> getEnumList() {
        return Arrays.stream(WineGrade.class.getEnumConstants())
                .map(targetEnum -> new EnumResDto.CommonEnumRes(targetEnum.name(), targetEnum.getDescription()))
                .collect(Collectors.toList());
    }
}
