package webApplication.musicPlatform.web.controller.login;

import webApplication.musicPlatform.web.PageView;
import webApplication.musicPlatform.web.controller.ControllerInter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginController implements ControllerInter {
    @Override
    public PageView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Object loginUser = request.getSession().getAttribute("loginUser");
        if(!(loginUser == null)){
            // 이미 로그인된 상태일 경우 메인페이지로 이동
            return new PageView("index");    
        }


        //로그인 이후 이전페이지로 돌아가기 위한 세션 저장
        request.getHeader("Referer");
        request.setAttribute("loginReferer",request.getHeader("Referer"));
        
        return new PageView("login");
    }
}
