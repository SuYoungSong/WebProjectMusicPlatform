package webApplication.musicPlatform.web.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Video {

    private String uploadUserId;           // 영상 업로드 회원 아이디
    private String videoName;               // 영상 제목
    private String videoDescription;        // 영상 설명
    private String videoUrl;                // 영상이 저장된 URI
    private String videoGenre;              // 영상 장르

    public Video(String uploadUserId, String videoName, String videoDescription, String videoUrl, String videoGenre) {
        this.uploadUserId = uploadUserId;
        this.videoName = videoName;
        this.videoDescription = videoDescription;
        this.videoUrl = videoUrl;
        this.videoGenre = videoGenre;
    }
}
