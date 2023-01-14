package com.example.onlineshop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CategoryNotExist extends Throwable {
    public CategoryNotExist(){
        super("Category not exist", new Throwable());
    }
}
