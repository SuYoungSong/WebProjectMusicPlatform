package webApplication.musicPlatform.web.domain;

import lombok.Data;

@Data
public class Video {

    private String uploadUserId;           // 영상 업로드 회원 아이디
    private String videoName;               // 영상 제목
    private String videoDescription;        // 영상 설명
    private String videoGenre;              // 영상 장르

    public Video() {
    }

    public Video(String uploadUserId, String videoName, String videoDescription, String videoGenre) {
        this.uploadUserId = uploadUserId;
        this.videoName = videoName;
        this.videoDescription = videoDescription;
        this.videoGenre = videoGenre;
    }
}
