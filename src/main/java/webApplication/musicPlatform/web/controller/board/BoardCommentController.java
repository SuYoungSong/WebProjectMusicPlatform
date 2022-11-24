package webApplication.musicPlatform.web.controller.board;

import webApplication.musicPlatform.web.PageView;
import webApplication.musicPlatform.web.Repository.board.BoardCommentRepository;
import webApplication.musicPlatform.web.controller.ControllerInter;
import webApplication.musicPlatform.web.domain.Board;
import webApplication.musicPlatform.web.domain.BoardComment;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class BoardCommentController implements ControllerInter {
    @Override
    public PageView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        BoardCommentRepository boardCommentRepository = new BoardCommentRepository();

        String writer = request.getParameter("writer");
        int boardNumber = Integer.parseInt(request.getParameter("boardNumber"));
        String commentText = request.getParameter("text");


        BoardComment boardComment = new BoardComment(
                writer,
                commentText,
                boardNumber
        );

        try {
            boardCommentRepository.save(boardComment);
        } catch (SQLException e) {
            // 댓글 등록 실패
        }

        return new PageView("board");
    }
}
