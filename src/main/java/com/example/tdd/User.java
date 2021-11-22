package com.example.tdd;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class User {
    private String userName;
    private String Password;

    public User(String userName, String password) {
        this.userName = userName;
        Password = password;
    }
}
