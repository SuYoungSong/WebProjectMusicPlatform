package webApplication.musicPlatform.web.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import webApplication.musicPlatform.web.Repository.music.MusicFileRepository;
import webApplication.musicPlatform.web.Repository.music.MusicImageFileRepository;
import webApplication.musicPlatform.web.Repository.music.MusicRepository;
import webApplication.musicPlatform.web.Repository.video.VideoFileRepository;
import webApplication.musicPlatform.web.Repository.video.VideoImageFileRepository;
import webApplication.musicPlatform.web.Repository.video.VideoRepository;
import webApplication.musicPlatform.web.domain.*;

import java.io.File;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
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
    public LinkedHashMap<Integer, Video> callGenereVideo(@PathVariable String genere,@PathVariable int page) {
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

    // https://focus-dev.tistory.com/105

    @RequestMapping(value = "/api/video/show/{video}", method = RequestMethod.GET)
    public ResponseEntity<ResourceRegion> videoRegion(@RequestHeader HttpHeaders headers, @PathVariable String video) throws Exception {
        String path = "src/main/webapp/resources/videos/" + video;

        Resource resource = new FileSystemResource(path);

        long chunkSize = 1024 * 1024;
        long contentLength = resource.contentLength();

        ResourceRegion region;

        try {
            HttpRange httpRange = headers.getRange().stream().findFirst().get();
            long start = httpRange.getRangeStart(contentLength);
            long end = httpRange.getRangeEnd(contentLength);
            long rangeLength = Long.min(chunkSize, end -start + 1);

            log.info("start === {} , end == {}", start, end);

            region = new ResourceRegion(resource, start, rangeLength);
        } catch (Exception e) {
            long rangeLength = Long.min(chunkSize, contentLength);
            region = new ResourceRegion(resource, 0, rangeLength);
        }

        return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
                .cacheControl(CacheControl.maxAge(10, TimeUnit.MINUTES))
                .contentType(MediaTypeFactory.getMediaType(resource).orElse(MediaType.APPLICATION_OCTET_STREAM))
                .header("Accept-Ranges", "bytes")
                .eTag(path)
                .body(region);

    }
}
