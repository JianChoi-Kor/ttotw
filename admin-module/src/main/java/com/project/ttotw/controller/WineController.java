package com.project.ttotw.controller;

import com.project.ttotw.dto.EnumResDto;
import com.project.ttotw.dto.WineRequestDto;
import com.project.ttotw.dto.WineResponseDto;
import com.project.ttotw.entity.GrapeVarieties;
import com.project.ttotw.enums.CountryOfOrigin;
import com.project.ttotw.enums.WineGrade;
import com.project.ttotw.enums.WineType;
import com.project.ttotw.service.GrapeVarietiesService;
import com.project.ttotw.service.WineService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
@RequestMapping("/wine")
public class WineController {
    private final WineService wineService;
    private final GrapeVarietiesService grapeVarietiesService;

    @GetMapping("")
    public ModelAndView getWineList(WineRequestDto.SearchWineList searchWineList) {
        ModelAndView modelAndView = new ModelAndView("page/wine/wine_list");

        Page<WineResponseDto.WineListView> result = wineService.getWineList(searchWineList);
        modelAndView.addObject("result", result);

        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView getWineDetails(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("page/wine/wine_details");

        WineResponseDto.WineDetailsView result = wineService.getWineDetails(id);
        modelAndView.addObject("result", result);

        return modelAndView;
    }

    @GetMapping("/image/{fileId}")
    public void getWineImage(@PathVariable Long fileId, HttpServletResponse servletResponse) {
        wineService.getWineImage(fileId, servletResponse);
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
    @PostMapping(value = "/register")
    public ResponseEntity<?> registerWine(@ModelAttribute @Validated WineRequestDto.RegisterWine registerWine, Errors errors,
                                          @RequestPart MultipartFile wineImage) {
        if (errors.hasErrors() || wineImage == null) {
            return ResponseEntity.badRequest().build();
        }

        wineService.registerWine(registerWine, wineImage);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteWine(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }

        wineService.deleteWine(id);

        return ResponseEntity.ok().build();
    }
}
