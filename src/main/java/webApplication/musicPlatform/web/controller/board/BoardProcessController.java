package webApplication.musicPlatform.web.controller.board;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import webApplication.musicPlatform.web.PageView;
import webApplication.musicPlatform.web.Repository.board.BoardImageRepository;
import webApplication.musicPlatform.web.Repository.board.BoardRepository;
import webApplication.musicPlatform.web.controller.ControllerInter;
import webApplication.musicPlatform.web.domain.Board;
import webApplication.musicPlatform.web.domain.BoardImage;
import webApplication.musicPlatform.web.domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

@Slf4j
public class BoardProcessController implements ControllerInter {
    @Override
    public PageView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        BoardRepository boardRepository = new BoardRepository();
        // 파일이 저장될 경로
        String saveLocation = request.getSession().getServletContext().getRealPath("resources");
        // 파일의 최대 크기  ( kb * mb * gb )
        int maxSize = 1024 * 1024 * 10000;
        // 파라미터 저장할 Map
        Map<String, String> parameter = new HashMap<>();
        // 다중 이미지 처리를 위한 정보 저장 List
        ArrayList<String> imageArrayList = new ArrayList<>();

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
                    try {
                        // 유저가 업로드한 파일명
                        String userUploadFileName = fi.getName();
                        String ext = userUploadFileName.substring(userUploadFileName.lastIndexOf("."));

                        // 변경할 파일 이름
                        String uuid = UUID.randomUUID().toString();
                        String serverFileName = uuid + ext;

                        // 서버에 파일 저장
                        File upPath = new File(currentDir + "\\images");
                        fi.write(new File(upPath, serverFileName));

                        imageArrayList.add(serverFileName);
                    }catch (Exception e){
                        continue;
                    }
                }
            }
        } catch (Exception e) {
            // servletFileUpload 오류시 pass
        }

        // DB에내용 저장
        int boardNumber = postDBSave(request, parameter);
        try {
            if (imageArrayList.size()>0) {
                imageDBSave(imageArrayList, boardNumber);
            }
        } catch (SQLException e) {
            log.error("board Image Save fail boardNumber = " + boardNumber);
        }

        return new PageView("board");
    }

    private static void imageDBSave(ArrayList<String> imageArrayList, int boardNumber) throws SQLException {
        for( String serverFileName : imageArrayList) {
            // DB에 저장
            BoardImage boardImage = new BoardImage();
            boardImage.setBoardNumber(boardNumber);
            boardImage.setServerFilePath(serverFileName);

            BoardImageRepository boardImageRepository = new BoardImageRepository();
            boardImageRepository.save(boardImage);
        }
    }


    public int postDBSave(HttpServletRequest request, Map<String, String> parameter){
        User user = (User) request.getSession().getAttribute("loginUser");
        String title = parameter.get("title");
        String content = parameter.get("content");
        content = content.replace("\r\n", "<br/>");
        String category = "free";
        int boardNumber;
                Board board = new Board(
                user.getId(),
                title,
                content,
                category
        );
        try {
            BoardRepository boardRepository = new BoardRepository();
            boardNumber = boardRepository.write(board);
        } catch (SQLException e) {
            // 글쓰기 실패
            boardNumber = -1;
            log.error("post save fail user = "+user.getId());
        }
        return boardNumber;
    }
}

