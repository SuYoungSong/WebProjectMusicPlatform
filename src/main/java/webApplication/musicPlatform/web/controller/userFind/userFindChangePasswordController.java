package webApplication.musicPlatform.web.controller.userFind;

import webApplication.musicPlatform.web.PageView;
import webApplication.musicPlatform.web.Repository.user.UserRepository;
import webApplication.musicPlatform.web.controller.ControllerInter;
import webApplication.musicPlatform.web.domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class userFindChangePasswordController implements ControllerInter {
    @Override
    public PageView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = (String)request.getSession().getAttribute("findId");
        String password = request.getParameter("password");
        String passwordCheck = request.getParameter("passwordCheck");
        UserRepository userRepository = new UserRepository();

        if (!password.equals(passwordCheck)) {
            request.setAttribute("findId",id);
            request.setAttribute("failChangePassword","입력된 비밀번호가 일치하지 않습니다.");
            request.setAttribute("returnPassword", password);
            request.setAttribute("returnPasswordCheck", passwordCheck);
            return new PageView("userFind-changePassword");
        }

        try {
            User user = userRepository.findById(id);
            userRepository.update(
                    user.getId(),
                    password,
                    user.getPhone(),
                    user.getName(),
                    user.getNickname()
            );

        } catch (SQLException e) {
            request.setAttribute("failChangePassword","비밀번호 변경에 실패하였습니다. 관리자에게 문의하세요.");
            request.setAttribute("findId",id);
            request.setAttribute("returnPassword", password);
            request.setAttribute("returnPasswordCheck", passwordCheck);
            return new PageView("userFind-changePassword");
        }


        request.setAttribute("successMessage","비밀번호 변경 성공");
        return new PageView("userFind-result");
    }
}
