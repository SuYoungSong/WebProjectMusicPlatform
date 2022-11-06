package webApplication.musicPlatform.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PageView {
    private String movePage;

    public PageView(String movePage) {
        this.movePage = "/WEB-INF/views/" + movePage + ".jsp";
    }

    public void render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(movePage);
        requestDispatcher.forward(request,response);
    }
}
