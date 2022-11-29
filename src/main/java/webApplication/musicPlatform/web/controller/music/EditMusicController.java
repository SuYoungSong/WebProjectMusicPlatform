package webApplication.musicPlatform.web.controller.music;

import webApplication.musicPlatform.web.PageView;
import webApplication.musicPlatform.web.Repository.music.MusicRepository;
import webApplication.musicPlatform.web.Repository.video.VideoRepository;
import webApplication.musicPlatform.web.controller.ControllerInter;
import webApplication.musicPlatform.web.domain.Music;
import webApplication.musicPlatform.web.domain.Video;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class EditMusicController implements ControllerInter {
    @Override
    public PageView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int musicNumber = Integer.parseInt(request.getParameter("musicNumber"));
        Music music = null;
        MusicRepository videoRepository = new MusicRepository();
        try {
            music = videoRepository.findByNumber(musicNumber);
        } catch (SQLException e) {
            // 음악 찾기 실패
        }

        request.setAttribute("editMusic", music);
        request.setAttribute("musicNumber", musicNumber);


        return new PageView("editMusic");
    }
}
