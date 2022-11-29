package webApplication.musicPlatform.web.controller.video;

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
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedHashMap;

public class DeleteVideoController implements ControllerInter {
    @Override
    public PageView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("myVideoChecked","Checked");
        VideoRepository videoRepository = new VideoRepository();

        int videoNumber = Integer.parseInt(request.getParameter("videoNumber"));

        try {
            videoRepository.delete(videoNumber);
        } catch (SQLException e) {
            // 비디오 삭제 실패
            e.printStackTrace();
        }


        User user = (User) request.getSession().getAttribute("loginUser");

        if (!(user == null)){
            MusicRepository musicRepository = new MusicRepository();

            LinkedHashMap<Integer, Music> musics = null;
            LinkedHashMap<Integer, Video> videos = null;

            LinkedHashMap<Integer, MusicImage> musicImages = new LinkedHashMap<>();
            LinkedHashMap<Integer, VideoImage> videoImages = new LinkedHashMap<>();
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
