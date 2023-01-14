package com.example.onlineshop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class UserExist extends Throwable {
    public UserExist(){
        super("User with email exist", new Throwable());
    }
}
