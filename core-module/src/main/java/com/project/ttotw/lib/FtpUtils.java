package com.project.ttotw.lib;

import com.project.ttotw.entity.File;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FtpUtils {

    void open();
    void close();
    File upload(String path, MultipartFile multipartFile);
    void delete(String path);
}
