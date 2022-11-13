package webApplication.musicPlatform.web.Repository.video;

import lombok.extern.slf4j.Slf4j;
import webApplication.musicPlatform.web.Repository.DBConnectionUtil;
import webApplication.musicPlatform.web.domain.User;
import webApplication.musicPlatform.web.domain.Video;
import webApplication.musicPlatform.web.domain.VideoFile;

import java.sql.*;
import java.util.NoSuchElementException;

@Slf4j
public class VideoFileRepository {


    public int upload(VideoFile videofile) throws SQLException{
        String sql = "insert into videofile(videoNumber, serverFilePath, userUploadFileName) values(?,?,?)";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, videofile.getVideoNumber());
            pstmt.setString(2, videofile.getServerFileName());
            pstmt.setString(3, videofile.getUserUploadFileName());

            pstmt.executeUpdate();

            rs = pstmt.getGeneratedKeys();                // 쿼리 실행 후 생성된 키 값 반환
            int videoFileNumber = (rs.next())?rs.getInt(1):null;

            return videoFileNumber;
        } catch (SQLException e) {
            log.error("db error", e);
            throw e;
        } finally {
            {
                close(con, pstmt, rs);
            }
        }
    }

    public VideoFile findByNumber(int videoNumber) throws SQLException {
        String sql = "select * from videoFile where videoNumber=?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, videoNumber);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                VideoFile videoFile = new VideoFile();
                videoFile.setVideoNumber(rs.getInt("videoNumber"));
                videoFile.setServerFileName(rs.getString("serverFilePath"));
                videoFile.setUserUploadFileName(rs.getString("userUploadFileName"));

                return videoFile;
            } else {
                throw new NoSuchElementException("videoFile not found userId="+ videoNumber);
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
