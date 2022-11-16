package webApplication.musicPlatform.web.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MusicImage {
    private int musicNumber;
    private String serverFilePath;


    public MusicImage() {
    }

    public MusicImage(int musicNumber, String serverFilePath) {
        this.musicNumber = musicNumber;
        this.serverFilePath = serverFilePath;
    }
}
