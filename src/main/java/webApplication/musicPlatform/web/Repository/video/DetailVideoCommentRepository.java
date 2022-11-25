package webApplication.musicPlatform.web.Repository.video;

import lombok.extern.slf4j.Slf4j;
import webApplication.musicPlatform.web.Repository.ParentRepository;
import webApplication.musicPlatform.web.domain.DetailMusicComment;
import webApplication.musicPlatform.web.domain.DetailVideoComment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Slf4j
public class DetailVideoCommentRepository extends ParentRepository {
    public DetailVideoComment save(DetailVideoComment detailVideoComment) throws SQLException {
        String sql = "insert into videocomment(writer, text, videoNumber) values(?,?,?)";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, detailVideoComment.getWriter());
            pstmt.setString(2, detailVideoComment.getCommentText());
            pstmt.setInt(3, detailVideoComment.getVideoNumber());

            pstmt.executeUpdate();
            return detailVideoComment;
        } catch (SQLException e) {
            log.error("db error", e);
            throw e;
        } finally {
            {
                close(con, pstmt, null);
            }
        }
    }

    public ArrayList<DetailVideoComment> findByNumber(int musicNumber) throws SQLException {
        String sql = "select * from videocomment where videoNumber=?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        ArrayList<DetailVideoComment> detailVideoCommentArrayList = new ArrayList<>();
        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, musicNumber);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                DetailVideoComment detailMusicComment = new DetailVideoComment();
                detailMusicComment.setWriter(rs.getString("writer"));
                detailMusicComment.setCommentText(rs.getString("text"));
                detailMusicComment.setVideoNumber(rs.getInt("VideoNumber"));
                detailVideoCommentArrayList.add(detailMusicComment);
            }
        } catch (SQLException e) {
            log.error("db error", e);
        } finally {
            close(con, pstmt, rs);
        }
        return detailVideoCommentArrayList;
    }
}

