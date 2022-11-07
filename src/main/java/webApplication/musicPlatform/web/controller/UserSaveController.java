package webApplication.musicPlatform.web.controller;

import webApplication.musicPlatform.web.Repository.UserRepository;
import webApplication.musicPlatform.web.PageView;
import webApplication.musicPlatform.web.domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class UserSaveController implements  ControllerInter{

    private UserRepository userRepository = new UserRepository();

    @Override
    public PageView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 파라미터 받아오기
        String id = request.getParameter("id");
        String password = request.getParameter("password");

        String phoneFirst = request.getParameter("phoneFirst");
        String phoneSecond = request.getParameter("phoneSecond");
        String phoneThird = request.getParameter("phoneThird");
        String phone = phoneFirst + "-" + phoneSecond + "-" + phoneThird;

        String name = request.getParameter("name");
        String nickname = request.getParameter("nickname");
        String profileImageUrl = request.getParameter("profileImageUrl");

        // 저장할 유저 정보 생성
        User user = new User(id, password, phone, name, nickname, profileImageUrl);

        // 멤버 저장
        try {
            userRepository.save(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // 멤버 정보 모델에 저장
        request.setAttribute("user",user);

        // 회원가입 완료 페이지
        return new PageView("register-result");
    }
}
