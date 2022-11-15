package webApplication.musicPlatform.web.controller.userFind;

import webApplication.musicPlatform.web.PageView;
import webApplication.musicPlatform.web.controller.ControllerInter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class userFindController implements ControllerInter {
    @Override
    public PageView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return new PageView("userFind");
    }
}
