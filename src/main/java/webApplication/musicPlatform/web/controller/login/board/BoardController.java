package webApplication.musicPlatform.web.controller.login.board;

import webApplication.musicPlatform.web.PageView;
import webApplication.musicPlatform.web.Repository.board.BoardRepository;
import webApplication.musicPlatform.web.controller.ControllerInter;
import webApplication.musicPlatform.web.domain.Board;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class BoardController implements ControllerInter {
    @Override
    public PageView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BoardRepository boardRepository = new BoardRepository();
        LinkedHashMap<Integer, Board> posts;
        // 게시글 불러오기
        try {
            posts = boardRepository.callPosts();
        } catch (SQLException e) {
            posts = new LinkedHashMap<>();
        }
        request.setAttribute("posts", posts);
        return new PageView("board");
    }
}
