package webApplication.musicPlatform.web.controller.upload;

import webApplication.musicPlatform.web.PageView;
import webApplication.musicPlatform.web.controller.ControllerInter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MusicVideoFileUploadController implements ControllerInter {
    @Override
    public PageView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 비로그인시 사용 불가 처리
        if ( request.getSession().getAttribute("loginUser") == null){
            // 결과 페이지에 전달할 오류 내용
            request.setAttribute("uploadError","로그인 후 사용하실 수 있습니다.");
            return new PageView("fileUpload-result");
        }
        return new PageView("musicVideoFileUpload");
    }
}
