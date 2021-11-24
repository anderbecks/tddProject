package com.example.tdd;

import com.example.tdd.exceptions.UsernameOrPasswordException;
import org.springframework.stereotype.Service;

import java.util.List;

public class Login {

    public boolean loginValidator(List<User> userList, String userName, String password) throws UsernameOrPasswordException {
        for (User user : userList){
            if (user.getUserName().equals(userName)){
                if (user.getPassword().equals(password)){
                    return true;
                }
            }
        }

        throw new UsernameOrPasswordException("Wrong username or password");
    }
}
