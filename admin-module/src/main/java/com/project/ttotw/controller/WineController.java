package com.project.ttotw.controller;

import com.project.ttotw.dto.EnumResDto;
import com.project.ttotw.dto.WineRequestDto;
import com.project.ttotw.enums.CountryOfOrigin;
import com.project.ttotw.enums.WineGrade;
import com.project.ttotw.enums.WineType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/wine")
public class WineController {

    @GetMapping("")
    public ModelAndView getWineList() {
        ModelAndView modelAndView = new ModelAndView("page/wine/wine_list");
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView getWineDetail(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("page/wine/wine_list");
        return modelAndView;
    }

    @GetMapping("/register")
    public ModelAndView registerWine() {
        ModelAndView modelAndView = new ModelAndView("page/wine/wine_register");

        //CountryOfOrigin Enum
        List<EnumResDto.CommonEnumRes> countryEnumList = CountryOfOrigin.getEnumList();
        modelAndView.addObject("countryEnumList", countryEnumList);

        //WineGrad Enum
        List<EnumResDto.CommonEnumRes> wineGradeEnumList = WineGrade.getEnumList();
        modelAndView.addObject("wineGradeEnumList", wineGradeEnumList);

        //WineType Enum
        List<EnumResDto.CommonEnumRes> windTypeEnumList = WineType.getEnumList();
        modelAndView.addObject("wineTypeEnumList", windTypeEnumList);


        return modelAndView;
    }

    @ResponseBody
    @PostMapping("/register")
    public ResponseEntity<?> registerWine(WineRequestDto.RegisterWine registerWine) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/modify")
    public ModelAndView modifyWine() {
        ModelAndView modelAndView = new ModelAndView("page/wine/wine_modify");

        //CountryOfOrigin Enum
        List<EnumResDto.CommonEnumRes> countryEnumList = CountryOfOrigin.getEnumList();
        modelAndView.addObject("countryEnumList", countryEnumList);

        //WineGrad Enum
        List<EnumResDto.CommonEnumRes> wineGradeEnumList = WineGrade.getEnumList();
        modelAndView.addObject("wineGradeEnumList", wineGradeEnumList);

        //WineType Enum
        List<EnumResDto.CommonEnumRes> windTypeEnumList = WineType.getEnumList();
        modelAndView.addObject("wineTypeEnumList", windTypeEnumList);

        return modelAndView;
    }

    @ResponseBody
    @PostMapping("/modify")
    public ResponseEntity<?> modifyWine(WineRequestDto.ModifyWine modifyWine) {
        return ResponseEntity.ok().build();
    }
}
