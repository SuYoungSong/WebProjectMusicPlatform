package webApplication.musicPlatform.web.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class VideoImage {
    private int videoNumber;
    private String serverFilePath;


    public VideoImage() {
    }

    public VideoImage(int videoNumber, String serverFilePath) {
        this.videoNumber = videoNumber;
        this.serverFilePath = serverFilePath;
    }
}
