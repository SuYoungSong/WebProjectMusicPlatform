package webApplication.musicPlatform.web.domain;

import lombok.Data;

@Data
public class User {
    private String id;
    private String password;
    private String phone;
    private String name;
    private String nickname;
    private String profileImageUrl;

    public User() {
    }

    public User(String id, String password, String phone, String name, String nickname, String profileImageUrl) {
        this.id = id;
        this.password = password;
        this.phone = phone;
        this.name = name;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
    }
}
