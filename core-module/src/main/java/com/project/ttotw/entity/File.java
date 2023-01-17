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

    //저장 경로
    @Column(name = "saved_path", nullable = false)
    private String savedPath;

    //파일 크기
    @Column(name = "file_size", nullable = false)
    private Integer fileSize;

    //파일 확장자
    @Column(name = "file_ext", nullable = false)
    private String fileExt;

    //삭제 여부
    @Column(name = "use_at", nullable = false)
    private boolean useAt;
}
