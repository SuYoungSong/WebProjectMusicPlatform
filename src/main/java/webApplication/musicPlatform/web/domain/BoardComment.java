package webApplication.musicPlatform.web.domain;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class BoardComment {
    private String writer;
    private String commentText;
    private int boardNumber;

    public BoardComment() {
    }

    public BoardComment(String writer, String commentText, int boardNumber) {
        this.writer = writer;
        this.commentText = commentText;
        this.boardNumber = boardNumber;
    }
}
