package webApplication.musicPlatform.web.Repository;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static webApplication.musicPlatform.web.Repository.ConnectionConst.*;

@Slf4j
public class DBConnectionUtil {
    // 데이터베이스 실제 연결을 진행하는 클래스
    public static Connection getConnection(){
        try{
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            log.info("get connection={}, classs={}", connection, connection.getClass());
            return connection;
        } catch(SQLException e){
            throw new IllegalStateException((e));
        }
    }
}
