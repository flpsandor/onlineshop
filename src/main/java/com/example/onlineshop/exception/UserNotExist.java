package com.example.onlineshop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class UserNotExist extends Throwable {
    public UserNotExist(){
        super("User not exist", new Throwable());
    }
}
