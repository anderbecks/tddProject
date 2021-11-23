package com.example.tdd;

import java.security.NoSuchAlgorithmException;

public class UserFactory {

    public User createUser(SecureUtils secureUtils, String userName, String password) throws NoSuchAlgorithmException {
        byte[] salt = SecureUtils.getSalt();
        password = SecureUtils.getSecurePassword(password, salt);
        User user = new User(userName, salt, password);
        System.out.println(password);
       return user;
    }
}
