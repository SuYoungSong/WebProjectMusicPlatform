package webApplication.musicPlatform.web.Repository;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Slf4j
public class ParentRepository {
    protected Connection getConnection() {
        return DBConnectionUtil.getConnection();
    }

    protected void close(Connection con, Statement stmt, ResultSet rs){
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
