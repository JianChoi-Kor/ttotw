package com.project.ttotw.lib;

import com.project.ttotw.entity.File;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;

public interface FtpUtils {

    void open();
    void close();
    File upload(String path, MultipartFile multipartFile);
    void delete(String path);
    void getImage(String path, ServletOutputStream servletOutputStream);
}
