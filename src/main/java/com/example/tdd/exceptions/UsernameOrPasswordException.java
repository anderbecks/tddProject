package com.example.tdd.exceptions;

public class UsernameOrPasswordException extends Exception{
    public UsernameOrPasswordException(String message){
        super(message);
    }
}
