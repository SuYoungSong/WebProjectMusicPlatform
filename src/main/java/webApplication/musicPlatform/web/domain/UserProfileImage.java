package webApplication.musicPlatform.web.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserProfileImage {
    private String userId;
    private String serverFilePath;
    private String userUploadFileName;

    public UserProfileImage() { }

    public UserProfileImage(String userId, String serverFilePath, String userUploadFileName) {
        this.userId = userId;
        this.serverFilePath = serverFilePath;
        this.userUploadFileName = userUploadFileName;
    }
}
