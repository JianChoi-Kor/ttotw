package com.project.ttotw.entity;

import com.project.ttotw.dto.EnumResDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "grape_varieties")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class GrapeVarieties {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    //품종명
    @Column(name = "origin_name", nullable = false)
    private String originName;

    //품종명(한글)
    @Column(name = "korean_name", nullable = false)
    private String koreanName;

    //설명
    @Column(name = "description", columnDefinition = "LONGTEXT", nullable = false)
    private String description;
}
