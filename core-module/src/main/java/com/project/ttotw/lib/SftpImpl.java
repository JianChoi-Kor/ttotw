package com.project.ttotw.lib;

import com.jcraft.jsch.*;
import com.project.ttotw.entity.File;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.UUID;
import java.util.Vector;

@Slf4j
@Component
public class SftpImpl implements FtpUtils{

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

    @Override
    public void open() {
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

            //sftp 채널 열기 및 접속
            channel = jSchSession.openChannel("sftp");
            channel.connect();

        } catch (JSchException e) {
            e.printStackTrace();
            log.error("SFTP:: server connect failed.");
        }

        //채널을 FTP 용 채널 객체로 캐스팅
        channelSftp = (ChannelSftp) channel;
    }

    @Override
    public void close() {
        if (jSchSession.isConnected()) {
            channelSftp.disconnect();
            jSchSession.disconnect();
        }
    }

    @Override
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
            return null;
        } finally {
            close();
        }

        return File.builder()
                .originName(originName)
                .savedName(savedName)
                .savedPath(savedPath)
                .fileExt(extension)
                .useAt(true)
                .build();
    }

    @Override
    public void delete(String path) {

    }

    private void createDirectory(String firstDirectoryPath, String uploadPath) throws IOException {
        try {
            //check document root
            if (!this.exists(fileServerDocumentRoot)) {
                log.error("SFTP:: server doesn't exists root directory");
                throw new IOException();
            }

            //directory check, if not exists create
            if (!this.exists(firstDirectoryPath)) {
                channelSftp.mkdir(firstDirectoryPath);
            }
            if (!this.exists(uploadPath)) {
                channelSftp.mkdir(uploadPath);
            }
        } catch (SftpException e) {
            log.error("SFTP:: create directory failed.");
            e.printStackTrace();
        }
    }

    //경로가 존재하는지 확인
    private boolean exists(String path) {
        Vector res = null;
        try {
            res = channelSftp.ls(path);
        } catch (SftpException e) {
            if (e.id == ChannelSftp.SSH_FX_NO_SUCH_FILE) {
                return false;
            } else {
                e.printStackTrace();
            }
        }
        return res != null && !res.isEmpty();
    }

    @Override
    public void getImage(String path, ServletOutputStream servletOutputStream) {
        open();
        try {
            if (exists(path)) {
                try (InputStream inputStream = channelSftp.get(path); OutputStream outputStream = servletOutputStream) {
                    int length;
                    byte[] buffer = new byte[1024];
                    while ((length = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, length);
                    }
                } catch (SftpException e) {
                    log.error("SFTP:: get image failed.");
                    e.printStackTrace();
                } catch (IOException e) {
                    log.error("SFTP:: get image failed.");
                    e.printStackTrace();
                }
            }
        } finally {
            close();
        }
    }
}
