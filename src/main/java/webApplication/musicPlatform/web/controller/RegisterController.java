package webApplication.musicPlatform.web.controller;

import webApplication.musicPlatform.web.PageView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterController implements ControllerInter{
    // 회원가입 폼으로 이동시켜주는 컨트롤러
    @Override
    public PageView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return new PageView("register");
    }
}
