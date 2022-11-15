package webApplication.musicPlatform.web.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class VideoFile {
    private int videoNumber;
    private String serverFileName;
    private String userUploadFileName;

    public VideoFile() {
    }

    public VideoFile(int videoNumber, String serverFileName, String userUploadFileName) {
        this.videoNumber = videoNumber;
        this.serverFileName = serverFileName;
        this.userUploadFileName = userUploadFileName;
    }
}
