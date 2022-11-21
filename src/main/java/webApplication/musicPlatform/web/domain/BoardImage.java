package webApplication.musicPlatform.web.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BoardImage {
    private int boardNumber;
    private String serverFilePath;

    public BoardImage() {
    }

    public BoardImage(int boardNumber, String serverFilePath) {
        this.boardNumber = boardNumber;
        this.serverFilePath = serverFilePath;
    }
}
