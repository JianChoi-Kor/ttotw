package com.project.ttotw.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@Controller
@RequestMapping("/wine")
public class WineController {

    @GetMapping("/list")
    public ModelAndView getWineList() {
        ModelAndView modelAndView = new ModelAndView("page/wine/wine_list");
        return modelAndView;
    }
}
