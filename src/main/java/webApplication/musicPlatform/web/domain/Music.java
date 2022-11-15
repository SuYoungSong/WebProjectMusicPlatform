package webApplication.musicPlatform.web.domain;

import lombok.Data;

@Data
public class Music {
    public Music(String musicName, String uploadUser, String musicDescription, String genre, String lyrics, String songwriter, String lyricwriter, String musicArranger, String singer, String releaseDate) {
        this.musicName = musicName;
        this.uploadUser = uploadUser;
        this.musicDescription = musicDescription;
        this.genre = genre;
        this.lyrics = lyrics;
        this.songwriter = songwriter;
        this.lyricwriter = lyricwriter;
        this.musicArranger = musicArranger;
        this.singer = singer;
        this.releaseDate = releaseDate;
    }

    private String musicName;           // 음악 제목
    private String uploadUser;          // 음악 업로드 회원 아이디
    private String musicDescription;    // 음악 설명
    private String genre;               // 음악 장르
    private String lyrics;              // 음악 가사
    private String songwriter;          // 음악 작곡가
    private String lyricwriter;         // 음악 작사가
    private String musicArranger;       // 음악 편곡가
    private String singer;              // 가수
    private String releaseDate;         // 음악 발매일

    public Music() {
    }


}
