package webApplication.musicPlatform.web.controller;

import webApplication.musicPlatform.web.PageView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutController implements ControllerInter{
    @Override
    public PageView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 세션에서 로그인 정보를 찾은 후 해당 정보를 제거
        HttpSession session = request.getSession();
        session.removeAttribute("loginUser");

        String referer = getReferer(request);
        return new PageView(referer);
    }

    private String getReferer(HttpServletRequest request) {
        String referer = request.getHeader("Referer");
        if (!referer.equals("")) {
            String host = request.getHeader("Host");
            referer = referer.replaceAll(host, "");
            referer = referer.replaceAll("http://", "");
            referer = referer.replaceAll("https://", "");
            if(!referer.equals("/")) {
                String[] refererSplit = referer.split("/");
                referer = refererSplit[refererSplit.length - 1];
            }
        }else{ referer = "index";}

        return referer;
    }

}
