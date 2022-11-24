package webApplication.musicPlatform.web.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import webApplication.musicPlatform.web.Repository.board.BoardImageRepository;
import webApplication.musicPlatform.web.Repository.board.BoardRepository;
import webApplication.musicPlatform.web.Repository.music.MusicFileRepository;
import webApplication.musicPlatform.web.Repository.music.MusicImageFileRepository;
import webApplication.musicPlatform.web.Repository.music.MusicRepository;
import webApplication.musicPlatform.web.domain.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

@RestController
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

    // 음악 파일명 가져오기
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
}
