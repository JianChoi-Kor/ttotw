package com.project.ttotw.enums;

import lombok.Getter;

@Getter
public enum FileDirectory {
    WINE("wine");

    private String directoryName;

    FileDirectory(String directoryName) {
        this.directoryName = directoryName;
    }
}
