package webApplication.musicPlatform.web.Repository.video;

import lombok.extern.slf4j.Slf4j;
import webApplication.musicPlatform.web.Repository.DBConnectionUtil;
import webApplication.musicPlatform.web.Repository.ParentRepository;
import webApplication.musicPlatform.web.domain.Music;
import webApplication.musicPlatform.web.domain.Video;

import java.sql.*;
import java.util.LinkedHashMap;
import java.util.NoSuchElementException;

@Slf4j
public class VideoRepository extends ParentRepository {

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
    public LinkedHashMap<Integer,Video> findByGenereLimited10(int page,String genere) throws SQLException {
        String sql = "select * from video where genere=? order by videoNumber DESC LIMIT ?,10;";

        page = (page-1)*10;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        LinkedHashMap<Integer, Video> videos = new LinkedHashMap<>();
        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,genere);
            pstmt.setInt(2,page);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                Video video = new Video();
                int musicNumber = rs.getInt("videoNumber");
                video.setVideoName(rs.getString("videoName"));
                video.setUploadUserId(rs.getString("uploadUser"));
                video.setVideoDescription(rs.getString("videoText"));
                video.setVideoGenre(rs.getString("genere"));

                videos.put(musicNumber, video);
            }
            return videos;
        } catch(SQLException e){
            log.error("db error", e);
            throw e;
        } finally{
            {
                close(con, pstmt, rs);
            }
        }
    }
    public LinkedHashMap<Integer,Video> callRecentlyVideoLimit10(int page) throws SQLException {
        String sql = "select * from video order by videoNumber DESC LIMIT ?,10;";

        page = (page-1)*10;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        LinkedHashMap<Integer, Video> videos = new LinkedHashMap<>();
        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1,page);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                Video video = new Video();
                int videoNumber = rs.getInt("videoNumber");
                video.setVideoName(rs.getString("videoName"));
                video.setUploadUserId(rs.getString("uploadUser"));
                video.setVideoDescription(rs.getString("videoText"));
                video.setVideoGenre(rs.getString("genere"));

                videos.put(videoNumber, video);
            }
            return videos;
        } catch(SQLException e){
            log.error("db error", e);
            throw e;
        } finally{
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

    public LinkedHashMap<Integer,Video> findById(String userId) throws SQLException {
        String sql = "select * from video where uploadUser=?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, userId);

            rs = pstmt.executeQuery();
            LinkedHashMap<Integer, Video> videos = new LinkedHashMap<>();

            while (rs.next()) {
                Video video = new Video();
                int videoNumber = rs.getInt("videoNumber");
                video.setVideoName(rs.getString("videoName"));
                video.setUploadUserId(rs.getString("uploadUser"));
                video.setVideoDescription(rs.getString("videoText"));
                video.setVideoGenre(rs.getString("genere"));

                videos.put(videoNumber, video);
            }
            return videos;
        } catch(SQLException e){
            log.error("db error", e);
            throw e;
        } finally{
            {
                close(con, pstmt, rs);
            }
        }
    }
    public void delete(int videoNumber) throws SQLException {
        String sql = "delete from video where videoNumber=?";
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, videoNumber);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            log.error("db error", e);
            throw e;
        } finally {
            close(con, pstmt, null);
        }
    }



    public void update(String videoName, String videoText, String genere, int videoNumber) throws SQLException {
        String sql = "update video set videoName=?, videoText=?, genere=? where videoNumber=?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, videoName);
            pstmt.setString(2, videoText);
            pstmt.setString(3, genere);
            pstmt.setInt(4, videoNumber);

            pstmt.execute();

        } catch (SQLException e) {
            log.error("db error", e);
            throw e;
        } finally {
            close(con, pstmt, null);
        }
    }
}
