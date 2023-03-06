package com.project.ttotw.controller;

import com.project.ttotw.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/test")
@RestController
public class TestController {

    private final ApiResponse response;
}
