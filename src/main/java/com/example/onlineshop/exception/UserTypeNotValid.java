package com.example.onlineshop.exception;

public class UserTypeNotValid extends Throwable {
    public UserTypeNotValid(){
        super("User type not valid", new Throwable());
    }
}
