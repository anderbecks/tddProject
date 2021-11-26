package com.example.tdd;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {


    public boolean jwtIsValid(String jwt){
        Claims claims = JwtUtils.decodeJWT(jwt);
        Long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        if (claims.getExpiration().compareTo(now) > 0){
            return true;
        }
        return false;
    }
}
