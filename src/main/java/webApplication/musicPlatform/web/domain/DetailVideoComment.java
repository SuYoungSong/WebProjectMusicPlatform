package webApplication.musicPlatform.web.domain;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class DetailVideoComment {
    private String writer;
    private String commentText;
    private int videoNumber;

    public DetailVideoComment() {
    }

    public DetailVideoComment(String writer, String commentText, int videoNumber) {
        this.writer = writer;
        this.commentText = commentText;
        this.videoNumber = videoNumber;
    }
}
