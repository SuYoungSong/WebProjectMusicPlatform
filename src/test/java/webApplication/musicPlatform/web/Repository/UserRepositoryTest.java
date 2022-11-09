package webApplication.musicPlatform.web.Repository;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import webApplication.musicPlatform.web.Repository.user.UserRepository;
import webApplication.musicPlatform.web.domain.User;

import java.sql.SQLException;
import java.util.NoSuchElementException;


@Slf4j
class UserRepositoryTest {

    UserRepository ur = new UserRepository();

    @Test
    void crud() throws SQLException{

        // save
        User user = new User("asdf76", "1234", "01000000000", "홍길동", "닌자");
        ur.save(user);

        // findById
        User findUser = ur.findById(user.getId());
        log.info("findMember={}", findUser);
        Assertions.assertThat(user).isEqualTo(findUser);


        // update
        ur.update(user.getId(), "pass", "01012341234", "홍길순", "길순");
        User updateUser = ur.findById(user.getId());
        Assertions.assertThat(updateUser.getNickname()).isEqualTo("길순");

        //delete
        ur.delete(user.getId());
        Assertions.assertThatThrownBy(() -> ur.findById(user.getId()))
                .isInstanceOf(NoSuchElementException.class);
    }

}