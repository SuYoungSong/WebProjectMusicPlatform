package webApplication.musicPlatform.web.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import webApplication.musicPlatform.web.Repository.music.MusicFileRepository;
import webApplication.musicPlatform.web.Repository.music.MusicImageFileRepository;
import webApplication.musicPlatform.web.Repository.music.MusicRepository;
import webApplication.musicPlatform.web.Repository.video.VideoFileRepository;
import webApplication.musicPlatform.web.Repository.video.VideoImageFileRepository;
import webApplication.musicPlatform.web.Repository.video.VideoRepository;
import webApplication.musicPlatform.web.domain.*;

import java.sql.SQLException;
import java.util.LinkedHashMap;

@RestController
public class VideoPagging {
    VideoRepository videoRepository = new VideoRepository();
    VideoFileRepository videoFileRepository = new VideoFileRepository();
    VideoImageFileRepository videoImageFileRepository = new VideoImageFileRepository();
    LinkedHashMap<Integer, Video> videos;

    // 최신비디오 페이지별 10개 가져오기
    @RequestMapping(value = { "/api/video/callVideo10/{page}"})
    public LinkedHashMap<Integer, Video> callRecentlyVideo10(@PathVariable int page) {
        try {
            videos = videoRepository.callRecentlyVideoLimit10(page);
        } catch (SQLException e) {
            // 음악 불러오기 실패시
            videos = new LinkedHashMap<>();
        }
        return videos;
    }


    // 장르별 비디오 10개씩 가져오기
    @RequestMapping(value = { "/api/video/genre/{genere}/{page}"})
    public LinkedHashMap<Integer, Video> callGenereVideo(@PathVariable String genere,int page) {
        try {
            videos = videoRepository.findByGenereLimited10(page, genere);
        } catch (SQLException e) {
            // 음악 불러오기 실패시
            videos = new LinkedHashMap<>();
        }
        return videos;
    }

    // 비디오 이미지 가져오기
    @RequestMapping(value = { "/api/video/image/{videoNum}"})
    public VideoImage callMusicImage(@PathVariable  int videoNum) {
        VideoImage image = null;
        try {
            image = videoImageFileRepository.findByNumber(videoNum);
        } catch (SQLException e) {
            image = new VideoImage();
        }
        return image;
    }

    // 비디오 파일명 가져오기
    @RequestMapping(value = { "/api/video/file/{videoNum}"})
    public VideoFile callMusicFile(@PathVariable  int videoNum) {
        VideoFile file = null;
        try {
            file = videoFileRepository.findByNumber(videoNum);
        } catch (SQLException e) {
            file = new VideoFile();
        }
        return file;
    }
}
