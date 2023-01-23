package com.project.ttotw.controller;

import com.project.ttotw.dto.EnumResDto;
import com.project.ttotw.dto.WineRequestDto;
import com.project.ttotw.entity.GrapeVarieties;
import com.project.ttotw.enums.CountryOfOrigin;
import com.project.ttotw.enums.WineGrade;
import com.project.ttotw.enums.WineType;
import com.project.ttotw.service.GrapeVarietiesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
@RequestMapping("/wine")
public class WineController {

    private final GrapeVarietiesService grapeVarietiesService;

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

        //grapeVarietiesList
        List<GrapeVarieties> grapeVarietiesList = grapeVarietiesService.findAll();
        List<EnumResDto.CommonGrapeVarietiesRes> commonGrapeVarietiesRes = grapeVarietiesList.stream()
                .map(EnumResDto.CommonGrapeVarietiesRes::from)
                .collect(Collectors.toList());
        modelAndView.addObject("commonGrapeVarietiesRes", commonGrapeVarietiesRes);

        return modelAndView;
    }

    @ResponseBody
    @PostMapping("/register")
    public ResponseEntity<?> registerWine(@ModelAttribute WineRequestDto.RegisterWine registerWine, @RequestPart MultipartFile wineImage) {
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
