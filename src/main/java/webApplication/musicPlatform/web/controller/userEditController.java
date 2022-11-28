package webApplication.musicPlatform.web.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import webApplication.musicPlatform.web.PageView;
import webApplication.musicPlatform.web.Repository.user.UserProfileImageRepository;
import webApplication.musicPlatform.web.Repository.user.UserRepository;
import webApplication.musicPlatform.web.domain.User;
import webApplication.musicPlatform.web.domain.UserProfileImage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class userEditController implements ControllerInter {
    @Override
    public PageView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("userInfoChecked","Checked");

        UserRepository userRepository = new UserRepository();
        // 파일이 저장될 경로
        String saveLocation = request.getSession().getServletContext().getRealPath("resources");
        // 파일의 최대 크기  ( kb * mb * gb )
        int maxSize = 1024 * 1024 * 10;
        // 파라미터 저장할 Map
        Map<String, String> parameter = new HashMap<>();

        File currentDir = new File(saveLocation);
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setRepository(currentDir);
        factory.setSizeThreshold(maxSize);

        ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
        try {
            List<FileItem> items = servletFileUpload.parseRequest(request);
            for(FileItem fi : items) {
                if (fi.isFormField()) {
                    // 파라미터 받아서 Map에 저장하기.
                    parameter.put(fi.getFieldName(), fi.getString("utf-8"));
                } else {
                    // 유저가 업로드한 파일명
                    String userUploadFileName = fi.getName();
                    String ext = userUploadFileName.substring(userUploadFileName.lastIndexOf("."));

                    // 변경할 파일 이름
                    String uuid = UUID.randomUUID().toString();
                    String serverFileName = uuid + ext;

                    // 서버에 파일 저장
                    File upPath = new File(currentDir + "\\images");
                    fi.write(new File(upPath, serverFileName));

                    // DB에 저장용 데이터 생성
                    parameter.put("serverFileName", serverFileName);
                    parameter.put("userUploadFileName", userUploadFileName);
                }
            }
        } catch (Exception e) {
            // 회원 프로필 등록과정 오류시 기본 프로필로 설정
            parameter.put("serverFileName", "defaultProfileImage.png");
            parameter.put("userUploadFileName", "null");
        }

        // 파라미터 받아오기
        User checkUser = (User) request.getSession().getAttribute("loginUser");
        String id = checkUser.getId();
        String password = parameter.get("password");
        String passwordCheck = parameter.get("passwordCheck");
        String phoneFirst = parameter.get("phoneFirst");
        String phoneSecond = parameter.get("phoneSecond");
        String phoneThird = parameter.get("phoneThird");
        String phone = phoneFirst + "-" + phoneSecond + "-" + phoneThird;
        String name = parameter.get("name");
        String nickname = parameter.get("nickname");

        // 비밀번호 체크 검사
        if(!password.equals(passwordCheck)){
            // 오류 메세지 설정
            request.setAttribute("failUserEditMessage", "비밀번호가 일치하지 않습니다. 다시한번 확인해주세요.");
            return new PageView("myPage");
        }
//
//
//        // 만약 중복된 아이디 여부 체크
//        try {
//            userRepository.findById(id);
//            // 오류 메세지 설정
//            request.setAttribute("failUserEditMessage", "이미 사용중인 아이디 입니다. 다른 아이디를 사용해주세요.");
//            return new PageView("myPage");
//        } catch (SQLException | NoSuchElementException e) { }
//

        // 유저 DB수정
        try {
            userRepository.update(id, password, phone, name, nickname);
        } catch (SQLException e) {
            request.setAttribute("failUserEditMessage","문제가 발생하였습니다. <br> 지속적으로 문제가 발생하는 경우 관리자에게 문의하세요.");
            return new PageView("myPage");
        }


        // 유저 프로필 이미지 DB 수정
        UserProfileImageRepository userProfileImageRepository = new UserProfileImageRepository();
        try {
            userProfileImageRepository.update(parameter.get("serverFileName"),parameter.get("userUploadFileName"),id);
        } catch (SQLException e) {
                // 유저 프로필파일 저장 실패시
                // 리턴시키기
                request.setAttribute("failUserEditMessage","문제가 발생하였습니다. <br> 지속적으로 문제가 발생하는 경우 관리자에게 문의하세요.");
            return new PageView("myPage");
        }
        User user = (User) request.getSession().getAttribute("loginUser");
        try {
            request.getSession().setAttribute("loginUser",userRepository.findById(user.getId()));
        } catch (SQLException e) {
            // 유저 아이디 오류
        }

        // 회원가입 완료 페이지
        return new PageView("myPage");
    }
}
