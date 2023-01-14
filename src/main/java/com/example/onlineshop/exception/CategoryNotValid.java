package com.example.onlineshop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class CategoryNotValid extends Exception{
    public CategoryNotValid(){
        super("Category not valid", new Throwable());
    }
}