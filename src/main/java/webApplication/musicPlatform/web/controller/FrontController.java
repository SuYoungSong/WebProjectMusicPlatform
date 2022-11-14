package webApplication.musicPlatform.web.controller;

import webApplication.musicPlatform.web.PageView;
import webApplication.musicPlatform.web.controller.login.LoginController;
import webApplication.musicPlatform.web.controller.login.LoginProcessController;
import webApplication.musicPlatform.web.controller.login.LogoutController;
import webApplication.musicPlatform.web.controller.register.RegisterController;
import webApplication.musicPlatform.web.controller.register.UserSaveController;
import webApplication.musicPlatform.web.controller.upload.FileUploadController;
import webApplication.musicPlatform.web.controller.upload.MusicVideoFileUploadController;
import webApplication.musicPlatform.web.controller.userFind.userFindChangePasswordController;
import webApplication.musicPlatform.web.controller.userFind.userFindController;
import webApplication.musicPlatform.web.controller.userFind.userFindProcessController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name="frontController", urlPatterns = "/front/*")
public class FrontController extends HttpServlet {
    private Map<String, ControllerInter> controllerMappingMap = new HashMap<>();

    public FrontController() {
        // Controller 주소를 각 Controller 인스턴스에 맵핑
        // 처리 Controller
        controllerMappingMap.put("/front/login", new LoginController());
        controllerMappingMap.put("/front/logout", new LogoutController());
        controllerMappingMap.put("/front/loginProcess", new LoginProcessController());
        controllerMappingMap.put("/front/fileUploadProcess", new FileUploadController());
        controllerMappingMap.put("/front/users/userFindProcess", new userFindProcessController());
        controllerMappingMap.put("/front/users/userFindPChangePassword", new userFindChangePasswordController());


        // 단순 이동 Controller
        controllerMappingMap.put("/front/users/register", new RegisterController());
        controllerMappingMap.put("/front/users/userFind", new userFindController());
        controllerMappingMap.put("/front/users/save", new UserSaveController());
        controllerMappingMap.put("/front/musicVideoFileUpload", new MusicVideoFileUploadController());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 사용자가 들어온 URI를 기반으로 요청한 Controller를 찾는다.
        String requestURI = request.getRequestURI();
        System.out.println("requestURI = " + requestURI);
        ControllerInter controller = controllerMappingMap.get(requestURI);

        // 만약 사용자가 요청한 Controller을 찾을 수 없다면 NOT FOUND 응답
        if (controller == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // PageView를 통해서 jsp 파일에 접근하여 forward
        PageView pv = controller.process(request, response);
        pv.render(request, response);

    }
}
