package webApplication.musicPlatform.web.Repository.board;

import lombok.extern.slf4j.Slf4j;
import webApplication.musicPlatform.web.Repository.ParentRepository;
import webApplication.musicPlatform.web.domain.BoardImage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

@Slf4j
public class BoardImageRepository extends ParentRepository {

    public BoardImage save(BoardImage boardImage) throws SQLException {
        String sql = "insert into boardimage(boardNumber, serverFilePath) values(?,?)";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, boardImage.getBoardNumber());
            pstmt.setString(2, boardImage.getServerFilePath());

            pstmt.executeUpdate();
            return boardImage;
        } catch (SQLException e) {
            log.error("db error", e);
            throw e;
        } finally {
            {
                close(con, pstmt, null);
            }
        }
    }

    public ArrayList<BoardImage> findByNumber(int boardNumber) throws SQLException {
        String sql = "select * from boardimage where boardNumber=?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        // 이미지 저장용
        ArrayList<BoardImage> imageArrayList = new ArrayList<>();
        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, boardNumber);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                BoardImage boardImage = new BoardImage();
                boardImage.setBoardNumber(rs.getInt("boardNumber"));
                boardImage.setServerFilePath(rs.getString("serverFilePath"));
                imageArrayList.add(boardImage);
            }
        } catch (SQLException e) {
            log.error("db error", e);
        } finally {
            close(con, pstmt, rs);
        }
        return imageArrayList;
    }
}
