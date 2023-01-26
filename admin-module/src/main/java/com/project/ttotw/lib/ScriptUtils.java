package com.project.ttotw.lib;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Service
public class ScriptUtils {

    /* 유틸리티 클래스의 불필요한 인스턴스화를 방지 */
    private ScriptUtils() {}

    public static void init(HttpServletResponse response) {
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
    }

    public static void alert(HttpServletResponse response, String alertMessage) throws IOException {
        init(response);
        PrintWriter out = response.getWriter();
        out.println("<script>alert('" + alertMessage + "');</script>");
        out.flush();
    }

    public static void alertAndMovePage(HttpServletResponse response, String alertMessage, String movePage) throws IOException {
        init(response);
        PrintWriter out = response.getWriter();
        out.println("<script>alert('" + alertMessage + "'); location.href='" + movePage + "';</script>");
        out.flush();
    }

    public static void alertAndBackPage(HttpServletResponse response, String alertMessage) throws IOException {
        init(response);
        PrintWriter out = response.getWriter();
        out.println("<script>alert('" + alertMessage + "'); history.go(-1);</script>");
        out.flush();
    }
}
