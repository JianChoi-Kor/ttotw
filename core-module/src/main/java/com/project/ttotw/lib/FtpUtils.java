package com.project.ttotw.lib;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

@Component
@Slf4j
public class FtpUtils {
    @Value("${ttotw.ftp.server}")
    private String server;
    @Value("${ttotw.ftp.port}")
    private int port;
    @Value("${ttotw.ftp.username}")
    private String username;
    @Value("${ttotw.ftp.password}")
    private String password;
    private FTPClient ftp;

    //constructor (need set value)

    public void open() {
        ftp = new FTPClient();
        //default controlEncoding 값이 "ISO-8859-1" 때문에 한글 파일의 경우 파일명이 깨짐
        //ftp server 에 저장될 파일명을 uuid 등의 방식으로 한글을 사용하지 않고 저장할 경우 UTF-8 설정이 따로 필요하지 않다.
        ftp.setControlEncoding("UTF-8");
        //PrintCommandListener 를 추가하여 표준 출력에 대한 명령줄 도구를 사용하여 FTP 서버에 연결할 때 일반적으로 표시되는 응답을 출력
        ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out), true));

        try {
            //ftp 서버 연결
            ftp.connect(server, port);

            //ftp 서버에 정상적으로 연결되었는지 확인
            int reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                log.error("");
            }

            //socketTimeout 값 설정
            ftp.setSoTimeout(1000);
            //ftp 서버 로그인
            ftp.login(username, password);
            //file type 설정 (default FTP.ASCII_FILE_TYPE)
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("");
        }
    }

    public void close() {
        try {
            ftp.logout();
            ftp.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("");
        }
    }

    //단일 업로드(기본)
    public void upload(MultipartFile file) {
        open();
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
            ftp.storeFile(file.getOriginalFilename(), inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("");
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
                log.error("");
            }
            close();
        }
    }

    //파일 삭제
    public void delete(String filePath) {
        open();
        try {
            ftp.deleteFile(filePath);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("");
        } finally {
            close();
        }
    }

    //다중 업로드

    //다운로드

    //폴더 생성
}
