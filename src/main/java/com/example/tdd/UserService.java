package com.example.tdd;

import com.example.tdd.entities.User;
import com.example.tdd.enums.Permissions;
import com.example.tdd.enums.Resource;
import com.example.tdd.utils.JwtUtils;
import com.example.tdd.utils.SecureUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class UserService {

    @Autowired
    JwtService jwtService;

    private List<User> userList = new ArrayList<>();
    public void createUser(SecureUtils secureUtils, String userName, String password, Resource resource, List<Permissions> permissions) throws NoSuchAlgorithmException {
        byte[] salt = SecureUtils.getSalt();
        HashMap<Resource, Permissions> perMap = new HashMap<>();
        password = SecureUtils.getSecurePassword(password, salt);
        User user = new User(userName, salt, password, new HashMap<>());
        user.getAuthorisations().put(resource, permissions);
        System.out.println(password);
       userList.add(user);
    }

    public User getUser(String username) {
        for (User user : userList){
            if (user.getUserName().equals(username)){
                return user;
            }
        }
        return null;
    }

    public List<User> getAllUsers(){
        return userList;
    }

    public List<Permissions> getUserPermissions(String token, Resource resource){
        if (jwtService.jwtIsValid(token)){
            Claims claims = JwtUtils.decodeJWT(token);
            for (User user : userList) {
                if (user.getUserName().equals(claims.getId())) {
                    return user.getAuthorisations().get(resource);
                }
            }
        }


        return null;
    }
}
