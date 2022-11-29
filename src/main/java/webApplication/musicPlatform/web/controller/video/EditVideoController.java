package webApplication.musicPlatform.web.controller.video;

import webApplication.musicPlatform.web.PageView;
import webApplication.musicPlatform.web.Repository.video.VideoRepository;
import webApplication.musicPlatform.web.controller.ControllerInter;
import webApplication.musicPlatform.web.domain.Video;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class EditVideoController implements ControllerInter {
    @Override
    public PageView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int videoNumber = Integer.parseInt(request.getParameter("videoNumber"));

        Video video = null;
        VideoRepository videoRepository = new VideoRepository();
        try {
            video = videoRepository.findByNumber(videoNumber);
        } catch (SQLException e) {
            // 비디오 찾기 실패
        }

        request.setAttribute("editVideo", video);
        request.setAttribute("videoNumber", videoNumber);


        return new PageView("editVideo");
    }
}
