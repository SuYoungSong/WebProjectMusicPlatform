package webApplication.musicPlatform.web.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import webApplication.musicPlatform.web.Repository.music.DetailMusicCommentRepository;
import webApplication.musicPlatform.web.Repository.video.DetailVideoCommentRepository;
import webApplication.musicPlatform.web.domain.DetailMusicComment;
import webApplication.musicPlatform.web.domain.DetailVideoComment;

import java.util.ArrayList;

@RestController
public class DetailVideoCommentGet {

    DetailVideoCommentRepository detailVideoCommentRepository = new DetailVideoCommentRepository();
    ArrayList<DetailVideoComment> commentList;

    @RequestMapping(value = { "/api/detailVideo/callComment/{videoNmber}/{page}"})
    public ArrayList<DetailVideoComment> findByNumber(@PathVariable int videoNmber, @PathVariable int page) {
        try {
            commentList = detailVideoCommentRepository.findByNumber(videoNmber);
        } catch (Exception e) {
            // 코멘트 호출 실패
            commentList = new ArrayList<>();
        }

        return commentList;
    }
}
