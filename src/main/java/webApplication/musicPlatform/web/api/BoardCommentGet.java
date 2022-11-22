package webApplication.musicPlatform.web.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import webApplication.musicPlatform.web.Repository.board.BoardCommentRepository;
import webApplication.musicPlatform.web.domain.BoardComment;

import java.sql.SQLException;
import java.util.ArrayList;

@RestController
public class BoardCommentGet {
    BoardCommentRepository boardCommentRepository = new BoardCommentRepository();
    ArrayList<BoardComment> commentList;

    @RequestMapping(value = { "/api/board/comment/{postNumber}"})
    public ArrayList<BoardComment> findByNumber(@PathVariable int postNumber) {
        try {
            commentList = boardCommentRepository.findByNumber(postNumber);
        } catch (Exception e) {
            // 코멘트 호출 실패
            commentList = new ArrayList<>();
        }

        return commentList;
    }
}
