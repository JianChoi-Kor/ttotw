package com.project.ttotw.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "file")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class File extends BaseDateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    //원본 파일명
    @Column(name = "origin_name", nullable = false)
    private String originName;

    //저장된 파일명
    @Column(name = "saved_name", nullable = false)
    private String savedName;

    //저장된 호스트
    @Column(name = "saved_host", nullable = false)
    private String savedHost;

    //저장 경로
    @Column(name = "saved_path_1", nullable = false)
    private String savedPath1;

    @Column(name = "saved_path_2", nullable = false)
    private String savedPath2;

    //파일 확장자
    @Column(name = "file_ext", nullable = false)
    private String fileExt;

    //삭제 여부
    @Column(name = "use_at", nullable = false)
    private boolean useAt;

    public String fullFilePath() {
        final String SEPARATOR = "/";
        final String PERIOD = ".";

        return this.getSavedPath1() + getSavedPath2() + SEPARATOR + this.getSavedName() + PERIOD + this.getFileExt();
    }
}
