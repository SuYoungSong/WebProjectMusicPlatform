package webApplication.musicPlatform.web.domain;

import lombok.Data;

@Data
public class User {
    private String id;
    private String password;
    private String phone;
    private String name;
    private String nickname;

    public User() {
    }

    public User(String id, String password, String phone, String name, String nickname) {
        this.id = id;
        this.password = password;
        this.phone = phone;
        this.name = name;
        this.nickname = nickname;
    }
}
