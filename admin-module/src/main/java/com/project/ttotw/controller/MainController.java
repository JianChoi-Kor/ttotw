package com.project.ttotw.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping("main")
    public ModelAndView testPage() {
        ModelAndView modelAndView = new ModelAndView("page/index");
        System.out.println("get main");
        return modelAndView;
    }
}
