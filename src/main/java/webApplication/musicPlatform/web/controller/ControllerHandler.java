package webApplication.musicPlatform.web.controller;

import webApplication.musicPlatform.web.controller.board.BoardCommentController;
import webApplication.musicPlatform.web.controller.board.BoardController;
import webApplication.musicPlatform.web.controller.login.LoginController;
import webApplication.musicPlatform.web.controller.login.LoginProcessController;
import webApplication.musicPlatform.web.controller.login.LogoutController;
import webApplication.musicPlatform.web.controller.board.BoardProcessController;
import webApplication.musicPlatform.web.controller.register.userExitController;
import webApplication.musicPlatform.web.controller.music.*;
import webApplication.musicPlatform.web.controller.myPage.myPageController;
import webApplication.musicPlatform.web.controller.register.RegisterController;
import webApplication.musicPlatform.web.controller.register.UserSaveController;
import webApplication.musicPlatform.web.controller.upload.FileUploadController;
import webApplication.musicPlatform.web.controller.upload.MusicVideoFileUploadController;
import webApplication.musicPlatform.web.controller.userFind.userFindChangePasswordController;
import webApplication.musicPlatform.web.controller.userFind.userFindController;
import webApplication.musicPlatform.web.controller.userFind.userFindProcessController;
import webApplication.musicPlatform.web.controller.video.*;

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
        controllerMappingMap.put("/front/users/exit", new userExitController());
        controllerMappingMap.put("/front/users/edit", new userEditController());
        controllerMappingMap.put("/front/users/userFindPChangePassword", new userFindChangePasswordController());
        controllerMappingMap.put("/front/board/write", new BoardProcessController());
        controllerMappingMap.put("/front/board/comment/save", new BoardCommentController());
        controllerMappingMap.put("/front/detailVideo/writeComment", new DetailVideoCommentController());
        controllerMappingMap.put("/front/music/editProcess", new EditMusicProcessController());
        controllerMappingMap.put("/front/video/editProcess", new EditVideoProcessController());
        controllerMappingMap.put("/front/music/delete", new DeleteMusicController());
        controllerMappingMap.put("/front/video/delete", new DeleteVideoController());


        // 단순 이동 Controller
        controllerMappingMap.put("/front/music/edit", new EditMusicController());
        controllerMappingMap.put("/front/video/edit", new EditVideoController());
        controllerMappingMap.put("/front/users/register", new RegisterController());
        controllerMappingMap.put("/front/users/userFind", new userFindController());
        controllerMappingMap.put("/front/users/save", new UserSaveController());
        controllerMappingMap.put("/front/musicVideoFileUpload", new MusicVideoFileUploadController());
        controllerMappingMap.put("/front/board", new BoardController());
        controllerMappingMap.put("/front/music", new MusicController());
        controllerMappingMap.put("/front/video", new VideoController());
        controllerMappingMap.put("/front/detailVideo", new DetailVideoController());
        controllerMappingMap.put("/front/detailMusic", new DetailMusicController());
        controllerMappingMap.put("/front/genereVideo", new genereVideoController());
        controllerMappingMap.put("/front/genereMusic", new genereMusicController());
        controllerMappingMap.put("/front/recentlyVideo", new recentlyVideoController());
        controllerMappingMap.put("/front/recentlyMusic", new recentlyMusicController());
        controllerMappingMap.put("/front/myPage", new myPageController());

        // 임시 css 확인용
        controllerMappingMap.put("/front/temp", new tempController());
    }

    public ControllerInter getController(String uri){
        return controllerMappingMap.get(uri);
    }
}
