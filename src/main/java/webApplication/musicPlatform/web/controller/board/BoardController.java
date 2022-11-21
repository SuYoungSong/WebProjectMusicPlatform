package webApplication.musicPlatform.web.controller.board;

import webApplication.musicPlatform.web.PageView;
import webApplication.musicPlatform.web.Repository.board.BoardImageRepository;
import webApplication.musicPlatform.web.Repository.board.BoardRepository;
import webApplication.musicPlatform.web.controller.ControllerInter;
import webApplication.musicPlatform.web.domain.Board;
import webApplication.musicPlatform.web.domain.BoardImage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class BoardController implements ControllerInter {
    @Override
    public PageView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BoardRepository boardRepository = new BoardRepository();

        return new PageView("board");
    }
}
