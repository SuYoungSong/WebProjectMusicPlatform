package webApplication.musicPlatform.Repository;

import org.springframework.jdbc.core.JdbcTemplate;
import webApplication.musicPlatform.web.domain.User;

import javax.sql.DataSource;

public class UserRepository {

    // 객체가 하나만 생성하기 위해 설정
    private final static UserRepository instance = new UserRepository();
 //   private final JdbcTemplate jdbcTemplate;

   // public UserRepository(DataSource dataSource) {
    //    this.jdbcTemplate = new JdbcTemplate(dataSource);
   // }

    public static UserRepository getInstance(){
        return instance;
    }
    public User save(User user){
        // 유저를 DB에 추가하는 코드 작성
        return user;
    }

    public User findById(String id){
        // id로 유저 정보 찾기
        // sql문으로 가져오기
        User user = null;
        return user;
    }

}
