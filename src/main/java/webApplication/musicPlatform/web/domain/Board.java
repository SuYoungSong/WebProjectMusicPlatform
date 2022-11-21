package webApplication.musicPlatform.web.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Board {
    private String writer;
    private String title;
    private String content;
    private String category;

    public Board(){ }

    public Board(String writer, String title, String content, String category) {
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.category = category;
    }
}
