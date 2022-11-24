package webApplication.musicPlatform.web.controller.music;

import webApplication.musicPlatform.web.PageView;
import webApplication.musicPlatform.web.controller.ControllerInter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class genereMusicController implements ControllerInter {
    @Override
    public PageView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String genere;
        try {
            genere = request.getParameter("genere");
        }catch(Exception e){
            genere = "발라드";     // 임시로 지정
        }

        request.setAttribute("genere",genere);
        return new PageView("genereMusic");
    }
}
