package webApplication.musicPlatform.web.controller;

import webApplication.musicPlatform.web.PageView;
import webApplication.musicPlatform.web.Repository.video.VideoImageFileRepository;
import webApplication.musicPlatform.web.Repository.video.VideoRepository;
import webApplication.musicPlatform.web.domain.Video;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class DetailVideoController implements ControllerInter {
    @Override
    public PageView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int videoNumber = Integer.parseInt(request.getParameter("no"));

        VideoRepository videoRepository = new VideoRepository();
        VideoImageFileRepository videoImageFileRepository = new VideoImageFileRepository();
        Video video = null;
        try {
            video = videoRepository.findByNumber(videoNumber);
//            videoImageFileRepository.findByNumber(videoNumber);
        } catch (SQLException e) {
            // 비디오 탐색 실패
        }

        request.setAttribute("videoInfo",video);

        return new PageView("detailVideo");
    }
}
