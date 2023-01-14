package com.example.onlineshop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class ProductNotExist extends Exception{

    public ProductNotExist(){
        super("Product not exist", new Throwable());
    }
}
