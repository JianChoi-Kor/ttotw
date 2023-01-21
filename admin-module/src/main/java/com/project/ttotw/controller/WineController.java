package com.project.ttotw.controller;

import com.project.ttotw.dto.WineRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

        //내려주는 데이터 grade, type, country, grapeVarieties 추가

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
        return modelAndView;
    }

    @ResponseBody
    @PostMapping("/modify")
    public ResponseEntity<?> modifyWine(WineRequestDto.ModifyWine modifyWine) {
        return ResponseEntity.ok().build();
    }
}
