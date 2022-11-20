package webApplication.musicPlatform.web.Repository.board;

import lombok.extern.slf4j.Slf4j;
import webApplication.musicPlatform.web.Repository.ParentRepository;
import webApplication.musicPlatform.web.domain.Board;
import webApplication.musicPlatform.web.domain.UserProfileImage;

import java.sql.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@Slf4j
public class
BoardRepository extends ParentRepository {
    public int write(Board board) throws SQLException {
        String sql = "insert into board(writer, title, content, category) values(?,?,?,?)";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, board.getWriter());
            pstmt.setString(2, board.getTitle());
            pstmt.setString(3, board.getContent());
            pstmt.setString(4, board.getCategory());

            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();                // 쿼리 실행 후 생성된 키 값 반환
            int boardNumber = (rs.next())?rs.getInt(1):null;

            return boardNumber;
        } catch (SQLException e) {
            log.error("db error", e);
            throw e;
        } finally {
            {
                close(con, pstmt, null);
            }
        }
    }

    public LinkedHashMap<Integer,Board> callPosts() throws SQLException {
        String sql = "select * from board order by boardNumber DESC";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        LinkedHashMap<Integer, Board> posts = new LinkedHashMap<>();
        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                Board board = new Board();
                int boardNumber = rs.getInt("boardNumber");
                board.setWriter(rs.getString("writer"));
                board.setTitle(rs.getString("title"));
                board.setContent(rs.getString("content"));
                board.setCategory(rs.getString("category"));

                posts.put(boardNumber, board);
            }
            return posts;
        } catch(SQLException e){
            log.error("db error", e);
            throw e;
        } finally{
            {
                close(con, pstmt, null);
            }
        }
    }

    public Board findById(String id) throws SQLException {
        String sql = "select * from board where writer=?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, id);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                Board board = new Board();
                board.setWriter(rs.getString("writer"));
                board.setTitle(rs.getString("title"));
                board.setContent(rs.getString("content"));
                board.setCategory(rs.getString("category"));

                return board;
            } else {
                throw new NoSuchElementException("board not found userId="+id);
            }
        } catch (SQLException e) {
            log.error("db error", e);
            throw e;
        } finally {
            close(con, pstmt, rs);
        }
    }
}
