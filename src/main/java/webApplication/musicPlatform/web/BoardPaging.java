package webApplication.musicPlatform.web;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import webApplication.musicPlatform.web.Repository.board.BoardImageRepository;
import webApplication.musicPlatform.web.Repository.board.BoardRepository;
import webApplication.musicPlatform.web.domain.Board;
import webApplication.musicPlatform.web.domain.BoardImage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

@RestController
public class BoardPaging {
    BoardRepository boardRepository = new BoardRepository();
    BoardImageRepository boardImageRepository = new BoardImageRepository();
    LinkedHashMap<Integer, Board> postsLimit5;

    @RequestMapping(value = { "/api/board/callPost5/{page}"})
    public LinkedHashMap<Integer, Board> callPostPage(@PathVariable  int page) {
        try {
            postsLimit5 = boardRepository.callPostsLimit5(page);
        } catch (SQLException e) {
            // 포스트 불러오기 실패시
            postsLimit5 = new LinkedHashMap<>();
        }
        return postsLimit5;
    }

    @RequestMapping(value = { "/api/board/callPostImage/{boardNum}"})
    public ArrayList<BoardImage> callPostImage(@PathVariable  int boardNum) {
        ArrayList<BoardImage> images = null;
        try {
            images = boardImageRepository.findByNumber(boardNum);
        } catch (SQLException e) {
            images = new ArrayList<BoardImage>();
        }
        return images;
    }
}
