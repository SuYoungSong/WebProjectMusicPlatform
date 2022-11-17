package webApplication.musicPlatform.web.Repository.music;

import lombok.extern.slf4j.Slf4j;
import webApplication.musicPlatform.web.Repository.DBConnectionUtil;
import webApplication.musicPlatform.web.domain.MusicImage;
import webApplication.musicPlatform.web.domain.VideoImage;

import java.sql.*;
import java.util.NoSuchElementException;

@Slf4j
public class MusicImageFileRepository {
    public int upload(MusicImage musicImage) throws SQLException {
        String sql = "insert into musicimage(musicNumber, serverFilePath) values(?,?)";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, musicImage.getMusicNumber());
            pstmt.setString(2, musicImage.getServerFilePath());

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

    public VideoImage findByNumber(int musicNumber) throws SQLException {
        String sql = "select * from musicimage where musicNumber=?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, musicNumber);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                VideoImage videoImage = new VideoImage();
                videoImage.setVideoNumber(rs.getInt("videoNumber"));
                videoImage.setServerFilePath(rs.getString("serverFilePath"));

                return videoImage;
            } else {
                throw new NoSuchElementException("musicImageFile not found userId="+ musicNumber);
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
