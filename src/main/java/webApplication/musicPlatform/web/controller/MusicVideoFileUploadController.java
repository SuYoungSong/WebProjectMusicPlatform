package webApplication.musicPlatform.web.controller;

import webApplication.musicPlatform.web.PageView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MusicVideoFileUploadController implements ControllerInter{
    @Override
    public PageView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return new PageView("musicVideoFileUpload");
    }
}
