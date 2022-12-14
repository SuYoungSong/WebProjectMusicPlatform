package webApplication.musicPlatform.web.controller.music;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import webApplication.musicPlatform.web.PageView;
import webApplication.musicPlatform.web.Repository.music.MusicImageFileRepository;
import webApplication.musicPlatform.web.Repository.music.MusicRepository;
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
import java.util.*;

public class EditMusicProcessController implements ControllerInter {
    @Override
    public PageView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("myMusicChecked","Checked");

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
            dataBaseSave(parameter);
            // 결과 페이지로 보낼 파일명
            request.setAttribute("uploadFileInfo", parameter);
        } catch (Exception e) {
            e.printStackTrace();
            // 결과 페이지에 전달할 오류 내용
//            request.setAttribute("uploadError", "파일 등록 과정에서 오류가 발생하였습니다. <br>\n" +
//                    "    다시 시도해주세요<br>\n" +
//                    "    지속적으로 오류가 발생할 경우 관리자에게 문의 바랍니다.");
        }


        User user = (User) request.getSession().getAttribute("loginUser");

        if (!(user == null)){
            MusicRepository musicRepository = new MusicRepository();
            VideoRepository videoRepository = new VideoRepository();

            LinkedHashMap<Integer, Music> musics;
            LinkedHashMap<Integer, Video> videos;

            LinkedHashMap<Integer, MusicImage> musicImages;
            LinkedHashMap<Integer, VideoImage> videoImages;

            try {
                musics = musicRepository.findById(user.getId());
                musicImages = getMusicImages(musics);

                videos = videoRepository.findById(user.getId());
                videoImages = getVideoImages(videos);

                request.setAttribute("musicMap", musics);
                request.setAttribute("musicImageMap", musicImages);

                request.setAttribute("videoMap", videos);
                request.setAttribute("videoImageMap", videoImages);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }

        return new PageView("myPage");
    }

    private void dataBaseSave(Map<String,String> parameter) throws SQLException {
        String serverImageFileName = null;
        int musicNumber = Integer.parseInt(parameter.get("musicNumber"));

        // 음악 정보를 DB에 등록
        MusicRepository musicRepository = new MusicRepository();
        musicRepository.update(
                parameter.get("musicName"),
                parameter.get("musicDescription"),
                parameter.get("genre"),
                parameter.get("lyrics"),
                parameter.get("songwriter"),
                parameter.get("lyricwriter"),
                parameter.get("musicArranger"),
                parameter.get("singer"),
                parameter.get("releaseDate"),
                musicNumber
        );
        // 음악 이미지 정보를 DB에 등록
        MusicImageFileRepository musicImageFileRepository = new MusicImageFileRepository();
        serverImageFileName = (parameter.get("serverImageFileName")).equals("default") ? musicImageFileRepository.findByNumber(musicNumber).getServerFilePath() : parameter.get("serverImageFileName");
        musicImageFileRepository.update(musicNumber, serverImageFileName);

    }
    private static LinkedHashMap<Integer, MusicImage> getMusicImages(LinkedHashMap<Integer, Music> musics) {
        LinkedHashMap<Integer, MusicImage> musicImages = new LinkedHashMap<>();
        MusicImageFileRepository musicImageFileRepository = new MusicImageFileRepository();

        musics.forEach((key, value) ->{
            try {
                MusicImage musicImage = musicImageFileRepository.findByNumber(key);
                musicImages.put(key, musicImage);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        return musicImages;
    }

    private static LinkedHashMap<Integer, VideoImage> getVideoImages(LinkedHashMap<Integer, Video> musics) {
        LinkedHashMap<Integer, VideoImage> videoImages = new LinkedHashMap<>();
        VideoImageFileRepository videoImageFileRepository = new VideoImageFileRepository();

        musics.forEach((key, value) ->{
            try {
                VideoImage videoImage = videoImageFileRepository.findByNumber(key);
                videoImages.put(key, videoImage);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        return videoImages;
    }
}
