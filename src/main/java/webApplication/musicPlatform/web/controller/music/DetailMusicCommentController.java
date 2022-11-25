package webApplication.musicPlatform.web.controller.music;

import webApplication.musicPlatform.web.PageView;
import webApplication.musicPlatform.web.Repository.board.BoardCommentRepository;
import webApplication.musicPlatform.web.Repository.music.DetailMusicCommentRepository;
import webApplication.musicPlatform.web.Repository.music.MusicImageFileRepository;
import webApplication.musicPlatform.web.Repository.music.MusicRepository;
import webApplication.musicPlatform.web.controller.ControllerInter;
import webApplication.musicPlatform.web.domain.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class DetailMusicCommentController implements ControllerInter {
    @Override
    public PageView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DetailMusicCommentRepository detailMusicCommentRepository = new DetailMusicCommentRepository();

        User user = (User)request.getSession().getAttribute("loginUser");
        String writer = user.getId();
        int musicNumber = Integer.parseInt(request.getParameter("musicNumber"));
        String commentText = request.getParameter("text");
        MusicRepository musicRepository = new MusicRepository();
        MusicImageFileRepository musicImageFileRepository = new MusicImageFileRepository();

        DetailMusicComment detailMusicComment = new DetailMusicComment(
                writer,
                commentText,
                musicNumber
        );

        try {
            detailMusicCommentRepository.save(detailMusicComment);
        } catch (SQLException e) {
            // 댓글 등록 실패
        }

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
        request.setAttribute("detailMusicNumber",musicNumber);


        return new PageView("detailMusic");
    }
}
