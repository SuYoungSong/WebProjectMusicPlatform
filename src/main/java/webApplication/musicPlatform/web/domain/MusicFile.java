package webApplication.musicPlatform.web.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MusicFile {
    private int musicNumber;
    private String serverFileName;
    private String userUploadFileName;

    public MusicFile() {
    }

    public MusicFile(int musicNumber, String serverFileName, String userUploadFileName) {
        this.musicNumber = musicNumber;
        this.serverFileName = serverFileName;
        this.userUploadFileName = userUploadFileName;
    }
}