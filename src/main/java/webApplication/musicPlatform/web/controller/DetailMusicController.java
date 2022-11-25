package webApplication.musicPlatform.web.controller;

import webApplication.musicPlatform.web.PageView;
import webApplication.musicPlatform.web.Repository.music.MusicImageFileRepository;
import webApplication.musicPlatform.web.Repository.music.MusicRepository;
import webApplication.musicPlatform.web.domain.Music;
import webApplication.musicPlatform.web.domain.MusicImage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class DetailMusicController implements ControllerInter {
    @Override
    public PageView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MusicRepository musicRepository = new MusicRepository();
        MusicImageFileRepository musicImageFileRepository = new MusicImageFileRepository();

        int musicNumber = Integer.parseInt(request.getParameter("no"));
        Music music = null;
        MusicImage musicImage = null;

        try {
            music = musicRepository.findByNumber(musicNumber);
            musicImage = musicImageFileRepository.findByNumber(musicNumber);
        } catch (SQLException e) {
            // 음악 정보를 찾지 못한 경우
        }

        request.setAttribute("detailMusicInfo",music);
        request.setAttribute("detailMusicImage",musicImage);


        return new PageView("detailMusic");
    }
}
