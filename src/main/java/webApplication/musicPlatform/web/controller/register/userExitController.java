package webApplication.musicPlatform.web.controller.register;

import webApplication.musicPlatform.web.PageView;
import webApplication.musicPlatform.web.Repository.user.UserRepository;
import webApplication.musicPlatform.web.controller.ControllerInter;
import webApplication.musicPlatform.web.domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class userExitController implements ControllerInter {
    @Override
    public PageView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        User user = (User)request.getSession().getAttribute("loginUser");

        if (!(user == null)){

            UserRepository userRepository = new UserRepository();
            try {
                userRepository.delete(user.getId());
            } catch (SQLException e) {
                // 회원탈퇴 SQL문 오류시
            }


        }

        request.getSession().removeAttribute("loginUser");

        return new PageView("/");
    }
}
