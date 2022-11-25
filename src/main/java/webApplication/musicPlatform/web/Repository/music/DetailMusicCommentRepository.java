package webApplication.musicPlatform.web.Repository.music;

import lombok.extern.slf4j.Slf4j;
import webApplication.musicPlatform.web.Repository.ParentRepository;
import webApplication.musicPlatform.web.domain.BoardComment;
import webApplication.musicPlatform.web.domain.DetailMusicComment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Slf4j
public class DetailMusicCommentRepository extends ParentRepository {
    public DetailMusicComment save(DetailMusicComment detailMusicComment) throws SQLException {
        String sql = "insert into musiccomment(writer, text, musicNumber) values(?,?,?)";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, detailMusicComment.getWriter());
            pstmt.setString(2, detailMusicComment.getCommentText());
            pstmt.setInt(3, detailMusicComment.getMusicNumber());

            pstmt.executeUpdate();
            return detailMusicComment;
        } catch (SQLException e) {
            log.error("db error", e);
            throw e;
        } finally {
            {
                close(con, pstmt, null);
            }
        }
    }

    public ArrayList<DetailMusicComment> findByNumber(int musicNumber) throws SQLException {
        String sql = "select * from musiccomment where musicNumber=?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        ArrayList<DetailMusicComment> detailMusicCommentArrayList = new ArrayList<>();
        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, musicNumber);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                DetailMusicComment detailMusicComment = new DetailMusicComment();
                detailMusicComment.setWriter(rs.getString("writer"));
                detailMusicComment.setCommentText(rs.getString("text"));
                detailMusicComment.setMusicNumber(rs.getInt("musicNumber"));
                detailMusicCommentArrayList.add(detailMusicComment);
            }
        } catch (SQLException e) {
            log.error("db error", e);
        } finally {
            close(con, pstmt, rs);
        }
        return detailMusicCommentArrayList;
    }
}

