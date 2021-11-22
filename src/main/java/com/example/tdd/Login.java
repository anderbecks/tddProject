package com.example.tdd;

public class Login {

    public boolean loginValidator(User user, String userName, String password){
        if (user.getUserName().equals(userName)){
            if (user.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }
}
