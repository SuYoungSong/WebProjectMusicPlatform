package webApplication.musicPlatform.web.controller.upload;


import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import webApplication.musicPlatform.web.PageView;
import webApplication.musicPlatform.web.Repository.music.MusicFileRepository;
import webApplication.musicPlatform.web.Repository.music.MusicImageFileRepository;
import webApplication.musicPlatform.web.Repository.music.MusicRepository;
import webApplication.musicPlatform.web.Repository.video.VideoFileRepository;
import webApplication.musicPlatform.web.Repository.video.VideoImageFileRepository;
import webApplication.musicPlatform.web.Repository.video.VideoRepository;
import webApplication.musicPlatform.web.controller.ControllerInter;
import webApplication.musicPlatform.web.domain.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class FileUploadController implements ControllerInter {
    @Override
    public PageView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        // 파일이 저장될 경로
        String saveLocation = request.getSession().getServletContext().getRealPath("resources");
        // 파일의 최대 크기  ( kb * mb * gb )
        int maxSize = 1024 * 1024 * 10000;
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
                } else if(fi.getFieldName().equals("file")){
                    // 유저가 업로드한 파일명
                    String userUploadFileName = fi.getName();
                    String ext = userUploadFileName.substring(userUploadFileName.lastIndexOf("."));

                    // 변경할 파일 이름
                    String uuid = UUID.randomUUID().toString();
                    String serverFileName = uuid + ext;

                    // 서버에 파일 저장
                    File upPath = new File(currentDir + "\\" + parameter.get("fileType"));
                    fi.write(new File(upPath, serverFileName));

                    parameter.put("serverFileName", serverFileName);
                    parameter.put("userUploadFileName", userUploadFileName);
                } else if(fi.getFieldName().equals("imageFile")) {
                    try {
                        // 유저가 업로드한 파일명
                        String userUploadFileName = fi.getName();
                        String ext = userUploadFileName.substring(userUploadFileName.lastIndexOf("."));

                        // 변경할 파일 이름
                        String uuid = UUID.randomUUID().toString();
                        String serverFileName = uuid + ext;

                        // 서버에 파일 저장
                        File upPath = new File(currentDir + "\\" + "images");
                        fi.write(new File(upPath, serverFileName));

                        parameter.put("serverImageFileName", serverFileName);
                    } catch (Exception e) {
                        // 이미지 파일이 업로드 되지 않을경우 기본 이미지로 설정
                        parameter.put("serverImageFileName", "default");
                    }
                }
            }
            // DB에 저장
            User user = (User) request.getSession().getAttribute("loginUser");
            dataBaseSave(user,parameter);
            // 결과 페이지로 보낼 파일명
            request.setAttribute("uploadFileInfo", parameter);
        } catch (Exception e) {
            // 결과 페이지에 전달할 오류 내용
            request.setAttribute("uploadError", "파일 등록 과정에서 오류가 발생하였습니다. <br>\n" +
                                                    "    다시 시도해주세요<br>\n" +
                                                    "    지속적으로 오류가 발생할 경우 관리자에게 문의 바랍니다.");
        }

        return new PageView("fileUpload-result");
    }

    private void dataBaseSave(User user, Map<String,String> parameter) throws SQLException {
        String userId = user.getId();
        String fileType = parameter.get("fileType");
        String serverImageFileName = null;
        switch (fileType){
            case "videos":
                // 영상 정보를 DB에 등록
                Video video = new Video(
                        userId,
                        parameter.get("videoName"),
                        parameter.get("videoDescription"),
                        parameter.get("genere")
                );

                VideoRepository videoRepository = new VideoRepository();
                int videoNumber = videoRepository.upload(video);

                // 영상 파일 정보를 DB에 등록
                VideoFile videoFile = new VideoFile(
                        videoNumber,
                        parameter.get("serverFileName"),
                        parameter.get("userUploadFileName")
                );


                VideoFileRepository videoFileRepository = new VideoFileRepository();
                videoFileRepository.upload(videoFile);

                // 영상 이미지 정보를 DB에 등록
                serverImageFileName = (parameter.get("serverImageFileName")).equals("default")?"defaultVideoImage.png":"parameter.get(\"serverImageFileName\")";
                VideoImage videoImage = new VideoImage(
                        videoNumber,
                        serverImageFileName
                );
                VideoImageFileRepository videoImageFileRepository = new VideoImageFileRepository();
                videoImageFileRepository.upload(videoImage);
                break;
            case "musics":
                // 음악 정보를 DB에 등록
                Music music = new Music(
                        parameter.get("musicName"),
                        userId,
                        parameter.get("musicDescription"),
                        parameter.get("genre"),
                        parameter.get("lyrics"),
                        parameter.get("songwriter"),
                        parameter.get("lyricwriter"),
                        parameter.get("musicArranger"),
                        parameter.get("singer"),
                        parameter.get("releaseDate")
                );

                MusicRepository musicRepository = new MusicRepository();
                int musicNumber = musicRepository.upload(music);

                // 음악 파일 정보를 DB에 등록
                MusicFile musicFile = new MusicFile(
                        musicNumber,
                        parameter.get("serverFileName"),
                        parameter.get("userUploadFileName")
                );

                MusicFileRepository musicFileRepository = new MusicFileRepository();
                musicFileRepository.upload(musicFile);

                // 음악 이미지 정보를 DB에 등록
                serverImageFileName = (parameter.get("serverImageFileName")).equals("default")?"defaultMusicImage.png":"parameter.get(\"serverImageFileName\")";
                MusicImage musicImage = new MusicImage(
                        musicNumber,
                        serverImageFileName
                );
                MusicImageFileRepository musicImageFileRepository = new MusicImageFileRepository();
                musicImageFileRepository.upload(musicImage);
                break;
        }
    }


}
