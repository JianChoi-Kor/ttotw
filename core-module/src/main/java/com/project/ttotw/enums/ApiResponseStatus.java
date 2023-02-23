package com.project.ttotw.enums;

import lombok.Getter;

@Getter
public enum ApiResponseStatus {

    SUCCESS("success"),
    FAIL("fail"),
    ERROR("error");

    private String value;

    ApiResponseStatus(String value) {
        this.value = value;
    }
}
