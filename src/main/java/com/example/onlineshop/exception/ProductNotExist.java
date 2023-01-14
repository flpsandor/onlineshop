package com.example.onlineshop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotExist extends Exception{

    public ProductNotExist(){
        super("Product not exist", new Throwable());
    }
}
