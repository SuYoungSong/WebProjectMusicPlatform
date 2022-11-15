package webApplication.musicPlatform.web.Repository.user;

import lombok.extern.slf4j.Slf4j;
import webApplication.musicPlatform.web.Repository.DBConnectionUtil;
import webApplication.musicPlatform.web.domain.MusicFile;
import webApplication.musicPlatform.web.domain.UserProfileImage;

import java.sql.*;
import java.util.NoSuchElementException;

@Slf4j
public class UserProfileImageRepository {



    public UserProfileImage upload(UserProfileImage userProfileImage) throws SQLException{
        String sql = "insert into userprofileimage(userId, serverFilePath, userUploadFileName) values(?,?,?)";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, userProfileImage.getUserId());
            pstmt.setString(2, userProfileImage.getServerFilePath());
            pstmt.setString(3, userProfileImage.getUserUploadFileName());

            pstmt.executeUpdate();
            return userProfileImage;
        } catch (SQLException e) {
            log.error("db error", e);
            throw e;
        } finally {
            {
                close(con, pstmt, rs);
            }
        }
    }

    public UserProfileImage findById(String userId) throws SQLException {
        String sql = "select * from userprofileimage where userId=?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, userId);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                UserProfileImage userProfileImage = new UserProfileImage();
                userProfileImage.setUserId(rs.getString("userId"));
                userProfileImage.setServerFilePath(rs.getString("serverFilePath"));
                userProfileImage.setUserUploadFileName(rs.getString("userUploadFileName"));

                return userProfileImage;
            } else {
                throw new NoSuchElementException("UserProfileImage not found userId="+ userId);
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
