package com.example.onlineshop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class UserTypeNotValid extends Throwable {
    public UserTypeNotValid(){
        super("User type not valid", new Throwable());
    }
}