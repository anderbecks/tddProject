package com.example.tdd;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class User {
    private String userName;
    private byte [] salt;
    private String password;

    public User(String userName, byte[] salt, String password) {
        this.userName = userName;
        this.salt = salt;
        this.password = password;
    }

}
