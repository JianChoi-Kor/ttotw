package com.project.ttotw.enums;

import com.project.ttotw.dto.EnumResDto;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public enum WineType {
    RED("레드"),
    WHITE("화이트"),
    SPARKLING("스파클링");

    private String description;

    WineType(String description) {
        this.description = description;
    }

    public static List<EnumResDto.CommonEnumRes> getEnumList() {
        return Arrays.stream(WineType.class.getEnumConstants())
                .map(targetEnum -> new EnumResDto.CommonEnumRes(targetEnum.name(), targetEnum.getDescription()))
                .collect(Collectors.toList());
    }
}
