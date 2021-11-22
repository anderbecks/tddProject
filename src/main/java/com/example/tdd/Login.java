package com.example.tdd;

import java.util.List;

public class Login {

    public boolean loginValidator(List<User> userList, String userName, String password){
        for (User user : userList){
            if (user.getUserName().equals(userName)){
                if (user.getPassword().equals(password)){
                    return true;
                }
            }
        }

        return false;
    }
}
