package com.example.onlineshop.exception;

public class UserNotExist extends Throwable {
    public UserNotExist(){
        super("User not exist", new Throwable());
    }
}
