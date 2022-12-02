package webApplication.musicPlatform.web.controller.serach;

import webApplication.musicPlatform.web.PageView;
import webApplication.musicPlatform.web.Repository.music.MusicImageFileRepository;
import webApplication.musicPlatform.web.Repository.music.MusicRepository;
import webApplication.musicPlatform.web.Repository.video.VideoImageFileRepository;
import webApplication.musicPlatform.web.Repository.video.VideoRepository;
import webApplication.musicPlatform.web.controller.ControllerInter;
import webApplication.musicPlatform.web.domain.Music;
import webApplication.musicPlatform.web.domain.MusicImage;
import webApplication.musicPlatform.web.domain.Video;
import webApplication.musicPlatform.web.domain.VideoImage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedHashMap;

public class SearchController implements ControllerInter {
    @Override
    public PageView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        MusicRepository musicRepository = new MusicRepository();
        VideoRepository videoRepository = new VideoRepository();

        LinkedHashMap<Integer, Music> musics;
        LinkedHashMap<Integer, Video> videos;
        String inputText = null;
        String searchType = null;
        try {
            inputText = request.getParameter("inputText");
            searchType = request.getParameter("searchType");
        } catch (Exception e) {
            request.setAttribute("searchErrorMessage", "잘못된 검색 요청 입니다.");
            return new PageView("search");
        }

        try {
            switch (searchType) {
                case "제목":
                    musics = musicRepository.findByTitle(inputText);
                    videos = videoRepository.findByTitle(inputText);
                    break;

                case "가수":
                    musics = musicRepository.findBySinger(inputText);
                    videos = videoRepository.findByUploader(inputText);
                    break;

                case "업로더":
                    musics = musicRepository.findByUpload(inputText);
                    videos = videoRepository.findByUploader(inputText);
                    break;

                default:
                    request.setAttribute("searchErrorMessage", "잘못된 검색 요청 입니다.");
                    return new PageView("search");
            }
        }catch(Exception e){
            request.setAttribute("searchErrorMessage", "예상치 못한 오류가 발생하였습니다.");
            return new PageView("search");
        }


        MusicImageFileRepository musicImageFileRepository = new MusicImageFileRepository();
        VideoImageFileRepository videoImageFileRepository = new VideoImageFileRepository();

        LinkedHashMap<Integer, MusicImage> musicImages = new LinkedHashMap<>();
        LinkedHashMap<Integer, VideoImage> videoImages = new LinkedHashMap<>();

        musics.forEach((key, value) -> {
            try {
                MusicImage musicImage = musicImageFileRepository.findByNumber(key);
                musicImages.put(key, musicImage);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        videos.forEach((key, value) -> {
            try {
                VideoImage videoImage = videoImageFileRepository.findByNumber(key);
                videoImages.put(key, videoImage);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        request.setAttribute("inputText", inputText);
        request.setAttribute("searchType", searchType);
        request.setAttribute("searchMusics", musics);
        request.setAttribute("searchVideos",videos);
        request.setAttribute("searchMusicImages",musicImages);
        request.setAttribute("searchVideoImages",videoImages);

        return new PageView("search");
    }
}
