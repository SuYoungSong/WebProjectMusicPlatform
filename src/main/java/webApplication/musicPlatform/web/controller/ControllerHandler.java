package webApplication.musicPlatform.web.controller;

import webApplication.musicPlatform.web.controller.login.board.BoardController;
import webApplication.musicPlatform.web.controller.login.LoginController;
import webApplication.musicPlatform.web.controller.login.LoginProcessController;
import webApplication.musicPlatform.web.controller.login.LogoutController;
import webApplication.musicPlatform.web.controller.login.board.BoardProcessController;
import webApplication.musicPlatform.web.controller.register.RegisterController;
import webApplication.musicPlatform.web.controller.register.UserSaveController;
import webApplication.musicPlatform.web.controller.upload.FileUploadController;
import webApplication.musicPlatform.web.controller.upload.MusicVideoFileUploadController;
import webApplication.musicPlatform.web.controller.userFind.userFindChangePasswordController;
import webApplication.musicPlatform.web.controller.userFind.userFindController;
import webApplication.musicPlatform.web.controller.userFind.userFindProcessController;

import java.util.HashMap;
import java.util.Map;

public class ControllerHandler {

    private Map<String, ControllerInter> controllerMappingMap = new HashMap<>();

    public ControllerHandler() {
        // Controller 주소를 각 Controller 인스턴스에 맵핑
        // 처리 Controller
        controllerMappingMap.put("/front/login", new LoginController());
        controllerMappingMap.put("/front/logout", new LogoutController());
        controllerMappingMap.put("/front/loginProcess", new LoginProcessController());
        controllerMappingMap.put("/front/fileUploadProcess", new FileUploadController());
        controllerMappingMap.put("/front/users/userFindProcess", new userFindProcessController());
        controllerMappingMap.put("/front/users/userFindPChangePassword", new userFindChangePasswordController());
        controllerMappingMap.put("/front/board/write", new BoardProcessController());


        // 단순 이동 Controller
        controllerMappingMap.put("/front/users/register", new RegisterController());
        controllerMappingMap.put("/front/users/userFind", new userFindController());
        controllerMappingMap.put("/front/users/save", new UserSaveController());
        controllerMappingMap.put("/front/musicVideoFileUpload", new MusicVideoFileUploadController());
        controllerMappingMap.put("/front/board", new BoardController());
    }

    public ControllerInter getController(String uri){
        return controllerMappingMap.get(uri);
    }
}
