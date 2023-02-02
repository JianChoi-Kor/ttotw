package com.project.ttotw.lib;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

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
    @Value("${ttotw.ftp.documentRoot}")
    private String fileServerDocumentRoot;

    private final String SEPARATOR = "/";

    private FTPClient ftp;

    //constructor (need set value)

    private void open() {
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
                log.error("FTPClient:: server connection failed.");
            }

            //socketTimeout 값 설정
            ftp.setSoTimeout(10000);
            //ftp 서버 로그인
            ftp.login(username, password);
            //file type 설정 (default FTP.ASCII_FILE_TYPE)
            ftp.setFileType(FTP.BINARY_FILE_TYPE);

        } catch (IOException e) {
            log.error("FTPClient:: server connection failed.");
            e.printStackTrace();
        }
    }

    private void close() {
        try {
            ftp.logout();
            ftp.disconnect();
        } catch (IOException e) {
            log.error("FTPClient:: server close failed.");
            e.printStackTrace();
        }
    }

    //단일 업로드
    public void upload(String firstDirectory, MultipartFile file) {
        open();
        InputStream inputStream = null;
        try {
            //directory to store files for today's date
            String yyyyMMdd = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            //first directory path
            String firstDirectoryPath = fileServerDocumentRoot + SEPARATOR + firstDirectory;
            //upload directory path
            String uploadDirectoryPath = fileServerDocumentRoot + SEPARATOR + firstDirectory + SEPARATOR + yyyyMMdd;

            //create directory
            createDirectory(firstDirectoryPath, uploadDirectoryPath);

            //create uuid file name
            String uuidFileName = UUID.randomUUID().toString();


            inputStream = file.getInputStream();
            ftp.storeFile(uploadDirectoryPath + SEPARATOR + uuidFileName, inputStream);
        } catch (IOException e) {
            log.error("FTPClient:: file upload failed.");
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                log.error("FTPClient:: inputStream close failed.");
                e.printStackTrace();
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
            log.error("FTPClient:: file delete failed.");
            e.printStackTrace();
        } finally {
            close();
        }
    }

    //다중 업로드

    //다운로드

    //폴더 생성
    private void createDirectory(String firstDirectoryPath, String uploadPath) throws IOException {
        //check document root
        if (!ftp.changeWorkingDirectory(fileServerDocumentRoot)) {
            log.error("FTPClient:: server doesn't exists root directory");
            throw new IOException();
        }

        //directory check, if not exists create
        if (!ftp.changeWorkingDirectory(firstDirectoryPath)) {
            ftp.makeDirectory(firstDirectoryPath);
        }
        if (!ftp.changeWorkingDirectory(uploadPath)) {
            ftp.makeDirectory(uploadPath);
        }
    }
}
