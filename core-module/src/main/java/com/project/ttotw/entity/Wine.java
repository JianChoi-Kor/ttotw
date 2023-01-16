package com.project.ttotw.entity;

import com.project.ttotw.enums.CountryOfOrigin;
import com.project.ttotw.enums.WineGrade;
import com.project.ttotw.enums.WineType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Table(name = "wine")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Wine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    //등급
    @Enumerated(EnumType.STRING)
    @Column(name = "grade", nullable = false)
    private WineGrade grade;

    //타입
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private WineType type;

    //제품명
    @Column(name = "origin_name", nullable = false)
    private String originName;

    //제품명(한글)
    @Column(name = "korean_name", nullable = false)
    private String koreanName;

    //최소 가격
    @Column(name = "min_amount", nullable = false)
    private Long minAmount;

    //최고 가격
    @Column(name = "max_amount", nullable = false)
    private Long maxAmount;

    //타닌
    @Column(name = "tannin", nullable = false)
    private Integer tannin;

    //바디
    @Column(name = "body", nullable = false)
    private Integer body;

    //산미
    @Column(name = "acidity", nullable = false)
    private Integer acidity;

    //당도
    @Column(name = "dry", nullable = false)
    private Integer dry;

    //생산지 분류
    @Column(name = "country", nullable = false)
    private CountryOfOrigin country;

    //생산지 상세(영문)
    private String countryDetails;

    //품종

}
