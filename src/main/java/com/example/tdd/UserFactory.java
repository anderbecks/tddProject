package com.example.tdd;

public class UserFactory {

    public User createUser(String userName, String password){
        User user = new User(userName,password);
       return user;
    }
}
