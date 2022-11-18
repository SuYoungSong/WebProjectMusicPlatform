package webApplication.musicPlatform.web.Repository.music;

import lombok.extern.slf4j.Slf4j;
import webApplication.musicPlatform.web.Repository.DBConnectionUtil;
import webApplication.musicPlatform.web.Repository.ParentRepository;
import webApplication.musicPlatform.web.domain.MusicFile;
import webApplication.musicPlatform.web.domain.VideoFile;

import java.sql.*;
import java.util.NoSuchElementException;

@Slf4j
public class MusicFileRepository extends ParentRepository {


    public int upload(MusicFile musicFile) throws SQLException{
        String sql = "insert into musicfile(musicNumber, serverFilePath, userUploadFileName) values(?,?,?)";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, musicFile.getMusicNumber());
            pstmt.setString(2, musicFile.getServerFileName());
            pstmt.setString(3, musicFile.getUserUploadFileName());

            pstmt.executeUpdate();

            rs = pstmt.getGeneratedKeys();                // 쿼리 실행 후 생성된 키 값 반환
            int musicFileNumber = (rs.next())?rs.getInt(1):null;

            return musicFileNumber;
        } catch (SQLException e) {
            log.error("db error", e);
            throw e;
        } finally {
            {
                close(con, pstmt, rs);
            }
        }
    }

    public MusicFile findByNumber(int musicNumber) throws SQLException {
        String sql = "select * from musicfile where musicNumber=?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, musicNumber);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                MusicFile musicFile = new MusicFile();
                musicFile.setMusicNumber(rs.getInt("musicNumber"));
                musicFile.setServerFileName(rs.getString("serverFilePath"));
                musicFile.setUserUploadFileName(rs.getString("userUploadFileName"));

                return musicFile;
            } else {
                throw new NoSuchElementException("musicFile not found userId="+ musicNumber);
            }
        } catch (SQLException e) {
            log.error("db error", e);
            throw e;
        } finally {
            close(con, pstmt, rs);
        }
    }
}
