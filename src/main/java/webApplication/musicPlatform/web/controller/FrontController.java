package webApplication.musicPlatform.web.controller;

import webApplication.musicPlatform.web.PageView;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="frontController", urlPatterns = "/front/*")
public class FrontController extends HttpServlet {
    private ControllerHandler controllerHandler = null;

    public FrontController() {
        controllerHandler = new ControllerHandler();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 사용자가 들어온 URI를 기반으로 요청한 Controller를 찾는다.
        String requestURI = request.getRequestURI();
        System.out.println("requestURI = " + requestURI);
        ControllerInter controller = controllerHandler.getController(requestURI);

        // 만약 사용자가 요청한 Controller을 찾을 수 없다면 error Page 반환
        if (controller == null){
            response.sendRedirect("/front/error");
//            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // PageView를 통해서 jsp 파일에 접근하여 forward
        PageView pv = controller.process(request, response);
        pv.render(request, response);

    }
}
