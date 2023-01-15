package com.example.onlineshop.exception;

public class UserExist extends Throwable {
    public UserExist(){
        super("User with email exist", new Throwable());
    }
}
