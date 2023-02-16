package com.project.ttotw.lib;

import com.jcraft.jsch.*;
import com.project.ttotw.entity.File;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.UUID;

@Slf4j
@Component
public class SftpUtils {

    @Value("${ttotw.sftp.server}")
    private String server;
    @Value("${ttotw.sftp.port}")
    private int port;
    @Value("${ttotw.sftp.username}")
    private String username;
    @Value(("${ttotw.sftp.privateKey}"))
    private String privateKey;
    @Value("${ttotw.sftp.documentRoot}")
    private String fileServerDocumentRoot;

    private final String SEPARATOR = "/";
    private final String PERIOD = ".";

    private JSch jSch;
    private Session jSchSession;
    private Channel channel;
    private ChannelSftp channelSftp;

    private void open() {
        //JSch 객체를 생성
        jSch = new JSch();

        try {
            //privateKey 인증
            jSch.addIdentity(privateKey);

            //JSchSession 객체를 생성 (사용자 이름, 접속할 호스트, 포트 전달)
            jSchSession = jSch.getSession(username, server, port);

            //기타 설정 적용
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            jSchSession.setConfig(config);
            //접속
            jSchSession.connect();

            //sftp 채널 열기
            channel = jSchSession.openChannel("sftp");

            //채널을 FTP 용 채널 객체로 캐스팅
            channelSftp = (ChannelSftp) channel;
            channelSftp.connect();

        } catch (JSchException e) {
            e.printStackTrace();
            log.error("SFTP:: server connect failed.");
        }
    }

    private void close() {
        if (jSchSession.isConnected()) {
            channelSftp.disconnect();
            jSchSession.disconnect();
        }
    }

    public File upload(String firstDirectory, MultipartFile file) {
        open();

        String originName = StringUtils.getFilename(file.getOriginalFilename());
        String savedName = "";
        String savedPath = "";
        String extension = "";

        try (InputStream inputStream = file.getInputStream()){
            //directory to store files for today's date
            String yyyyMMdd = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            //first directory path
            String firstDirectoryPath = fileServerDocumentRoot + SEPARATOR + firstDirectory;
            //upload directory path
            String uploadDirectoryPath = fileServerDocumentRoot + SEPARATOR + firstDirectory + SEPARATOR + yyyyMMdd;
            savedPath = uploadDirectoryPath;

            //create directory
            createDirectory(firstDirectoryPath, uploadDirectoryPath);

            //create uuid file name
            String uuidFileName = UUID.randomUUID().toString();
            savedName = uuidFileName;

            //file extension
            String fileExtension = StringUtils.getFilenameExtension(file.getOriginalFilename());
            extension = fileExtension;

            channelSftp.put(inputStream, uploadDirectoryPath + SEPARATOR + uuidFileName + PERIOD + fileExtension);
        } catch (IOException | SftpException e) {
            log.error("SFTP:: file upload failed.");
            e.printStackTrace();
        } finally {
            close();
        }

        File imageFile = File.builder()
                .originName(originName)
                .savedName(savedName)
                .savedPath(savedPath)
                .fileExt(extension)
                .useAt(true)
                .build();

        return imageFile;
    }

    private void createDirectory(String firstDirectoryPath, String uploadPath) throws IOException {
        try {
            //check document root
            if (!channelSftp.cd(fileServerDocumentRoot)) {
                log.error("SFTP:: server doesn't exists root directory");
                throw new IOException();
            }

            //directory check, if not exists create
            if (!channelSftp.cd(firstDirectoryPath)) {
                channelSftp.mkdir(firstDirectoryPath);
            }
            if (!channelSftp.cd(uploadPath)) {
                channelSftp.mkdir(uploadPath);
            }
        } catch (SftpException e) {
            e.printStackTrace();
        }
    }
}
