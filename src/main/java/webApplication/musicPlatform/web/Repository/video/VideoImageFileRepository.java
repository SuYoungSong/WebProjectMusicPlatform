package webApplication.musicPlatform.web.Repository.video;

import lombok.extern.slf4j.Slf4j;
import webApplication.musicPlatform.web.Repository.DBConnectionUtil;
import webApplication.musicPlatform.web.Repository.ParentRepository;
import webApplication.musicPlatform.web.domain.VideoFile;
import webApplication.musicPlatform.web.domain.VideoImage;

import java.sql.*;
import java.util.NoSuchElementException;

@Slf4j
public class VideoImageFileRepository extends ParentRepository {


    public int upload(VideoImage videoImage) throws SQLException {
        String sql = "insert into videoimage(videoNumber, serverFilePath) values(?,?)";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, videoImage.getVideoNumber());
            pstmt.setString(2, videoImage.getServerFilePath());

            pstmt.executeUpdate();

            rs = pstmt.getGeneratedKeys();                // 쿼리 실행 후 생성된 키 값 반환
            int videoFileNumber = (rs.next()) ? rs.getInt(1) : null;

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

    public VideoImage findByNumber(int videoNumber) throws SQLException {
        String sql = "select * from videoimage where videoNumber=?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, videoNumber);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                VideoImage videoImage = new VideoImage();
                videoImage.setVideoNumber(rs.getInt("videoNumber"));
                videoImage.setServerFilePath(rs.getString("serverFilePath"));

                return videoImage;
            } else {
//                throw new NoSuchElementException("videoImageFile not found userId="+ videoNumber);
                return null;
            }
        } catch (SQLException e) {
            log.error("db error", e);
            throw e;
        } finally {
            close(con, pstmt, rs);
        }
    }


    public void update(int videoNumber, String serverFilePath) throws SQLException {
        String sql = "update videoImage set serverFilePath=? where videoNumber=?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, serverFilePath);
            pstmt.setInt(2, videoNumber);
        } catch (SQLException e) {
            log.error("db error", e);
            throw e;
        } finally {
            close(con, pstmt, null);
        }
    }
}
