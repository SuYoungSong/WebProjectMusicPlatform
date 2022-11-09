package webApplication.musicPlatform.web.controller;

import webApplication.musicPlatform.web.PageView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginController implements ControllerInter{
    @Override
    public PageView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //로그인 이후 이전페이지로 돌아가기 위한 세션 저장
        request.getHeader("Referer");
        request.setAttribute("loginReferer",request.getHeader("Referer"));
        
        return new PageView("login");
    }
}
