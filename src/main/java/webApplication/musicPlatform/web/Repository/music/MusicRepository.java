package webApplication.musicPlatform.web.Repository.music;

import lombok.extern.slf4j.Slf4j;
import webApplication.musicPlatform.web.Repository.DBConnectionUtil;
import webApplication.musicPlatform.web.Repository.ParentRepository;
import webApplication.musicPlatform.web.domain.Board;
import webApplication.musicPlatform.web.domain.Music;
import webApplication.musicPlatform.web.domain.User;

import java.sql.*;
import java.util.LinkedHashMap;
import java.util.NoSuchElementException;

@Slf4j
public class MusicRepository extends ParentRepository {

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
    public LinkedHashMap<Integer,Music> findByGenreLimit10(int page, String genere) throws SQLException {
        String sql = "select * from music where genere=? order by musicNumber DESC LIMIT ?,10;";

        page = (page-1)*10;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        LinkedHashMap<Integer, Music> musics = new LinkedHashMap<>();
        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,genere);
            pstmt.setInt(2,page);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                Music music = new Music();
                int musicNumber = rs.getInt("musicNumber");
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

                musics.put(musicNumber, music);
            }
            return musics;
        } catch(SQLException e){
            log.error("db error", e);
            throw e;
        } finally{
            {
                close(con, pstmt, rs);
            }
        }
    }
    public LinkedHashMap<Integer,Music> callRecentlyMusicLimit10(int page) throws SQLException {
        String sql = "select * from music order by musicNumber DESC LIMIT ?,10;";

        page = (page-1)*10;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        LinkedHashMap<Integer, Music> musics = new LinkedHashMap<>();
        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1,page);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                Music music = new Music();
                int musicNumber = rs.getInt("musicNumber");
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

                musics.put(musicNumber, music);
            }
            return musics;
        } catch(SQLException e){
            log.error("db error", e);
            throw e;
        } finally{
            {
                close(con, pstmt, rs);
            }
        }
    }

    public LinkedHashMap<Integer,Music> callRecentlyMusicLimit5(int page) throws SQLException {
        String sql = "select * from music order by musicNumber DESC LIMIT ?,5;";

        page = (page-1)*5;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        LinkedHashMap<Integer, Music> musics = new LinkedHashMap<>();
        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1,page);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                Music music = new Music();
                int musicNumber = rs.getInt("musicNumber");
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

                musics.put(musicNumber, music);
            }
            return musics;
        } catch(SQLException e){
            log.error("db error", e);
            throw e;
        } finally{
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

    public LinkedHashMap<Integer,Music> findById(String userId) throws SQLException {
        String sql = "select * from music where uploadUser=?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, userId);

            rs = pstmt.executeQuery();
            LinkedHashMap<Integer, Music> musics = new LinkedHashMap<>();

            while (rs.next()) {
                Music music = new Music();
                int musicNumber = rs.getInt("musicNumber");
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

                musics.put(musicNumber, music);
            }
            return musics;
        } catch(SQLException e){
            log.error("db error", e);
            throw e;
        } finally {
            {
                close(con, pstmt, rs);
            }
        }

        }

    public void delete(int musicNumber) throws SQLException {
        String sql = "delete from music where musicNumber=?";
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, musicNumber);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            log.error("db error", e);
            throw e;
        } finally {
            close(con, pstmt, null);
        }
    }

    public void update(String musicName, String musicText, String genere, String lyrics, String songwriter, String lyricwriter, String musicArranger, String singer, String releaseDate, int musicNumber) throws SQLException {
        String sql = "update music set musicName=?, musicText=?, genere=?, lyrics=?, songwriter=?, lyricwriter=?, musicArranger=?, singer=?, releaseDate=? where musicNumber=?";


        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, musicName);
            pstmt.setString(2, musicText);
            pstmt.setString(3, genere);
            pstmt.setString(4, lyrics);
            pstmt.setString(5, songwriter);
            pstmt.setString(6, lyricwriter);
            pstmt.setString(7, musicArranger);
            pstmt.setString(8, singer);
            pstmt.setString(9, releaseDate);
            pstmt.setInt(10, musicNumber);

            pstmt.execute();

        } catch (SQLException e) {
            log.error("db error", e);
            throw e;
        } finally {
            close(con, pstmt, rs);
        }
    }


    }
