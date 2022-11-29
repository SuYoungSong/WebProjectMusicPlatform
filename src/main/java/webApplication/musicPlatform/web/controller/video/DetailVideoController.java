package webApplication.musicPlatform.web.controller.video;

import webApplication.musicPlatform.web.PageView;
import webApplication.musicPlatform.web.Repository.video.VideoFileRepository;
import webApplication.musicPlatform.web.Repository.video.VideoImageFileRepository;
import webApplication.musicPlatform.web.Repository.video.VideoRepository;
import webApplication.musicPlatform.web.controller.ControllerInter;
import webApplication.musicPlatform.web.domain.Video;
import webApplication.musicPlatform.web.domain.VideoFile;
import webApplication.musicPlatform.web.domain.VideoImage;

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
        VideoFileRepository videoFileRepository = new VideoFileRepository();
        VideoFile videoFile = null;
        Video video = null;
        VideoImage videoImage = null;
        try {
            video = videoRepository.findByNumber(videoNumber);
            videoImage = videoImageFileRepository.findByNumber(videoNumber);
            videoFile = videoFileRepository.findByNumber(videoNumber);
        } catch (SQLException e) {
            // 비디오 탐색 실패
        }
        request.setAttribute("videoInfo",video);
        request.setAttribute("videoFile",videoFile);
        request.setAttribute("videoImage",videoImage);
        request.setAttribute("detailVideoNumber",videoNumber);

        return new PageView("detailVideo");
    }
}
