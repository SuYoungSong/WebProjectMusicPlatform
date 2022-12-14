package webApplication.musicPlatform.web.controller.login;

import webApplication.musicPlatform.web.PageView;
import webApplication.musicPlatform.web.Repository.user.UserProfileImageRepository;
import webApplication.musicPlatform.web.Repository.user.UserRepository;
import webApplication.musicPlatform.web.controller.ControllerInter;
import webApplication.musicPlatform.web.domain.User;
import webApplication.musicPlatform.web.domain.UserProfileImage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.NoSuchElementException;

public class LoginProcessController implements ControllerInter {

    UserRepository userRepository = new UserRepository();
    UserProfileImageRepository userProfileImageRepository = new UserProfileImageRepository();

    @Override
    public PageView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 로그인 이후 이전페이지로 돌아가기 위한 Referer
        String loginReferer = getLoginReferer(request);

        // 로그인 처리를 위한 입력 데이터 받기
        String id = request.getParameter("id");
        String pass = request.getParameter("pass");

        System.out.println(loginReferer);

        // 로그인 실패시 사용하기 위함
        request.setAttribute("returnId", id);
        request.setAttribute("returnPass", pass);

        User findUser = null;
        UserProfileImage userProfileImage = null;
        try {
            // 아이디를 기반으로 유저를 찾아온다.
            findUser = userRepository.findById(id);
            userProfileImage = userProfileImageRepository.findById(findUser.getId());
            // 로그인에 성공한 경우
            if (findUser.getPassword().equals(pass)) {
                // 세션에 로그인 정보 등록
                request.getSession().setAttribute("loginUser",findUser);
                request.getSession().setAttribute("userProfileImage",userProfileImage);
                // 로그인 이전페이지로 이동시킨다.
                return new PageView(loginReferer);
            } else {
                // 비밀번호가 일치하지 않을경우 강제 오류발생처리
                throw new IOException("로그인 실패");
            }
        } catch (NoSuchElementException | SQLException | IOException e) {
            // NoSuchElementException --> DB에서 아이디를 찾을 수 없을때 발생
            // 로그인 실패시 로그인 페이지로 이동시켜 메세지를 띄운다.
            request.setAttribute("loginFailMessage", "아이디 또는 비밀번호 입력이 잘못되었습니다.");
            return new PageView("login");
        }
    }

    private static String getLoginReferer(HttpServletRequest request) {
        // 특정 페이지 오류 발견으로 Referer적용 대신 index로 보냄처리
//        String loginReferer = request.getParameter("loginReferer");
//
//        if(!loginReferer.equals("")) {
//            String host = request.getHeader("Host");
//            loginReferer = loginReferer.replaceAll(host, "");
//            loginReferer = loginReferer.replaceAll("http://", "");
//            loginReferer = loginReferer.replaceAll("https://", "");
//            if(!loginReferer.equals("/")) {
//                String[] refererSplit = loginReferer.split("/");
//                loginReferer = refererSplit[refererSplit.length - 1];
//            }
//        }else { loginReferer = "index"; }
//
//
//        return loginReferer;
        return "index";
    }
}