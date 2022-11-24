package webApplication.musicPlatform.web.Repository.board;

import lombok.extern.slf4j.Slf4j;
import webApplication.musicPlatform.web.Repository.ParentRepository;
import webApplication.musicPlatform.web.domain.BoardComment;
import webApplication.musicPlatform.web.domain.BoardImage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Slf4j
public class BoardCommentRepository extends ParentRepository {
    public BoardComment save(BoardComment boardComment) throws SQLException {
        String sql = "insert into boardcomment(writer, text, boardNumber) values(?,?,?)";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, boardComment.getWriter());
            pstmt.setString(2, boardComment.getCommentText());
            pstmt.setInt(3, boardComment.getBoardNumber());

            pstmt.executeUpdate();
            return boardComment;
        } catch (SQLException e) {
            log.error("db error", e);
            throw e;
        } finally {
            {
                close(con, pstmt, null);
            }
        }
    }

    public ArrayList<BoardComment> findByNumber(int boardNumber) throws SQLException {
        String sql = "select * from boardcomment where boardNumber=?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        // 이미지 저장용
        ArrayList<BoardComment> boardCommentArrayList = new ArrayList<>();
        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, boardNumber);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                BoardComment boardComment = new BoardComment();
                boardComment.setWriter(rs.getString("writer"));
                boardComment.setCommentText(rs.getString("text"));
                boardComment.setBoardNumber(rs.getInt("boardNumber"));
                boardCommentArrayList.add(boardComment);
            }
        } catch (SQLException e) {
            log.error("db error", e);
        } finally {
            close(con, pstmt, rs);
        }
        return boardCommentArrayList;
    }
}

