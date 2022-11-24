package webApplication.musicPlatform.web.controller.music;

import webApplication.musicPlatform.web.PageView;
import webApplication.musicPlatform.web.controller.ControllerInter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MusicController implements ControllerInter {
    @Override
    public PageView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return new PageView("index");
    }
}
