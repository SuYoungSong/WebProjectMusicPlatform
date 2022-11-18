package webApplication.musicPlatform.web.controller.login.board;

import lombok.extern.slf4j.Slf4j;
import webApplication.musicPlatform.web.PageView;
import webApplication.musicPlatform.web.Repository.board.BoardRepository;
import webApplication.musicPlatform.web.controller.ControllerInter;
import webApplication.musicPlatform.web.domain.Board;
import webApplication.musicPlatform.web.domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
public class BoardProcessController implements ControllerInter {
    @Override
    public PageView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("loginUser");
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String category = "free";
        BoardRepository boardRepository = new BoardRepository();

        Board board = new Board(
                user.getId(),
                title,
                content,
                category
        );

        try {
            boardRepository.write(board);
        } catch (SQLException e) {
            // 글쓰기 실패
            log.error("post save fail user = "+user.getId());
        }


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
