package com.project.ttotw.lib;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.IOException;
import java.io.PrintWriter;

public class FtpUtils {

    private String server;
    private int port;
    private String user;
    private String password;
    private FTPClient ftp;

    //constructor (need set value)

    public void open() throws IOException {
        ftp = new FTPClient();
        //팔요 여부 확인
        ftp.setControlEncoding("UTF-8");

        //PrintCommandListener 를 추가하여 표준 출력에 대한 명령줄 도구를 사용하여 FTP 서버에 연결할 때 일반적으로 표시되는 응답을 인쇄합니다.
        ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));

        //ftp 서버 연결
        ftp.connect(server, port);

        //ftp 서버에 정상적으로 연결되었는지 확인
        int reply = ftp.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            ftp.disconnect();
            throw new IOException("Exception in connecting to FTP Server");
        }

        // 현재 커넥션 timeout 을 millisecond 값으로 입력합니다.
        ftp.setSoTimeout(10000);
        //ftp 서버 로그인
        ftp.login(user, password);
        //file type 설정 (default FTP.BINARY_FILE_TYPE? FTP.ASCII_FILE_TYPE?)
        ftp.setFileType(FTP.BINARY_FILE_TYPE);
    }

    public void close() throws IOException {
        ftp.disconnect();
    }

    //단일 업로드

    //다중 업로드

    //다운로드

    //파일 삭제

    //폴더 생성
}
