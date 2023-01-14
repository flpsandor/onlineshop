package com.example.onlineshop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class PasswordNotMatch extends Exception{

    public PasswordNotMatch(){
        super("Product dont match", new Throwable());
    }
}
