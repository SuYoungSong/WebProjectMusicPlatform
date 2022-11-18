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

public class userFindProcessController implements ControllerInter {
    @Override
    public PageView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String type = request.getParameter("type");
        UserRepository userRepository = new UserRepository();

        // 공용 파라미터 받기
        String name = request.getParameter("name");
        String phoneFirst = request.getParameter("phoneFirst");
        String phoneSecond = request.getParameter("phoneSecond");
        String phoneThird = request.getParameter("phoneThird");
        String phone = phoneFirst + "-" + phoneSecond + "-" + phoneThird;
        String id = null;

        // 아이디 찾기
        if( type.equals("findId") ){
            try {
                User findUser = userRepository.findByPhoneName(phone, name);
                request.setAttribute("findId",findUser.getId());
                return new PageView("userFind-result");
            } catch (SQLException e) {
                // 아이디 찾기 실패시 되돌려보낸다.
                request.setAttribute("returnName",name);
                request.setAttribute("returnPhoneFirst",phoneFirst);
                request.setAttribute("returnPhoneSecond",phoneSecond);
                request.setAttribute("returnPhoneThird",phoneThird);
                request.setAttribute("FailUserFindMessage","일치하는 회원정보가 없습니다.");
                return new PageView("userFind");
            }
        }

        // 비밀번호 찾기
        if (type.equals("findPassword") ) {
            id = request.getParameter("id");
            try {
                User findUser = userRepository.findByPhoneName(phone, name);
                // 파라미터 정보를 통해 찾은 유저가 Input Id와 같은지 체크
                if (findUser.getId().equals(id)) {
                    request.setAttribute("successMessage","찾은 아이디 정보입니다.");
                    request.getSession().setAttribute("findId",findUser.getId());
                    return new PageView("userFind-changePassword");
                }else{
                    throw new Exception("일치하는 아이디 없음");
                }
            } catch (Exception e) {
                // 비밀번호 찾기 실패시 되돌려 보낸다.
                request.setAttribute("checkedReturn","checked");
                request.setAttribute("returnId",id);
                request.setAttribute("returnName",name);
                request.setAttribute("returnPhoneFirst",phoneFirst);
                request.setAttribute("returnPhoneSecond",phoneSecond);
                request.setAttribute("returnPhoneThird",phoneThird);
                request.setAttribute("FailUserFindMessage","일치하는 회원정보가 없습니다.");
                return new PageView("userFind");
            }
        }


        return new PageView("userFind");
    }
}
