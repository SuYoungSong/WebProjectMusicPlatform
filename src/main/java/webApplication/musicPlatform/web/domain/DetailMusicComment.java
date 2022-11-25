package webApplication.musicPlatform.web.domain;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class DetailMusicComment {
    private String writer;
    private String commentText;
    private int musicNumber;

    public DetailMusicComment() {
    }

    public DetailMusicComment(String writer, String commentText, int musicNumber) {
        this.writer = writer;
        this.commentText = commentText;
        this.musicNumber = musicNumber;
    }
}
