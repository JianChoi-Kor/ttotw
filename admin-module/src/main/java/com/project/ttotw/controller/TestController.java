package com.project.ttotw.controller;

import com.project.ttotw.entity.File;
import com.project.ttotw.lib.SftpUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("/test")
public class TestController {

    private final SftpUtils sftpUtils;

    @GetMapping("")
    public void test(@RequestPart MultipartFile multipartFile) {
        File file = sftpUtils.upload("", multipartFile);
        file.getFileExt();
    }
}
