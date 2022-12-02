package webApplication.musicPlatform.web.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import webApplication.musicPlatform.web.Repository.board.BoardCommentRepository;
import webApplication.musicPlatform.web.Repository.music.DetailMusicCommentRepository;
import webApplication.musicPlatform.web.domain.BoardComment;
import webApplication.musicPlatform.web.domain.DetailMusicComment;

import java.util.ArrayList;

@RestController
public class DetailMusicCommentGet {

    DetailMusicCommentRepository detailMusicCommentRepository = new DetailMusicCommentRepository();
    ArrayList<DetailMusicComment> commentList;

    @RequestMapping(value = { "/api/detailMusic/callComment/{musicNumber}/{page}"})
    public ArrayList<DetailMusicComment> findByNumber(@PathVariable int musicNumber, @PathVariable int page) {

        try {
            commentList = detailMusicCommentRepository.findByNumber(musicNumber);
        } catch (Exception e) {
            // 코멘트 호출 실패
            commentList = new ArrayList<>();
        }

        return commentList;
    }
}
