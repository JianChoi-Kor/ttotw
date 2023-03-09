package com.project.ttotw.dto;

import com.project.ttotw.enums.Continent;
import com.project.ttotw.enums.WineType;
import lombok.Getter;
import lombok.Setter;

public class WineRequestDto {

    @Getter
    @Setter
    public static class Recommend {

        //화이트 || 레드
        private WineType type;

        //가격
        private Integer price;

        //당도
        private Integer dry;

        //바디
        private Integer body;

        //산미
        private Integer acidity;

        //타닌
        private Integer tannin;

        //신대륙 || 구대륙
        private Continent continent;

        //더보기
        private boolean add;
    }
}
