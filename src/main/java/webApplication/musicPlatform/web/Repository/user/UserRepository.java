package webApplication.musicPlatform.web.Repository.user;

import lombok.extern.slf4j.Slf4j;
import webApplication.musicPlatform.web.Repository.DBConnectionUtil;
import webApplication.musicPlatform.web.Repository.ParentRepository;
import webApplication.musicPlatform.web.domain.User;

import java.sql.*;
import java.util.NoSuchElementException;

@Slf4j
public class UserRepository extends ParentRepository {

    public User save(User user) throws SQLException {
        String sql = "insert into user(id, password, phone, name, nickname) values(?,?,?,?,?)";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, user.getId());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getPhone());
            pstmt.setString(4, user.getName());
            pstmt.setString(5, user.getNickname());
            pstmt.executeUpdate();
            return user;
        } catch (SQLException e) {
            log.error("db error", e);
            throw e;
        } finally {
            {
                close(con, pstmt, null);
            }
        }
    }

    public User findById(String id) throws SQLException {
        String sql = "select * from user where id=?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, id);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setId(rs.getString("id"));
                user.setPassword(rs.getString("password"));
                user.setPhone(rs.getString("phone"));
                user.setName(rs.getString("name"));
                user.setNickname(rs.getString("nickname"));

                return user;
            } else {
                throw new NoSuchElementException("user not found userId="+id);
            }
        } catch (SQLException e) {
            log.error("db error", e);
            throw e;
        } finally {
            close(con, pstmt, rs);
        }
    }

    public User findByPhoneName(String phone, String name) throws SQLException {
        String sql = "select * from user where phone=? and name=? ";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, phone);
            pstmt.setString(2, name);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setId(rs.getString("id"));
                user.setPassword(rs.getString("password"));
                user.setPhone(rs.getString("phone"));
                user.setName(rs.getString("name"));
                user.setNickname(rs.getString("nickname"));

                return user;
            } else {
                throw new NoSuchElementException("userId not found userPhone="+ phone + "userName" + name);
            }
        } catch (SQLException e) {
            log.error("db error", e);
            throw e;
        } finally {
            close(con, pstmt, rs);
        }
    }

    public void update(String id, String password, String phone, String name, String nickname) throws SQLException {
        String sql = "update user set password=?, name=?, phone=?, nickname=? where id=?";

        Connection con = null;
        PreparedStatement pstmt = null;

        try{
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,password);
            pstmt.setString(2, name);
            pstmt.setString(3, phone);
            pstmt.setString(4, nickname);
            pstmt.setString(5, id);
            int resultSize = pstmt.executeUpdate();
            log.info("resultSize={}", resultSize);
        } catch (SQLException e) {
            log.error("db error", e);
            throw e;
        } finally {
            close(con, pstmt, null);
        }
    }

    public void delete(String id) throws SQLException {
        String sql = "delete from user where id=?";
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            log.error("db error", e);
            throw e;
        } finally {
            close(con, pstmt, null);
        }
    }

}
