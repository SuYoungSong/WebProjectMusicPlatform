package webApplication.musicPlatform.web.Repository.music;

import lombok.extern.slf4j.Slf4j;
import webApplication.musicPlatform.web.Repository.DBConnectionUtil;
import webApplication.musicPlatform.web.domain.Music;
import webApplication.musicPlatform.web.domain.User;

import java.sql.*;
import java.util.NoSuchElementException;

@Slf4j
public class MusicRepository {

    public int upload(Music music) throws SQLException{
        String sql = "insert into music(musicName, uploadUser, musicText, genere, lyrics, songwriter, lyricwriter, musicArranger, singer, releaseDate) values(?,?,?,?,?,?,?,?,?,?)";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, music.getMusicName());
            pstmt.setString(2, music.getUploadUser());
            pstmt.setString(3, music.getMusicDescription());
            pstmt.setString(4, music.getGenre());
            pstmt.setString(5, music.getLyrics());
            pstmt.setString(6, music.getSongwriter());
            pstmt.setString(7, music.getLyricwriter());
            pstmt.setString(8, music.getMusicArranger());
            pstmt.setString(9, music.getSinger());
            pstmt.setString(10, music.getReleaseDate());

            pstmt.executeUpdate();

            rs = pstmt.getGeneratedKeys();                // 쿼리 실행 후 생성된 키 값 반환
            int musicNumber = (rs.next())?rs.getInt(1):null;

            return musicNumber;
        } catch (SQLException e) {
            log.error("db error", e);
            throw e;
        } finally {
            {
                close(con, pstmt, rs);
            }
        }
    }

    public Music findByNumber(int musicNumber) throws SQLException {
        String sql = "select * from music where musicNumber=?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, musicNumber);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                Music music = new Music();
                music.setMusicName(rs.getString("musicName"));
                music.setUploadUser(rs.getString("uploadUser"));
                music.setMusicDescription(rs.getString("musicText"));
                music.setGenre(rs.getString("genere"));
                music.setLyrics(rs.getString("lyrics"));
                music.setSongwriter(rs.getString("songwriter"));
                music.setLyricwriter(rs.getString("lyricwriter"));
                music.setMusicArranger(rs.getString("musicArranger"));
                music.setSinger(rs.getString("singer"));
                music.setReleaseDate(rs.getString("releaseDate"));

                return music;
            } else {
                throw new NoSuchElementException("music not found musicNumber="+musicNumber);
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
