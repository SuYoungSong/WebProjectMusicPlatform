package webApplication.musicPlatform.web.Repository.music;

import lombok.extern.slf4j.Slf4j;
import webApplication.musicPlatform.web.Repository.DBConnectionUtil;
import webApplication.musicPlatform.web.Repository.ParentRepository;
import webApplication.musicPlatform.web.domain.MusicImage;
import webApplication.musicPlatform.web.domain.VideoImage;

import java.sql.*;
import java.util.NoSuchElementException;

@Slf4j
public class MusicImageFileRepository extends ParentRepository {
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

    public MusicImage findByNumber(int musicNumber) throws SQLException {
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
                MusicImage musicImage = new MusicImage();
                musicImage.setMusicNumber(rs.getInt("musicNumber"));
                musicImage.setServerFilePath(rs.getString("serverFilePath"));

                return musicImage;
            } else {
                // 음악 이미지 없음
//                throw new NoSuchElementException("musicImageFile not found userId="+ musicNumber);
                return null;
            }
        } catch (SQLException e) {
            log.error("db error", e);
            throw e;
        } finally {
            close(con, pstmt, rs);
        }
    }


    public void update(int musicNumber, String serverFilePath) throws SQLException {
        String sql = "update musicImage set serverFilePath=? where musicNumber=?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, serverFilePath);
            pstmt.setInt(2, musicNumber);
        } catch (SQLException e) {
            log.error("db error", e);
            throw e;
        } finally {
            close(con, pstmt, null);
        }
    }
}
