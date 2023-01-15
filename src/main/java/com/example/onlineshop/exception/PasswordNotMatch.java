package com.example.onlineshop.exception;

public class PasswordNotMatch extends Exception{

    public PasswordNotMatch(){
        super("Product dont match", new Throwable());
    }
}
