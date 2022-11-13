package webApplication.musicPlatform.web.Repository.video;

import lombok.extern.slf4j.Slf4j;
import webApplication.musicPlatform.web.Repository.DBConnectionUtil;
import webApplication.musicPlatform.web.domain.Music;
import webApplication.musicPlatform.web.domain.Video;

import java.sql.*;
import java.util.NoSuchElementException;

@Slf4j
public class VideoRepository {

    public int upload(Video video) throws SQLException{
        String sql = "insert into video(videoName, uploadUser, videoText, genere) values(?,?,?,?)";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, video.getVideoName());
            pstmt.setString(2, video.getUploadUserId());
            pstmt.setString(3, video.getVideoDescription());
            pstmt.setString(4, video.getVideoGenre());

            pstmt.executeUpdate();

            rs = pstmt.getGeneratedKeys();                // 쿼리 실행 후 생성된 키 값 반환
            int videoNumber = (rs.next())?rs.getInt(1):null;

            return videoNumber;
        } catch (SQLException e) {
            log.error("db error", e);
            throw e;
        } finally {
            {
                close(con, pstmt, rs);
            }
        }
    }

    public Video findByNumber(int videoNumber) throws SQLException {
        String sql = "select * from video where videoNumber=?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, videoNumber);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                Video video = new Video();
                video.setVideoName(rs.getString("videoName"));
                video.setUploadUserId(rs.getString("uploadUser"));
                video.setVideoDescription(rs.getString("videoText"));
                video.setVideoGenre(rs.getString("genere"));

                return video;
            } else {
                throw new NoSuchElementException("video not found videoNumber="+videoNumber);
            }
        } catch (SQLException e) {
            log.error("db error", e);
            throw e;
        } finally {
            close(con, pstmt, rs);
        }
    }



    private Connection getConnection() {
        return DBConnectionUtil.getConnection();
    }

    private void close(Connection con, Statement stmt, ResultSet rs){
        if ( rs != null ){
            try {
                rs.close();
            } catch (SQLException e){
                log.info("error", e);
            }
        }

        if ( stmt != null ){
            try {
                stmt.close();
            } catch (SQLException e){
                log.info("error", e);
            }
        }

        if ( con != null ){
            try {
                con.close();
            } catch (SQLException e){
                log.info("error", e);
            }
        }
    }
}
