package com.example.tdd;

import com.example.tdd.entities.User;
import com.example.tdd.exceptions.UsernameOrPasswordException;
import com.example.tdd.utils.JwtUtils;
import com.example.tdd.utils.SecureUtils;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class Login {

    public String loginValidator(List<User> userList, String userName, String password) throws UsernameOrPasswordException {
        for (User user : userList){
            if (user.getUserName().equals(userName)){
                password = SecureUtils.getSecurePassword(password, user.getSalt());
                if (user.getPassword().equals(password)){

                    return JwtUtils.createJWT(user);
                }
            }
        }

        throw new UsernameOrPasswordException("Wrong username or password");
    }
}
