package webApplication.musicPlatform.web.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import webApplication.musicPlatform.web.Repository.board.BoardImageRepository;
import webApplication.musicPlatform.web.Repository.board.BoardRepository;
import webApplication.musicPlatform.web.Repository.music.MusicFileRepository;
import webApplication.musicPlatform.web.Repository.music.MusicImageFileRepository;
import webApplication.musicPlatform.web.Repository.music.MusicRepository;
import webApplication.musicPlatform.web.domain.*;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class MusicPaging {
    MusicRepository musicRepository = new MusicRepository();
    MusicFileRepository musicFileRepository = new MusicFileRepository();
    MusicImageFileRepository musicImageFileRepository = new MusicImageFileRepository();

    LinkedHashMap<Integer, Music> musics;

    // 최신노래 페이지별 10개 가져오기
    @RequestMapping(value = { "/api/music/callMusic10/{page}"})
    public LinkedHashMap<Integer, Music> callRecentlyMusic10(@PathVariable int page) {
        try {
            musics = musicRepository.callRecentlyMusicLimit10(page);
        } catch (SQLException e) {
            // 음악 불러오기 실패시
            musics = new LinkedHashMap<>();
        }
        return musics;
    }

    @RequestMapping(value = { "/api/music/callMusic5/{page}"})
    public LinkedHashMap<Integer, Music> callRecentlyMusic5(@PathVariable int page) {
        try {
            musics = musicRepository.callRecentlyMusicLimit5(page);
        } catch (SQLException e) {
            // 음악 불러오기 실패시
            musics = new LinkedHashMap<>();
        }
        return musics;
    }

    // 장르별 음악 10개씩 가져오기
    @RequestMapping(value = { "/api/music/genre/{genere}/{page}"})
    public LinkedHashMap<Integer, Music> callGenereMusic(@PathVariable String genere,@PathVariable  int page) {
        try {
            musics = musicRepository.findByGenreLimit10(page, genere);
        } catch (SQLException e) {
            // 음악 불러오기 실패시
            musics = new LinkedHashMap<>();
        }
        return musics;
    }

    // 음악 이미지 가져오기
    @RequestMapping(value = { "/api/music/image/{musicNum}"})
    public MusicImage callMusicImage(@PathVariable  int musicNum) {
        MusicImage image = null;
        try {
            image = musicImageFileRepository.findByNumber(musicNum);
        } catch (SQLException e) {
            image = new MusicImage();
        }
        return image;
    }

    // 음악 파일 가져오기
    @RequestMapping(value = { "/api/music/file/{musicNum}"})
    public MusicFile callMusicFile(@PathVariable  int musicNum) {
        MusicFile file = null;
        try {
            file = musicFileRepository.findByNumber(musicNum);
        } catch (SQLException e) {
            file = new MusicFile();
        }
        return file;
    }

    // 음악 정보 가져오기
    @RequestMapping(value = { "/api/music/info/{musicNum}"})
    public Music callMusicInfo(@PathVariable  int musicNum) {
        Music music = null;
        try {
            music = musicRepository.findByNumber(musicNum);
        } catch (SQLException e) {
            music = new Music();
        }
        return music;
    }

    // https://focus-dev.tistory.com/105
    //https://luvstudy.tistory.com/172
    @RequestMapping(value = "/api/music/play/{musicNumber}", method = RequestMethod.GET)
    public ResponseEntity<ResourceRegion> musicRegion(@RequestHeader HttpHeaders headers, @PathVariable int musicNumber) throws Exception {

        MusicFile musicFile = musicFileRepository.findByNumber(musicNumber);
        String music = musicFile.getServerFileName();

        String path = "src/main/webapp/resources/musics/" + music;

        Resource resource = new FileSystemResource(path);

        long chunkSize = 1300 * 1300;
        long contentLength = resource.contentLength();

        ResourceRegion region;

        try {
            HttpRange httpRange = headers.getRange().stream().findFirst().get();
            long start = httpRange.getRangeStart(contentLength);
            long end = httpRange.getRangeEnd(contentLength);
            long rangeLength = Long.min(chunkSize, end -start + 1);

            log.info("start === {} , end == {}", start, end);

//            region = new ResourceRegion(resource, start, end);
            region = new ResourceRegion(resource, start, rangeLength);
        } catch (Exception e) {
            long rangeLength = Long.min(chunkSize, contentLength);
            region = new ResourceRegion(resource, 0, rangeLength);
        }

        return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
                .cacheControl(CacheControl.maxAge(1000, TimeUnit.MINUTES))
                .contentType(MediaTypeFactory.getMediaType(resource).orElse(MediaType.APPLICATION_OCTET_STREAM))
                .header("Accept-Ranges", "bytes")
                .eTag(path)
                .body(region);

    }
}

